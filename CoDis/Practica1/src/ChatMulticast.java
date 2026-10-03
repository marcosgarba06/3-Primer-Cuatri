import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatMulticast {
    private static final String IPMulticast = "239.1.2.3";
    private static final int puerto = 6000;

    private final Scanner scanner = new Scanner(System.in);
    private String nickname;

    private MulticastSocket socket;
    private InetAddress grupo;

    // se inicia el chat multicast, pidiendo el nombre de usuario y creando el socket multicast
    public void iniciar(){
        pedirNickname(); // primero se pide el nombre de usuario

        try {
            crearSocketMulticast(); // se crea el socket multicast y se une al grupo
            HReceptor receptor = new HReceptor(socket, nickname); // se crea el hilo receptor
            receptor.lanzarHiloRec(); // se lanza el hilo receptor

            System.out.println("Conectado al chat como " + nickname);
            bucleEmisor(); // se inicia el bucle de emision de mensajes
        } catch (IOException e) { // se captura si hay errores y se imprime un mensaje de error
            System.err.println("Error de red: " + e.getMessage());
        }
    }

    // método que pide el nombre de usuario y lo valida
    public void pedirNickname(){
        System.out.print("Introduzca su nombre de usuario (sin :): ");
        String nombreCandidato = scanner.nextLine().trim(); //trim para eliminar espacios al inicio y al final

        // comprobar que el nombre es válido
        if (nombreCandidato.contains(Mensaje.delimitador)) {
            System.out.println("El nombre de usuario no puede contener el caracter " + Mensaje.delimitador);
            pedirNickname();
        } else if (nombreCandidato.isEmpty()) {
            System.out.println("El nombre de usuario no puede estar vacío.");
            pedirNickname();
        }else{
            nickname = nombreCandidato;
        }

    }

    // método que crea el socket multicast y se une al grupo
    private void crearSocketMulticast() throws IOException {
        try{
            socket = new MulticastSocket(puerto); // se crea el socket multicast en el puerto

        } catch (IOException e) {
            System.err.println("Error al crear el socket multicast: " + e.getMessage());
        }

        try{
            grupo = InetAddress.getByName(IPMulticast); // pasa la dirección IP multicast a un objeto de direccion
            socket.joinGroup(grupo); // se une al grupo multicast
        }catch (IOException e) {
            System.err.println("Error al unirse al grupo multicast: " + e.getMessage());
        }

    }

    private void enviarMsj(String texto) throws IOException {
        // se crea objeto mensaje
        Mensaje msj = new Mensaje(nickname, texto);
        byte[] buffer = msj.juntarMensaje().getBytes(StandardCharsets.UTF_8);

        // se crea el paquete datagrama con el buffer, la dirección del grupo y el puerto
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, puerto);
        socket.send(paquete);                   // "fuego y olvido", sin confirmacion
    }

    // bucle que lee mensajes del usuario y los envia al grupo multicast, es el HILO DE EMISION
    private void bucleEmisor() throws IOException {
        while (true) {
            String mensaje = scanner.nextLine(); // se lee el mensaje del usuario

            // no enviar mensajes vacios
            if (mensaje.isBlank()) {
                continue;
            }
            enviarMsj(mensaje);                  // se envia el mensaje
        }
    }
}
