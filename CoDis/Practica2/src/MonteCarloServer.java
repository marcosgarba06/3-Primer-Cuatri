import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;



public class MonteCarloServer {
    public static void main(String args[]) {

        if  (args.length != 1)  { //c
            System.out.println("Uso: java MonteCarloServer <puerto>");
            System.exit(1);
        }

        String urlRegistro;
        try{
            int puerto = Integer.parseInt(args[0].trim());
            iniciarRegistro(puerto);

            MonteCarloImpl exportedObj = new MonteCarloImpl();

            urlRegistro = "rmi://localhost:" + puerto + "/montecarlo";
            Naming.rebind(urlRegistro, exportedObj);

            System.out.println("Servidor registrado.  Registro actual contiene:");
            // list names currently in the registry
            listarRegistro(urlRegistro);
            System.out.println("MonteCarloServer listo en el puerto " + puerto);
        }
        catch (Exception re) {
            System.out.println("Excepción en MonteCarloServer.main: " + re);
        }
    }

    // Arranca un registro RMI en la maquina local si todavía no existe en el método indicado.
    private static void iniciarRegistro(int puerto) throws RemoteException {
        try {
            Registry registro = LocateRegistry.getRegistry(puerto);
            registro.list();   // lanza excepción si el registro no existe
        }
        catch (RemoteException e) {
            // No hay un registro válido en ese puerto
            System.out.println("No se encuentra un registro RMI en el puerto "
                    + puerto);
            Registry registro = LocateRegistry.createRegistry(puerto);
            System.out.println("Registro RMI creado en el puerto " + puerto);
        }
    } // fin iniciarRegistro

    // Muestra los nombres que hay registrados en el registro
    private static void listarRegistro(String urlRegistro) throws RemoteException, MalformedURLException {
        System.out.println("El registro " + urlRegistro + " contiene: ");
        String[] nombres = Naming.list(urlRegistro);

        for (int i = 0; i < nombres.length; i++)
            System.out.println(nombres[i]);
    }
}