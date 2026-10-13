import java.rmi.*;

/**
 * Tarea que ejecuta un hilo del cliente: hace una única llamada RMI
 * a un servidor y guarda el resultado para que el cliente principal lo lea.
 */

public class HiloCliente implements Runnable {
    private MonteCarloInterface sv;
    private long pts;                  // puntos que le tocan a este servidor

    private long res;               // puntos bajo la curva devueltos por el servidor
    private boolean error;                // true si la llamada remota falló
    private long tiempoNs;                // tiempo que tardó esta llamada concreta

    // constructor
    public HiloCliente(MonteCarloInterface sv, long pts) {
        this.sv = sv;
        this.pts = pts;
        this.res = 0;
        this.error = false;
    }
    // getters
    public long getRes() {
        return res;
    }

    public boolean getError()  {
        return error;
    }
    public long getTiempoNs()  {
        return tiempoNs;
    }
    // metodo que ejecuta el hilo cuando se llama a start()
    public void run() {
        long inicio;
        inicio = System.nanoTime();
        try{
            res = sv.calcularPuntosBajoCurva(pts); // llamada remota al servidor para calcular los puntos bajo la curva
        }catch (RemoteException e) {
            error = true; // si hay un error en la llamada remota, se marca el error
            System.out.println("Error al calcular puntos bajo curva: ñ" + e);
        }
        // calcurar tiempo que tardo la llamada remota
        long fin = System.nanoTime();
        tiempoNs = fin - inicio;
    }
}