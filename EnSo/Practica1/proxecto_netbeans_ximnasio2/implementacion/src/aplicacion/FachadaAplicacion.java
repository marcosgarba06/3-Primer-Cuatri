package aplicacion;

import baseDatos.FachadaBaseDatos;
import java.util.List;

//clase principal da capa de aplicacion
public class FachadaAplicacion {
    private gui.FachadaGui fgui;
    private baseDatos.FachadaBaseDatos fbd;

    public FachadaAplicacion() {
        //inicializamos primeiro a GUI por se falla a base de datos.
        //asi xa temos a ventá lista para mostrar o erro
        this.fgui = new gui.FachadaGui(this); 
        
        //inicializamos a bd
        this.fbd = new FachadaBaseDatos(this);
    }

    public static void main(String args[]) {
        FachadaAplicacion fa = new FachadaAplicacion();
        fa.iniciaInterfazUsuario();
    }

    public void iniciaInterfazUsuario() {
        fgui.iniciaVista();
    }

    public void muestraExcepcion(String e) {
        System.out.println("Excepción capturada en la capa de aplicación: " + e);
        if (fgui != null) {
            fgui.muestraExcepcion(e);
        }
    }

    //metodos de delegacion cara a fachada de base de datos
    
    //usuarios
    public boolean altaUsuarioClienteConFactura(UsuarioGym u, java.math.BigDecimal importe, String metodoPago) {
        return fbd.altaUsuarioClienteConFactura(u, importe, metodoPago);
    }
    
    public boolean altaUsuarioTraballador(TraballadorGym t) {
        return fbd.altaUsuarioTraballador(t);
    }

    //administracion
    public List<UsuarioGym> buscarUsuariosAdmin(String dni, String nome) {
        return fbd.buscarUsuariosAdmin(dni, nome);
    }
    
    public TraballadorGym obterTraballadorPorDni(String dni) {
        return fbd.obterTraballadorPorDni(dni);
    }

    public boolean modificarUsuarioPorDni(UsuarioGym u) {
        return fbd.modificarUsuarioPorDni(u);
    }

    public boolean modificarTraballadorPorDni(TraballadorGym t) {
        return fbd.modificarTraballadorPorDni(t);
    }

    //baixa
    public void baixaUsuario(String dni) { 
        fbd.baixaUsuario(dni); 
    }

    //membresias    
    public boolean modificarMembresia(String dniCliente, aplicacion.EstadoMembresia novoEstado) {
        return fbd.modificarMembresia(dniCliente, novoEstado);
    }

    //inventario
    public void altaProducto(ProductoInventario p) { 
        fbd.altaProducto(p); 
    }
    
    public List<ProductoInventario> consultarProductos() { 
        return fbd.consultarProductos(); 
    }
    
    public void actualizarEstadoProducto(String codigoProducto, EstadoMaterial novoEstado) {
        fbd.actualizarEstadoProducto(codigoProducto, novoEstado);
    }
    
    public void baixaProducto(String codigoProducto) { 
        fbd.baixaProducto(codigoProducto); 
    }

    //limpeza de salas
    public List<SalaLibre> consultarSalas() {
        return fbd.consultarSalas();
    }
    
    public List<TraballadorLimpiezaLibre> consultarTraballadoresLimpiezaLibres() {
        return fbd.consultarTraballadoresLimpiezaLibres();
    }
    
    public boolean asignarLimpiezaASala(String dniTraballador, Integer idSala) {
        return fbd.asignarLimpiezaASala(dniTraballador, idSala);
    }

    public String autenticarUsuario(String usuario, String contrasena) {
        return fbd.autenticarUsuario(usuario, contrasena);
    }
    
    public List<Integer> consultarIdSalas() {
        return fbd.consultarIdSalas();
    }
}