package aplicacion;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

//clase auxiliar para encriptar os contrasinais antes de mandalos á base de datos
public class UtilidadesSeguridade {

    public static String sha256(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(texto.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            
            //pasamos o array de bytes a formato hexadecimal para que quede como un texto lexible
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (Exception e) {
            throw new RuntimeException("Error calculando hash SHA-256", e);
        }
    }
}