package baseDatos;

import aplicacion.EstadoMembresia;
import aplicacion.TipoUsuarioGym;
import aplicacion.TraballadorGym;
import aplicacion.TipoTraballadorGym;
import aplicacion.UsuarioGym;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuariosGym extends AbstractDAO {

    public DAOUsuariosGym(Connection conexion, aplicacion.FachadaAplicacion fa) {
        //gardamos a conexion á base de datos e a referencia á fachada da aplicacion para poder enviarlle mensaxes de erro á interface grafica se salta algunha excepcion
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
  
    //metodo para dar de alta a un cliente e rexistrar o seu primeiro pago.
    //fai falta gardalo en USUARIO, en CLIENTE e xerar unha FACTURA nunha soa transaccion
    public boolean altaUsuarioClienteConFactura(UsuarioGym u, java.math.BigDecimal importe, String metodoPago) {
        Connection con = this.getConexion();
        PreparedStatement stmUsuario = null;
        PreparedStatement stmCliente = null;
        PreparedStatement stmFactura = null;
        boolean oldAutoCommit = true;

        try {
            //desactivamos o autocommit para que se falla calquera cousa (por exemplo a factura), non se garde o usuario a medias na base de datos e desfaga todo automaticamente
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            
            //usamos SERIALIZABLE que é o nivel de illamento mais estrito para que ninguen poida rexistrarse ao mesmo tempo co mesmo correo e estropear o proceso
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            //comprobamos que o correo e o dni non existan xa na BD
            comprobarDuplicadosUsuario(u.getDni(), u.getEmail());

            //gardamos os datos principais na taboa pai.
            //un detalle importante: como usamos un enum propio de postgres (tipo_usuario_enum), temos que facer o cast explicito co :: para que o driver jdbc saiba interpretalo ben
            stmUsuario = con.prepareStatement(
                "INSERT INTO USUARIO " +
                "(dni, password_hash, nome, apelido1, apelido2, direccion, movil, email, data_nacemento, nomeXimnasio, tipo_usuario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::tipo_usuario_enum)"
            );
            stmUsuario.setString(1, u.getDni());
            
            //aplicamos o hash SHA-256 ao contrasinal antes de mandalo para que vaia protexido 
            //e non se almacene o texto plano na BD por motivos de seguridade
            stmUsuario.setString(2, aplicacion.UtilidadesSeguridade.sha256(u.getPasswordHash()));
            stmUsuario.setString(3, u.getNome());
            stmUsuario.setString(4, u.getApelido1());
            stmUsuario.setString(5, u.getApelido2());
            stmUsuario.setString(6, u.getDireccion());
            stmUsuario.setString(7, u.getMovil());
            stmUsuario.setString(8, u.getEmail());
            stmUsuario.setDate(9, u.getDataNacemento());
            stmUsuario.setString(10, u.getNomeXimnasio());
            stmUsuario.setString(11, "CLIENTE"); 
            stmUsuario.executeUpdate();

            //gardamos a fila correspondente na subtáboa de cliente usando de novo o cast para o enum do estado
            stmCliente = con.prepareStatement(
                "INSERT INTO CLIENTE (dni, estado_membresia) VALUES (?, ?::estado_membresia_enum)"
            );
            stmCliente.setString(1, u.getDni());
            stmCliente.setString(2, EstadoMembresia.ACTIVO.name());
            stmCliente.executeUpdate();

            //creamos o identificador unico da factura mediante un uuid aleatorio de java
            String codigoFactura = "F-" + java.util.UUID.randomUUID();

            //por ultimo vinculamos o pago desa cota asociandoo ao dni que acabamos de crear
            stmFactura = con.prepareStatement(
                "INSERT INTO FACTURA (CodigoFactura, dniCliente, importe, metodoPago) VALUES (?, ?, ?, ?::metodo_pago_enum)"
            );
            stmFactura.setString(1, codigoFactura);
            stmFactura.setString(2, u.getDni());
            stmFactura.setBigDecimal(3, importe);
            stmFactura.setString(4, metodoPago); 
            stmFactura.executeUpdate();

            //se todo o bloque try chegou ata aqui sen lanzar excepcions, confirmamos todo xunto
            con.commit();
            return true;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println(e.getMessage());
            return false;

        } finally {
            //pechamos todos os statements no bloque finally para liberar espazo de memoria no servidor
            cerrar(stmFactura);
            cerrar(stmCliente);
            cerrar(stmUsuario);
            restaurarAutoCommit(con, oldAutoCommit);
        }
    }

    //insercion dun traballador novo. neste caso a herdanza é un pouco máis longa e temos que facer unha transaccion a tres bandas: 
    //primeiro en USUARIO, logo en TRABALLADOR e por ultimo na súa especialidade
    public boolean altaUsuarioTraballador(TraballadorGym t) { 
        Connection con = this.getConexion();
        PreparedStatement stmUsuario = null;
        PreparedStatement stmTrab = null;
        PreparedStatement stmSub = null;
        boolean oldAutoCommit = true;

        try {
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            comprobarDuplicadosUsuario(t.getDni(), t.getEmail());

            //inserimos os datos comúns na taboa xeral encriptando o contrasinal
            stmUsuario = con.prepareStatement(
                "INSERT INTO USUARIO " +
                "(dni, password_hash, nome, apelido1, apelido2, direccion, movil, email, data_nacemento, nomeXimnasio, tipo_usuario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::tipo_usuario_enum)"
            );
            stmUsuario.setString(1, t.getDni());
            stmUsuario.setString(2, aplicacion.UtilidadesSeguridade.sha256(t.getPasswordHash()));
            stmUsuario.setString(3, t.getNome());
            stmUsuario.setString(4, t.getApelido1());
            stmUsuario.setString(5, t.getApelido2());
            stmUsuario.setString(6, t.getDireccion());
            stmUsuario.setString(7, t.getMovil());
            stmUsuario.setString(8, t.getEmail());
            stmUsuario.setDate(9, t.getDataNacemento());
            stmUsuario.setString(10, t.getNomeXimnasio());
            stmUsuario.setString(11, t.getTipoUsuario().name()); 
            stmUsuario.executeUpdate();

            //inserimos o seu soldo e horario no primeiro nivel de herdanza
            stmTrab = con.prepareStatement(
                "INSERT INTO TRABALLADOR (dni, soldo, horario, tipo_traballador) VALUES (?, ?, ?, ?::tipo_traballador_enum)"
            );
            stmTrab.setString(1, t.getDni());
            stmTrab.setBigDecimal(2, t.getSoldo());
            stmTrab.setString(3, t.getHorario());
            stmTrab.setString(4, t.getTipoTraballador().name());
            stmTrab.executeUpdate();

            //como un traballador pode ser de varios tipos, usamos un switch para meter o rexistro na taboa filla correspondente que toque
            switch (t.getTipoTraballador()) {
                case ADMINISTRADOR -> {
                    stmSub = con.prepareStatement("INSERT INTO ADMINISTRADOR (dni, cargo) VALUES (?, ?)");
                    stmSub.setString(1, t.getDni());
                    stmSub.setString(2, t.getCargo());
                    stmSub.executeUpdate();
                }
                case MONITOR -> {
                    stmSub = con.prepareStatement("INSERT INTO MONITOR (dni, especialidade) VALUES (?, ?)");
                    stmSub.setString(1, t.getDni());
                    stmSub.setString(2, t.getEspecialidade());
                    stmSub.executeUpdate();
                }
                case LIMPEZA -> {
                    stmSub = con.prepareStatement("INSERT INTO LIMPEZA (dni) VALUES (?)");
                    stmSub.setString(1, t.getDni());
                    stmSub.executeUpdate();
                }
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println(e.getMessage());
            return false;

        } finally {
            cerrar(stmSub);
            cerrar(stmTrab);
            cerrar(stmUsuario);
            restaurarAutoCommit(con, oldAutoCommit);
        }
    }

    //metodo de apoio para lanzar a excepcion axiña se alguén intenta rexistrar un dni xa usado
    private void comprobarDuplicadosUsuario(String dni, String email) throws SQLException { 
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = this.getConexion().prepareStatement(
                    "SELECT dni FROM USUARIO WHERE dni = ? OR email = ?"
            );
            stm.setString(1, dni);
            stm.setString(2, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                throw new SQLException("Xa existe un usuario con ese DNI ou email");
            }
        } finally {
            cerrar(rs);
            cerrar(stm);
        }
    }

    //busqueda de usuarios pola interface de xestion.
    //usamos sql dinamico porque non sabemos se o administrador vai buscar só por dni, só por nome ou polos dous á vez, entón construimos o select cos ifs
    public List<UsuarioGym> buscarUsuariosAdmin(String dni, String nome) { 
        List<UsuarioGym> resultado = new ArrayList<>();
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder(
                "SELECT dni, password_hash, nome, apelido1, apelido2, direccion, movil, email, data_nacemento, nomeXimnasio, tipo_usuario " +
                        "FROM USUARIO WHERE 1=1 "
        );

        if (dni != null && !dni.trim().isEmpty()) {
            sql.append(" AND dni = ? ");
        }
        
        //concatenamos os apelidos para permitir busquedas por nome completo.
        //usamos COALESCE porque o apelido2 pode ser null e ao concatenar estropearíase a cadea.
        //tamén aplicamos UPPER para que a busqueda ignore as maiusculas e minusculas
        if (nome != null && !nome.trim().isEmpty()) {
            sql.append(" AND UPPER(nome || ' ' || apelido1 || ' ' || COALESCE(apelido2,'')) LIKE UPPER(?) ");
        }
        sql.append(" ORDER BY apelido1, apelido2, nome ");

        try {
            stm = con.prepareStatement(sql.toString());
            
            //usamos un contador 'i' para ir metendo os parametros do prepared statement na orde correcta
            int i = 1;
            if (dni != null && !dni.trim().isEmpty()) {
                stm.setString(i++, dni);
            }
            if (nome != null && !nome.trim().isEmpty()) {
                //metémoslle as porcentaxes para que o LIKE busque coincidencias parciais de texto
                stm.setString(i++, "%" + nome.trim() + "%");
            }

            rs = stm.executeQuery();
            
            //imos creando un obxecto por cada rexistro para mandalo e pintalo na taboa da interface
            while (rs.next()) {
                resultado.add(new UsuarioGym(
                        rs.getString("dni"),
                        rs.getString("password_hash"),
                        rs.getString("nome"),
                        rs.getString("apelido1"),
                        rs.getString("apelido2"),
                        rs.getString("direccion"),
                        rs.getString("movil"),
                        rs.getString("email"),
                        rs.getDate("data_nacemento"),
                        rs.getString("nomeXimnasio"),
                        TipoUsuarioGym.valueOf(rs.getString("tipo_usuario"))
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error en buscarUsuariosAdmin: " + e.getMessage());
        } finally {
            cerrar(rs);
            cerrar(stm);
        }

        return resultado;
    }
   
    //obtemos os datos especificos dun traballador (soldo, cargo, etc).
    //neste caso cruzamos todas as taboas con varios LEFT JOIN á vez.
    //coma non sabemos de anteman se é monitor, admin ou de limpeza, os left join traen os datos correctos se existen, ou enchen con null os campos do resto de especialidades
    public TraballadorGym obterTraballadorPorDni(String dni) { 
        Connection con = this.getConexion();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = con.prepareStatement(
                "SELECT u.dni, u.password_hash, u.nome, u.apelido1, u.apelido2, u.direccion, u.movil, u.email, u.data_nacemento, u.nomeximnasio, " +
                "t.soldo, t.horario, t.tipo_traballador, a.cargo, m.especialidade " +
                "FROM USUARIO u " +
                "JOIN TRABALLADOR t ON t.dni = u.dni " +
                "LEFT JOIN ADMINISTRADOR a ON a.dni = t.dni " +
                "LEFT JOIN MONITOR m ON m.dni = t.dni " +
                "WHERE u.dni = ?"
            );
            stm.setString(1, dni);
            rs = stm.executeQuery();

            if (!rs.next()) return null;

            TipoTraballadorGym tipoTrab = TipoTraballadorGym.valueOf(rs.getString("tipo_traballador"));
            String cargo = rs.getString("cargo");
            String esp = rs.getString("especialidade");

            return new TraballadorGym(
                rs.getString("dni"),
                rs.getString("password_hash"),
                rs.getString("nome"),
                rs.getString("apelido1"),
                rs.getString("apelido2"),
                rs.getString("direccion"),
                rs.getString("movil"),
                rs.getString("email"),
                rs.getDate("data_nacemento"),
                rs.getString("nomeximnasio"),
                TipoUsuarioGym.TRABALLADOR,
                rs.getBigDecimal("soldo"),
                rs.getString("horario"),
                tipoTrab,
                cargo,
                esp
            );

        } catch (SQLException e) {
            System.out.println("obterTraballadorPorDni FAILED: " + e.getMessage());
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            cerrar(stm);
        }
    }

    //actualiza só os atributos compartidos que estan na taboa xeral de USUARIO
    public boolean modificarUsuarioPorDni(UsuarioGym u) { 
        Connection con = this.getConexion();
        PreparedStatement stmLock = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean oldAutoCommit = true;

        try {
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);

            //bloqueamos a fila exacta deste usuario con FOR UPDATE antes de editalo.
            //isto asegurase de que ninguén poida borralo ou modificalo noutra ventá mentres o actualizamos
            stmLock = con.prepareStatement("SELECT dni FROM USUARIO WHERE dni=? FOR UPDATE");
            stmLock.setString(1, u.getDni());
            rs = stmLock.executeQuery();
            if (!rs.next()) {
                con.rollback();
                return false;
            }

            stm = con.prepareStatement(
                "UPDATE USUARIO SET nome=?, apelido1=?, apelido2=?, direccion=?, movil=?, email=?, data_nacemento=?, nomeximnasio=? " +
                "WHERE dni=?"
            );
            stm.setString(1, u.getNome());
            stm.setString(2, u.getApelido1());
            stm.setString(3, u.getApelido2());
            stm.setString(4, u.getDireccion());
            stm.setString(5, u.getMovil());
            stm.setString(6, u.getEmail());
            stm.setDate(7, u.getDataNacemento());
            stm.setString(8, u.getNomeXimnasio());
            stm.setString(9, u.getDni());

            int updated = stm.executeUpdate();
            con.commit();

            return updated == 1;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println("modificarUsuarioPorDni FAILED: " + e.getMessage());
            return false;

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            cerrar(stm);
            cerrar(stmLock);
            restaurarAutoCommit(con, oldAutoCommit);
        }
    }

    //actualizacion dos atributos especificos dun traballador
    public boolean modificarTraballadorPorDni(TraballadorGym t) { 
        Connection con = this.getConexion();
        PreparedStatement stmLock = null;
        PreparedStatement stmT = null;
        ResultSet rs = null;
        boolean oldAutoCommit = true;

        try {
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);

            //bloqueo pesimista antes de modificar nada
            stmLock = con.prepareStatement("SELECT dni FROM TRABALLADOR WHERE dni=? FOR UPDATE");
            stmLock.setString(1, t.getDni());
            rs = stmLock.executeQuery();
            if (!rs.next()) {
                con.rollback();
                return false;
            }

            stmT = con.prepareStatement(
                "UPDATE TRABALLADOR SET soldo=?, horario=?, tipo_traballador=?::tipo_traballador_enum WHERE dni=?"
            );
            stmT.setBigDecimal(1, t.getSoldo());
            stmT.setString(2, t.getHorario());
            stmT.setString(3, t.getTipoTraballador().name());
            stmT.setString(4, t.getDni());
            stmT.executeUpdate();

            //como ao editar un traballador pódelle cambiar o seu rol (de monitor a admin, por exemplo), facer updates condicionais é un lío e pode dar moitos erros de integridade.
            //a estratexia máis limpa e consistente é borrar as súas asignacións antigas das tres taboas e logo facer un insert desde cero na súa nova taboa correspondente.
            //como estamos nunha transacción con bloqueos ninguén vai poder ver o usuario sen rol nese pequeno instante
            PreparedStatement delA = con.prepareStatement("DELETE FROM ADMINISTRADOR WHERE dni=?");
            PreparedStatement delM = con.prepareStatement("DELETE FROM MONITOR WHERE dni=?");
            PreparedStatement delL = con.prepareStatement("DELETE FROM LIMPEZA WHERE dni=?");
            delA.setString(1, t.getDni()); delA.executeUpdate();
            delM.setString(1, t.getDni()); delM.executeUpdate();
            delL.setString(1, t.getDni()); delL.executeUpdate();
            delA.close(); delM.close(); delL.close();

            switch (t.getTipoTraballador()) {
                case ADMINISTRADOR -> {
                    PreparedStatement ins = con.prepareStatement("INSERT INTO ADMINISTRADOR (dni, cargo) VALUES (?, ?)");
                    ins.setString(1, t.getDni());
                    ins.setString(2, t.getCargo());
                    ins.executeUpdate();
                    ins.close();
                }
                case MONITOR -> {
                    PreparedStatement ins = con.prepareStatement("INSERT INTO MONITOR (dni, especialidade) VALUES (?, ?)");
                    ins.setString(1, t.getDni());
                    ins.setString(2, t.getEspecialidade());
                    ins.executeUpdate();
                    ins.close();
                }
                case LIMPEZA -> {
                    PreparedStatement ins = con.prepareStatement("INSERT INTO LIMPEZA (dni) VALUES (?)");
                    ins.setString(1, t.getDni());
                    ins.executeUpdate();
                    ins.close();
                }
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println("modificarTraballadorPorDni FAILED: " + e.getMessage());
            return false;

        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            cerrar(stmT);
            cerrar(stmLock);
            restaurarAutoCommit(con, oldAutoCommit);
        }
    }
        
    //damos de baixa definitiva a calquera tipo de usuario polo seu dni. 
    public void baixaUsuario(String dni) { 
        Connection con = this.getConexion();
        PreparedStatement stmLock = null;
        PreparedStatement stmDeleteUsuario = null;
        ResultSet rs = null;
        boolean oldAutoCommit = true;

        try {
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            stmLock = con.prepareStatement(
                    "SELECT dni FROM USUARIO WHERE dni = ? FOR UPDATE"
            );
            stmLock.setString(1, dni);
            rs = stmLock.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Non existe o usuario");
            }

            //lanzamos un unico delete na supertaboa USUARIO.
            //como no noso script SQL configuramos as foreign keys con políticas ON DELETE CASCADE, 
            //o xestor de base de datos encargase de borrar as facturas, reservas, o cliente e as subtaboas asociadas automaticamente
            stmDeleteUsuario = con.prepareStatement("DELETE FROM USUARIO WHERE dni = ?");
            stmDeleteUsuario.setString(1, dni);

            if (stmDeleteUsuario.executeUpdate() != 1) {
                throw new SQLException("Non se puido borrar o usuario");
            }

            con.commit();
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error en baixaUsuario: " + e.getMessage());
        } finally {
            cerrar(rs);
            cerrar(stmDeleteUsuario);
            cerrar(stmLock);
            restaurarAutoCommit(con, oldAutoCommit);
        }
    }

    
    //modifica o estado dun cliente (se pasa de estar activo a unha baixa temporal por mantemento)
    public boolean modificarMembresia(String dniCliente, aplicacion.EstadoMembresia novoEstado) { 
        Connection con = this.getConexion();
        PreparedStatement stmLock = null;
        PreparedStatement stmUpdate = null;
        boolean oldAutoCommit = true;

        try {
            oldAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            stmLock = con.prepareStatement(
                "SELECT estado_membresia FROM CLIENTE WHERE dni = ? FOR UPDATE"
            );
            stmLock.setString(1, dniCliente);
            java.sql.ResultSet rs = stmLock.executeQuery();

            if (!rs.next()) {
                con.rollback();
                return false; 
            }

            stmUpdate = con.prepareStatement(
                "UPDATE CLIENTE SET estado_membresia = ?::estado_membresia_enum WHERE dni = ?"
            );
            stmUpdate.setString(1, novoEstado.name());
            stmUpdate.setString(2, dniCliente);

            int updated = stmUpdate.executeUpdate();
            con.commit();

            return updated == 1;

        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.out.println("modificarMembresia FAILED: " + e.getMessage());
            return false;

        } finally {
            cerrar(stmUpdate);
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
    
    //metodo para o login da aplicacion
    public String autenticarUsuario(String usuario, String contrasena) { 
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //obtemos da base de datos o contrasinal encriptado e o seu rol de usuario
            stm = this.getConexion().prepareStatement(
                "SELECT password_hash, tipo_usuario FROM USUARIO WHERE dni = ?"
            );
            stm.setString(1, usuario);
            rs = stm.executeQuery();

            if (rs.next()) {
                String passwordBD = rs.getString("password_hash");
                
                //agora collemos o contrasinal en texto plano que puxo na ventá do login e calculamos o seu hash 
                String contrasenaHash = aplicacion.UtilidadesSeguridade.sha256(contrasena);

                //se o que meteu produce o mesmo hash que o gardado enton é correcto
                //devolvemos o tipo de usuario para que a interface saiba que pantalla lle toca ver (admin ou normal)
                if (passwordBD.equals(contrasenaHash)) {
                    return rs.getString("tipo_usuario");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error autenticación: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stm != null) stm.close(); } catch (Exception e) {}
        }
        
        return null; 
    }
}