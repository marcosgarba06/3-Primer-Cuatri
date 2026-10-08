package baseDatos;

import aplicacion.EstadoMaterial;
import aplicacion.EstadoMembresia;
import aplicacion.ProductoInventario;
import aplicacion.SalaLibre;
import aplicacion.TraballadorGym;
import aplicacion.TraballadorLimpiezaLibre;
import aplicacion.UsuarioGym;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

//clase que serve de ponte entre a aplicacion e a base de datos instanciando a conexion e os tres DAOs
public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOUsuariosGym daoUsuariosGym;
    private DAOInventario daoInventario;
    private DAOLimpieza daoLimpieza;

    public FachadaBaseDatos(aplicacion.FachadaAplicacion fa) {
        this.fa = fa;
        Properties configuracion = new Properties();
        FileInputStream arqConfiguracion;

        try {
            //lemos o ficheiro de propiedades externo para non ter as credenciais do servidor incrustadas directamente no codigo compilado
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));

            //establecemos a conexion jdbc montando a url ca ip e o porto que acabamos de sacar do properties
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://" +
                    configuracion.getProperty("servidor") + ":" +
                    configuracion.getProperty("puerto") + "/" +
                    configuracion.getProperty("baseDatos"),
                    usuario);

            //unha vez temos a conexion lista instanciamos os tres daos pasándolle a mesma conexion para que a compartan
            this.daoUsuariosGym = new DAOUsuariosGym(conexion, fa);
            this.daoInventario = new DAOInventario(conexion, fa);
            this.daoLimpieza = new DAOLimpieza(conexion, fa);

        } catch (FileNotFoundException f) {
            //se non atopa o properties ou non pode conectar capturamos o erro e avisamos á interface grafica
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
            throw new IllegalStateException("No se pudo cargar la configuración de la base de datos.", f);
        } catch (IOException i) {
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
            throw new IllegalStateException("Error leyendo la configuración de la base de datos.", i);
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
            System.out.println("ERROR");
            System.out.flush();
            throw new IllegalStateException("No se pudo inicializar la conexión con la base de datos.", e);
        }
    }

    //a partir de aqui son todo metodos de delegacion que simplemente chaman á funcion correspondente do seu DAO específico
    
    public boolean altaUsuarioClienteConFactura(UsuarioGym u, java.math.BigDecimal importe, String metodoPago) {
        return daoUsuariosGym.altaUsuarioClienteConFactura(u, importe, metodoPago);
    }
    
    public boolean altaUsuarioTraballador(TraballadorGym t) {
        return daoUsuariosGym.altaUsuarioTraballador(t);
    }

    public TraballadorGym obterTraballadorPorDni(String dni) {
        return daoUsuariosGym.obterTraballadorPorDni(dni);
    }

    public boolean modificarUsuarioPorDni(UsuarioGym u) {
        return daoUsuariosGym.modificarUsuarioPorDni(u);
    }

    public boolean modificarTraballadorPorDni(TraballadorGym t) {
        return daoUsuariosGym.modificarTraballadorPorDni(t);
    }
    
    public List<UsuarioGym> buscarUsuariosAdmin(String dni, String nome) {
        return daoUsuariosGym.buscarUsuariosAdmin(dni, nome);
    }

    public void baixaUsuario(String dni) {
        daoUsuariosGym.baixaUsuario(dni);
    }

    public boolean modificarMembresia(String dniCliente, aplicacion.EstadoMembresia novoEstado) {
        return daoUsuariosGym.modificarMembresia(dniCliente, novoEstado);
    }

    public void altaProducto(ProductoInventario p) {
        daoInventario.altaProducto(p);
    }

    public List<ProductoInventario> consultarProductos() {
        return daoInventario.consultarProductos();
    }

    public void actualizarEstadoProducto(String codigoProducto, EstadoMaterial novoEstado) {
        daoInventario.actualizarEstadoProducto(codigoProducto, novoEstado);
    }

    public void baixaProducto(String codigoProducto) {
        daoInventario.baixaProducto(codigoProducto);
    }

    public List<SalaLibre> consultarSalas() {
        return daoLimpieza.consultarSalas();
    }

    public List<TraballadorLimpiezaLibre> consultarTraballadoresLimpiezaLibres() {
        return daoLimpieza.consultarTraballadoresLimpiezaLibres();
    }

   public boolean asignarLimpiezaASala(String dniTraballador, Integer idSala) {
        return daoLimpieza.asignarLimpiezaASala(dniTraballador, idSala);
    }

    public Connection getConexion() {
        return conexion;
    }
   
    //metodo chamado desde a ventá de login principal para verificar as credenciais antes de entrar
    public String autenticarUsuario(String usuario, String contrasena) {
        return daoUsuariosGym.autenticarUsuario(usuario, contrasena);
    }
    
    public List<Integer> consultarIdSalas() {
        return daoInventario.consultarIdSalas();
    }
}