// CODIGO DE MARCOS GARCÍA BLANCO
import java.io.*;
import java.rmi.*;


public class MonteCarloClient {
    public static void main(String[] args) {

        if (args.length < 3) { // minimo se debe poder pasar el numero N, el tam de bloque y una ip y un puerto (opcionalmente mas, para trabajar con varios servidores)
            System.out.println("Uso: java MonteCarloClient <N> <tamBloque> <ip1:puerto1> [ip2:puerto2] ... [ipN:puertoN]"); // <N> <ip> <puerto> [<ip> <puerto>] ...
            System.exit(1);
        }


        int numeroArgs = args.length;
        int numeroSv = numeroArgs - 2; // los 2 primeros args son N y el tamaño del bloque

        try {

            long numPts = Long.parseLong(args[0].trim()); // el primer argumento es el número de puntos a calcular (debe ser positivo)
            if (numPts <= 0) {
                System.out.println("El número de puntos tiene que ser positivo");
                System.exit(1);
            }

            long tamBloque = Long.parseLong(args[1].trim()); // el segundo argumento es el tamaño del bloque (debe ser positivo)
            if (tamBloque <= 0) {
                System.out.println("El tamaño del bloque tiene que ser positivo");
                System.exit(1);
            }
            
            MonteCarloInterface[] servidores = new MonteCarloInterface[numeroSv];
            String ipPuerto, urlReg;
            int numSvEncontrados = 0; // numero de servidores encontrados
            String[] direccionesEncontradas = new String[numeroSv]; // par ip:puerto de los servidores encontrados

            for (int i = 0; i < numeroSv; i++) {
                ipPuerto = args[i + 2].trim(); // se obtiene el argumento que contiene la ip y el puerto
                urlReg = "rmi://" + ipPuerto + "/montecarlo"; // se crea la url del registro rmi con el puerto y la ip
                try{
                    servidores[numSvEncontrados] = (MonteCarloInterface) Naming.lookup(urlReg); // se busca el objeto remoto en el registro rmi y se guarda en el array de servidores
                    direccionesEncontradas[numSvEncontrados] = ipPuerto;
                    numSvEncontrados++;

                }catch (Exception e){
                    System.out.println("Error al buscar el servidor " + urlReg + ": " + e);
                }
            }

            if (numSvEncontrados == 0) { // se comprueba si hay algun servidor disponible
                System.out.println("No se encontraron servidores disponibles");
                System.exit(1);
            } else{ // si hay servidores disponibles se muestran por pantalla
                System.out.println("Se encontraron " + numSvEncontrados + " servidores disponibles. Son:");
                for (int i = 0; i < numSvEncontrados; i++) {
                      System.out.println("    Servidor " + (i + 1) + ": " + direccionesEncontradas[i]);
                 }
            }

            HiloCliente.iniciarReparto(numPts, tamBloque); // se inicializa el contador de puntos totales y pendientes para los hilos
            //long[] cuotas = repartirPuntos(numPts, numSvEncontrados); // se reparten los puntos entre los servidores disponibles

            Thread[] hilos = new Thread[numSvEncontrados]; // array de hilos que se van a ejecutar
            HiloCliente[] tareas = new HiloCliente[numSvEncontrados]; // array de tareas que se van a ejecutar en los hilos

            long tiempoInicio, tiempoFin, aciertos = 0;
            tiempoInicio = System.nanoTime(); // arranca el cronometro para medir el tiempo total de la ejecución

            // bucle que crea los hilos y les asigna la tarea
            for (int i = 0; i < numSvEncontrados; i++) {
                tareas[i] = new HiloCliente(servidores[i]); // se crea la tarea con el servidor (los bloques los pide el propio hilo)
                hilos[i] = new Thread(tareas[i]); // se crea el hilo con la tarea
                hilos[i].start(); // se inicia
            }

            //otro buce que hace join de cada hilo para esperar a que acaben su trabajo
            for (int i = 0; i < numSvEncontrados; i++) {
                hilos[i].join(); // se espera a que acabe el hilo
                aciertos += tareas[i].getRes(); // se suman los aciertos de cada hilo
            }
            tiempoFin = System.nanoTime(); // se detiene el cronometro
            double resultado = (double) aciertos / numPts; // se calcula el resultado final

            // Impresiones finales por pantalla
            System.out.println("Puntos totales:    " + numPts);
            System.out.println("Puntos bajo curva: " + aciertos);
            System.out.println("Integral aprox.:   " + resultado);
            System.out.println("Tiempo (ns): " + (tiempoFin - tiempoInicio));
            System.out.println("Tiempo (s): " + (tiempoFin - tiempoInicio) / 1e9);

        } catch (Exception e) {
            System.out.println("Excepción en MonteCarloClient: " + e);
        }
    }

    /*
    METODO YA NO USADO (SE USABA CUANDO NO HABIA REPARTO DINAMICO)
    // metodo que reparte los puntos entre los servidores disponibles, devolviendo un array con el número de puntos que le toca a cada servidor
    // static porque no hace falta crear un obj para usarlo, se le pasa el numero de puntos y el numero de servidores
    private static long[] repartirPuntos(long numPts, int numSv) {
        long[] tramos = new long[numSv]; // array que contiene el número de puntos que le toca a cada servidor
        long cociente = numPts / numSv; // se calcula el número de puntos que le toca a cada servidor
        long resto = numPts % numSv; // se calcula el resto de puntos para repartir entre los servidores
        for (int i = 0; i < numSv; i++) {
            tramos[i] = cociente; // se asigna el número de puntos que le toca a cada servidor
            if (i < resto) { // si hay resto, se reparte entre los primeros servidores
                tramos[i]++;
            }
        }
        return tramos;
    }*/

}//end class
