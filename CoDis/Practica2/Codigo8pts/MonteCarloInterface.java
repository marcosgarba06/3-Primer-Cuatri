import java.rmi.*;

// Interface del método de montecarlo que se implementará en el servidor y se llamará desde el cliente
public interface MonteCarloInterface extends Remote {
    /**
    * Este método calcula el num de puntos bajo una curva.
    * @param  puntos - un long que contiene el número de puntos a calcular
    * @return el numero de puntos que caen debajo de la curva.
    */
    // trows java.rmi.RemoteException para que el cliente pueda manejar la excepción si el servidor no está disponible
    public long calcularPuntosBajoCurva(long puntos) throws java.rmi.RemoteException;

} //end interface
