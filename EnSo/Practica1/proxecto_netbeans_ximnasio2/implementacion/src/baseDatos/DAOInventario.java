package baseDatos;

import aplicacion.EstadoMaterial;
import aplicacion.ProductoInventario;
import aplicacion.TipoMaterial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOInventario extends AbstractDAO {

    public DAOInventario(Connection conexion, aplicacion.FachadaAplicacion fa) {
        //gardamos a conexion á base de datos e a referencia á fachada 
        //para poder enviarlle mensaxes de erro á interface se fai falla
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    //metodo para dar de alta un material. como usamos herdanza en postgres,
    //temos que facer un insert na taboa pai e logo na filla correspondente.
    //por iso é unha transaccion multi-sentenza
    public void altaProducto(ProductoInventario p) { 
        Connection con = this.getConexion();
        PreparedStatement stmBase = null;
        PreparedStatement stmSub = null;
        boolean oldAutoCommit = true;

        try {
            //desactivamos o autocommit para garantir a atomicidade.
            //se falla o insert na taboa filla, non queremos que quede o material orfo na taboa pai
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            //verificamos que non exista xa un material con ese id ou nome
            comprobarProductoDuplicado(p.getCodigoIdentificador(), p.getNome());

            //inserimos primeiro na taboa pai (MATERIAL).
            //temos que facer un cast explicito a ::estado_material_enum e ::tipo_material_enum 
            //para que postgres non dea un erro de tipos cos enums
            stmBase = con.prepareStatement(
                    "INSERT INTO MATERIAL (codigoIdentificador, nome, estado, idSala, tipo_material) " +
                            "VALUES (?, ?, ?::estado_material_enum, ?, ?::tipo_material_enum)"
            );
            stmBase.setString(1, p.getCodigoIdentificador());
            stmBase.setString(2, p.getNome());
            stmBase.setString(3, p.getEstado().name());

            //se a sala é null, insertamos un valor NULL real en SQL, non un 0
            if (p.getIdSala() == null) {
                stmBase.setNull(4, Types.INTEGER);
            } else {
                stmBase.setInt(4, p.getIdSala());
            }

            stmBase.setString(5, p.getTipoMaterial().name());
            stmBase.executeUpdate();

            //dependendo do tipo de material facemos o segundo insert na taboa filla que toque
            switch (p.getTipoMaterial()) {
                case PESO_LIBRE:
                    stmSub = con.prepareStatement(
                            "INSERT INTO MATERIAL_PESO_LIBRE (codigoIdentificador, tipo, peso) VALUES (?, ?, ?)"
                    );
                    stmSub.setString(1, p.getCodigoIdentificador());
                    stmSub.setString(2, p.getSubtipo());
                    stmSub.setBigDecimal(3, p.getPeso());
                    break;
                case MAQUINA:
                    stmSub = con.prepareStatement(
                            "INSERT INTO MAQUINA (codigoIdentificador, tipo) VALUES (?, ?)"
                    );
                    stmSub.setString(1, p.getCodigoIdentificador());
                    stmSub.setString(2, p.getSubtipo());
                    break;
                case SACO:
                    stmSub = con.prepareStatement(
                            "INSERT INTO SACOS (codigoIdentificador, material_fabrica, peso) VALUES (?, ?, ?)"
                    );
                    stmSub.setString(1, p.getCodigoIdentificador());
                    stmSub.setString(2, p.getMaterialFabrica());
                    stmSub.setBigDecimal(3, p.getPeso());
                    break;
                case GUANTE:
                    stmSub = con.prepareStatement(
                            "INSERT INTO GUANTES (codigoIdentificador, tipo) VALUES (?, ?)"
                    );
                    stmSub.setString(1, p.getCodigoIdentificador());
                    stmSub.setString(2, p.getSubtipo());
                    break;
                default:
                    throw new SQLException("Tipo de material non soportado");
            }
            stmSub.executeUpdate();

            //se chegamos ata aqui sen excepcions, confirmamos os dous inserts á vez na base de datos
            con.commit();

        } catch (SQLException e) {
            //se algo fallou (por exemplo unha violacion de unique), desfacemos todo
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error en altaProducto: " + e.getMessage());
            
            //lanzamos runtime para que a ventá se entere de que fallou e non amose mensaxe de exito
            throw new RuntimeException("Fallo al insertar el producto en la base de datos.");
            
        } finally {
            //pechamos os statements para liberar recursos de memoria
            cerrar(stmSub);
            cerrar(stmBase);
            restaurarAutoCommit(con, oldAutoCommit);
        }
    }

    //metodo auxiliar para evitar meter dúas maquinas ou pesas co mesmo nome ou id.
    //usamos UPPER para que a busqueda por nome non distinga maiusculas e minusculas
    private void comprobarProductoDuplicado(String codigo, String nome) throws SQLException { 
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = this.getConexion().prepareStatement(
                    "SELECT codigoIdentificador FROM MATERIAL " +
                            "WHERE codigoIdentificador = ? OR UPPER(nome) = UPPER(?)"
            );
            stm.setString(1, codigo);
            stm.setString(2, nome);
            rs = stm.executeQuery();

            if (rs.next()) {
                throw new SQLException("Xa existe un produto con ese código ou nome");
            }
        } finally {
            cerrar(rs);
            cerrar(stm);
        }
    }

    //obtemos toda a lista de materiais para mostralos na taboa da interface grafica do inventario
    public List<ProductoInventario> consultarProductos() { 
        List<ProductoInventario> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = con.prepareStatement(
                    "SELECT codigoIdentificador, nome, estado, idSala, tipo_material " +
                            "FROM MATERIAL ORDER BY nome"
            );
            rs = stm.executeQuery();

            //imos lendo cada fila do resultset e transformandoa nun obxecto java
            while (rs.next()) {
                resultado.add(new ProductoInventario(
                        rs.getString("codigoIdentificador"),
                        rs.getString("nome"),
                        EstadoMaterial.valueOf(rs.getString("estado")),
                        (Integer) rs.getObject("idSala"),
                        TipoMaterial.valueOf(rs.getString("tipo_material")),
                        null,
                        null,
                        null
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error en consultarProductos: " + e.getMessage());
        } finally {
            cerrar(rs);
            cerrar(stm);
        }

        return resultado;
    }

    //actualiza se unha maquina esta operativa rota ou en mantemento
    public void actualizarEstadoProducto(String codigoProducto, EstadoMaterial novoEstado) { 
        Connection con = this.getConexion();
        PreparedStatement stmLock = null;
        PreparedStatement stmUpdate = null;
        ResultSet rs = null;
        boolean oldAutoCommit = true;
        
        try {
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            
            //subimos o illamento a REPEATABLE_READ para evitar lecturas fantasma
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            //bloqueamos a fila do material con FOR UPDATE (bloqueo pesimista).
            //asi aseguramos que dous administradores non cambien o estado da mesma maquina ao mesmo tempo
            stmLock = con.prepareStatement(
                    "SELECT codigoIdentificador FROM MATERIAL WHERE codigoIdentificador = ? FOR UPDATE"
            );
            stmLock.setString(1, codigoProducto);
            rs = stmLock.executeQuery();
            
            if (!rs.next()) {
                throw new SQLException("Non existe o produto");
            }

            stmUpdate = con.prepareStatement(
                    "UPDATE MATERIAL SET estado = ?::estado_material_enum WHERE codigoIdentificador = ?"
            );
            stmUpdate.setString(1, novoEstado.name());
            stmUpdate.setString(2, codigoProducto);

            if (stmUpdate.executeUpdate() != 1) {
                throw new SQLException("Non se puido actualizar o estado");
            }

            con.commit();
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error en actualizarEstadoProducto: " + e.getMessage());
            
            throw new RuntimeException("Error en la base de datos al actualizar el estado.");
        } finally {
            cerrar(rs);
            cerrar(stmUpdate);
            cerrar(stmLock);
            restaurarAutoCommit(con, oldAutoCommit);
        }
    }
    
    //borra un material do ximnasio definitivamente
    public void baixaProducto(String codigoProducto) { 
        Connection con = this.getConexion();
        PreparedStatement stmLock = null;
        PreparedStatement stmDeleteBase = null;
        ResultSet rs = null;
        boolean oldAutoCommit = true;

        try {
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            stmLock = con.prepareStatement(
                    "SELECT codigoIdentificador FROM MATERIAL WHERE codigoIdentificador = ? FOR UPDATE"
            );
            stmLock.setString(1, codigoProducto);
            rs = stmLock.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Non existe o produto");
            }

            //so borramos de MATERIAL porque en postgres creamos as foreign keys con ON DELETE CASCADE.
            //postgres encargase el mesmo de borralo da subtaboa de forma automatica e limpa
            stmDeleteBase = con.prepareStatement(
                    "DELETE FROM MATERIAL WHERE codigoIdentificador = ?"
            );
            stmDeleteBase.setString(1, codigoProducto);

            if (stmDeleteBase.executeUpdate() != 1) {
                throw new SQLException("Non se puido borrar o produto");
            }

            con.commit();
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error en baixaProducto: " + e.getMessage());
        } finally {
            cerrar(rs);
            cerrar(stmDeleteBase);
            cerrar(stmLock);
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
    
    //saca unha lista dos id de todas as salas para metelas nos despregables da interface grafica
    public java.util.List<Integer> consultarIdSalas() { 
        java.util.List<Integer> resultado = new java.util.ArrayList<>();
        java.sql.Connection con = this.getConexion();
        java.sql.PreparedStatement stm = null;
        java.sql.ResultSet rs = null;
        
        try {
            stm = con.prepareStatement("SELECT idSala FROM SALA ORDER BY idSala");
            rs = stm.executeQuery();
            while (rs.next()) {
                resultado.add(rs.getInt("idSala"));
            }
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al consultar salas: " + e.getMessage());
        } finally {
            cerrar(rs);
            cerrar(stm);
        }
        return resultado;
    }
}