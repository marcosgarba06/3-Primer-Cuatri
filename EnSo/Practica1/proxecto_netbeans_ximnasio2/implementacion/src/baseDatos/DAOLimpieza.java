package baseDatos;

import aplicacion.SalaLibre;
import aplicacion.TraballadorLimpiezaLibre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOLimpieza extends AbstractDAO {

    public DAOLimpieza(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    //obten unha lista de todas as salas para poder mostralas no desplegable 
    //da interface de asignacion de limpeza
    public List<SalaLibre> consultarSalas() { 
        List<SalaLibre> resultado = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = this.getConexion().prepareStatement(
                "SELECT s.idSala, s.nomeXimnasio " +
                "FROM SALA s " +
                "ORDER BY s.idSala"
            );
            rs = stm.executeQuery();

            while (rs.next()) {
                resultado.add(new SalaLibre(
                    rs.getInt("idSala"),
                    rs.getString("nomeXimnasio")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error en consultarSalas: " + e.getMessage());
        } finally {
            cerrar(rs);
            cerrar(stm);
        }

        return resultado;
    }
   
    //obten unha lista cos traballadores da taboa LIMPEZA.
    //usamos un JOIN coa taboa pai USUARIO para poder mostrar o nome completo 
    //no canto de so o DNI na interface
    public List<TraballadorLimpiezaLibre> consultarTraballadoresLimpiezaLibres() { 
        List<TraballadorLimpiezaLibre> resultado = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //con COALESCE evitamos que o nome completo sexa NULL se o usuario non ten segundo apelido
            stm = this.getConexion().prepareStatement(
                "SELECT u.dni, (u.nome || ' ' || u.apelido1 || ' ' || COALESCE(u.apelido2,'')) AS nomeCompleto " +
                "FROM LIMPEZA l " +
                "JOIN USUARIO u ON l.dni = u.dni " +
                "ORDER BY nomeCompleto"
            );
            rs = stm.executeQuery();

            while (rs.next()) {
                resultado.add(new TraballadorLimpiezaLibre(
                    rs.getString("dni"),
                    rs.getString("nomeCompleto").trim()
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(
                "Error en consultarTraballadoresLimpiezaLibres: " + e.getMessage()
            );
        } finally {
            cerrar(rs);
            cerrar(stm);
        }

        return resultado;
    }
   
    //asigna un traballador a unha sala gardandoo na taboa relacional N:M LIMPIA
    public boolean asignarLimpiezaASala(String dniTraballador, Integer idSala) { 
        Connection con = this.getConexion();
        PreparedStatement stmLockSala = null;
        PreparedStatement stmLockTrab = null;
        PreparedStatement stmInsert = null;
        ResultSet rsSala = null;
        ResultSet rsTrab = null;
        boolean oldAutoCommit = true;

        try {
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            
            //usamos o nivel de illamento maximo (SERIALIZABLE) porque a asignacion é unha operacion critica.
            //evita calquera anomalia de concorrencia se dous admins asignan á vez
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            //bloqueamos a fila da sala
            stmLockSala = con.prepareStatement(
                "SELECT idSala FROM SALA WHERE idSala = ? FOR UPDATE"
            );
            stmLockSala.setInt(1, idSala);
            rsSala = stmLockSala.executeQuery();
            if (!rsSala.next()) {
                throw new SQLException("A sala non existe");
            }

            //bloqueamos a fila do traballador
            stmLockTrab = con.prepareStatement(
                "SELECT dni FROM LIMPEZA WHERE dni = ? FOR UPDATE"
            );
            stmLockTrab.setString(1, dniTraballador);
            rsTrab = stmLockTrab.executeQuery();
            if (!rsTrab.next()) {
                throw new SQLException("O traballador de limpeza non existe");
            }

            //unha vez cos dous bloqueos asegurados, insertamos a relacion
            stmInsert = con.prepareStatement(
                "INSERT INTO LIMPIA (dniTraballador, idSala) VALUES (?, ?)"
            );
            stmInsert.setString(1, dniTraballador);
            stmInsert.setInt(2, idSala);

            if (stmInsert.executeUpdate() != 1) {
                throw new SQLException("Non se puido facer a asignación");
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}

            //capturamos o codigo 23505 que é unha violacion de clave primaria en postgres.
            //asi evitamos que a app crashee se intentan asignar a mesma persoa á mesma sala dúas veces
            if ("23505".equals(e.getSQLState())) {
                this.getFachadaAplicacion().muestraExcepcion(
                    "A asignación xa existe para ese traballador e esa sala."
                );
                return false;
            }

            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error en asignarLimpiezaASala: " + e.getMessage());
            return false;

        } finally {
            cerrar(rsTrab);
            cerrar(rsSala);
            cerrar(stmInsert);
            cerrar(stmLockTrab);
            cerrar(stmLockSala);
            restaurarAutoCommit(con, oldAutoCommit);
        }
    }
   

    private void cerrar(AutoCloseable ac) {
        if (ac != null) {
            try { ac.close(); } catch (Exception e) {}
        }
    }

    private void restaurarAutoCommit(Connection con, boolean oldValue) {
        try { con.setAutoCommit(oldValue); } catch (SQLException e) {}
    }
  
}