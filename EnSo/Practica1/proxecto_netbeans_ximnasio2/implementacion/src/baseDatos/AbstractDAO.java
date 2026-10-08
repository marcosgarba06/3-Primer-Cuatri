package baseDatos;

// Cambiado public por protected y ajustado el nombre de la variable 
// de la fachada a fa para que sea igual que la biblioteca de ejemplo
public abstract class AbstractDAO {
   private java.sql.Connection conexion;
   private aplicacion.FachadaAplicacion fa;

    protected java.sql.Connection getConexion(){
        return this.conexion;
    }
    
    protected void setConexion(java.sql.Connection conexion){
        this.conexion=conexion;
    }
   
   protected void setFachadaAplicacion(aplicacion.FachadaAplicacion fa){
       this.fa=fa;
   }
   
   protected aplicacion.FachadaAplicacion getFachadaAplicacion(){
       return fa;
   }
}