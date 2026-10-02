import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class HReceptor implements Runnable { // implementa runnable para que pueda ser ejecutado en un hilo
    public static final int tamañoBuffer = 1024; // tamaño del buffer de recepción

    private MulticastSocket socket; // socket multicast para recibir mensajes
    private String nickname; // nombre de usuario del receptor

    // constructor
    public HReceptor(MulticastSocket socket, String nickname) {
        this.socket = socket;
        this.nickname = nickname;
    }

    // codigo del hilo que escucha de forma ininterrumpida
    @Override
    public void run() {
        while (true) {
            DatagramPacket paquete;
            paquete = new DatagramPacket(new byte[tamañoBuffer], tamañoBuffer);
            try {
                socket.receive(paquete); // se recibe el paquete y es bloqueante hasta que llegue un paquete
            }catch (IOException e) {
                System.out.println("Error de red: " + e.getMessage());
                break;
            }

            //se obtiene el mensaje del paquete y se decodifica a string
            String msjRec = new String(paquete.getData(), 0, paquete.getLength(), StandardCharsets.UTF_8);
            System.out.println(msjRec);
        }
    }

    // método que lanza el hilo receptor
    public void lanzarHiloRec() {
        Thread hiloReceptor = new Thread(this); // se crea un hilo con el objeto actual
        hiloReceptor.setDaemon(true); // se establece como hilo daemon para que no bloquee la salida del programa
        hiloReceptor.start(); // se inicia el hilo
    }
}