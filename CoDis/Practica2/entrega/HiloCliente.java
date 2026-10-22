// CODIGO DE MARCOS GARCÍA BLANCO
import java.rmi.*;

/**
 * Tarea que ejecuta un hilo del cliente: va pidiendo bloques de puntos y hace
 * una llamada RMI a su servidor por cada bloque, hasta que no queda trabajo.
 * Acumula el resultado para que el cliente principal lo lea.
 */

public class HiloCliente implements Runnable {
    private final MonteCarloInterface sv;

    // atributos estáticos compartidos por todos los hilos (todos tienen los mismos pendientes y tamBloque)
    private static long pendientes; // puntos que todavía no se han repartido
    private static long tamBloque;  // tamaño de cada bloque

    private long res; // puntos bajo la curva acumulados por este hilo

    // constructor
    public HiloCliente(MonteCarloInterface sv) {
        this.sv = sv;
        this.res = 0;
    }
    // getters
    public long getRes() {
        return res;
    }

    // lo llama el main una vez antes de lanzar los hilos para dejar listo el trabajo total y el tamaño del bloque
    public static void iniciarReparto(long total, long bloque) {
        pendientes = total;
        tamBloque = bloque;
    }

    // es static synchronized porque si fuera un synchronized normal el cerrojo seria this,
    // y como cada hilo tiene su propio obj HiloCliente cada uno cerraria el suyo y no se excluirían
    // al ser static el cerrojo es la clase
    private static synchronized long siguienteBloque() {
        // minimo entre el tamaño del bloque y los puntos pendientes (el ultimno bloque puede ser más pequeño)
        long bloque = Math.min(tamBloque, pendientes);
        pendientes -= bloque; // se descuentan los puntos que se van a repartir de pendientes
        return bloque;
    }

    // metodo que ejecuta el hilo cuando se llama a start()
    public void run() {
        long bloquePts = siguienteBloque(); // se obtiene el primer bloque de puntos a calcular
        while (bloquePts > 0) { // hasta que no queden puntos
            try {
                // llamada remota con un bloque para calcular puntos y se acumula el resultado
                res += sv.calcularPuntosBajoCurva(bloquePts);
            } catch (RemoteException e) {
                System.out.println("Error al calcular puntos bajo curva: " + e);
                return; // si el servidor falla, este hilo deja de pedir bloques
            }
            bloquePts = siguienteBloque(); // se obtiene el siguiente bloque de puntos a calcular
        }
    }
}