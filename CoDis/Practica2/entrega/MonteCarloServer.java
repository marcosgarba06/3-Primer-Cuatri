// CODIGO DE MARCOS GARCÍA BLANCO
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;



public class MonteCarloServer {
    public static void main(String args[]) {

        // El servidor requiere que se le pase dos argumentos: el puerto y la dirección IP (opcional).
        // Esto permite lanzar múltiples servidores en la misma máquina usando puertos diferentes
        if  (args.length > 2 || args.length < 1)  { //c
            System.out.println("Uso: java MonteCarloServer <puerto> [ip]");
            System.exit(1);
        }

        String urlRegistro;
        try{
            int puerto = Integer.parseInt(args[0].trim()); // convertir el argumento en un int y quitar \n
            String ip;
            if (args.length == 2) {
                ip = args[1].trim();
            }else{
                ip = "localhost";
            }
            System.setProperty("java.rmi.server.hostname", ip); // se indica cual es la ip para que los clientes se puedan conectar
            iniciarRegistro(puerto); // inicia el registro rmi pasandole el puerto, si ya existe no hace nada, si no existe lo crea

            // se crea la instancia de la implementación. Al heredar de UnicastRemoteObject ya queda listo
            // para recibir llamadas de red
            MonteCarloImpl exportedObj = new MonteCarloImpl();

            urlRegistro = "rmi://" + ip + ":" + puerto + "/montecarlo"; // se crea la url del registro rmi con el puerto y la ip

            // se registra el obj remoto con un nombre (la url) en el regirstro rmi
            Naming.rebind(urlRegistro, exportedObj);

            System.out.println("Objeto publicado como rmi en: " + urlRegistro);

            // lista nombres actualmente en el registro rmi
            listarRegistro(urlRegistro, puerto);
            //System.out.println("MonteCarloServer listo en el puerto " + puerto);
        }
        catch (Exception re) { // captura cualquier error y la imprime
            System.out.println("Excepción en MonteCarloServer.main: " + re);
        }
    }

    // Arranca un registro RMI en la maquina local si todavía no existe en el puerto indicado.
    private static void iniciarRegistro(int puerto) throws RemoteException {
        try {
            // intenta conectarse a un registro RMI que ya este corriendo en el puerto especificado
            Registry registro = LocateRegistry.getRegistry(puerto);
            registro.list();   // lanza excepción si el registro no existe
        }
        catch (RemoteException e) {
            // No hay un registro válido en ese puerto
            //System.out.println("No se encuentra un registro RMI en el puerto " + puerto);

            // Si la excepción saltó, se crea el registro RMI desde el código
            Registry registro = LocateRegistry.createRegistry(puerto);
            //System.out.println("Registro RMI creado en el puerto " + puerto);
        }
    }

    // Muestra los nombres que hay registrados en el registro
    private static void listarRegistro(String urlRegistro, int puerto) throws RemoteException, MalformedURLException {
        System.out.println("Servicios en el registro del puerto " + puerto + ":");
        String[] nombres = Naming.list(urlRegistro); // array de Strings con todos los servicios en el rmiregistry

        for(int i = 0; i < nombres.length; i++) // recorre el array y los imprime
            System.out.println("    - " + nombres[i]);
    }
}