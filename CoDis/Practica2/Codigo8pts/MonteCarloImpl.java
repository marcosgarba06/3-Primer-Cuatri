import java.rmi.*;
import java.rmi.server.*;
import java.util.Random;

public class MonteCarloImpl extends UnicastRemoteObject implements MonteCarloInterface {
  
   public MonteCarloImpl() throws RemoteException {
      super( );
   }
  
   public long calcularPuntosBajoCurva(long puntos) throws RemoteException {
       Random rand = new Random(); // crea un objeto Random para generar números aleatorios
       long cont = 0; // contador de los aciertos (pts debajo de la curva)

       for (long i = 0; i < puntos; i++) { // bucle que genera puntos aleatorios y los cuenta
           double x = rand.nextDouble(); // nextDouble() genera un número aleatorio entre 0.0 y 1.0
           double y = rand.nextDouble();

           if (y <= Math.exp(-(x * x))) { // y <= e^(-x^2)
               cont++;
           }
       }
        return cont;
   }
} // end class
