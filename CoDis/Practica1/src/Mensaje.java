public class Mensaje {
    public static String delimitador = ":"; // se indica el caracter que separa nickname de mensaje (NICKNAME:MENSAJE)

    // variables para almacenar las strings de nickname y texto
    private final String nickname;
    private final String texto;

    // constructor de la clase Mensaje
    public Mensaje(String nickname, String texto) {
        this.nickname = nickname;
        this.texto = texto;
    }

    // getters para obtener el nickname y el texto
    public String getNickname() {
        return nickname;
    }

    public String getTexto() {
        return texto;
    }

    // metodo para juntar un mensaje de modo Nickname:Texto
    public String juntarMensaje(){
        return nickname + delimitador + texto;
    }

    // metodo para separar un mensaje en nickname y texto, lo contrario que el anterior
    // static porque no necesita una instancia de la clase Mensaje para ser llamado
    public static Mensaje separarMensaje(String textoRec){
        int posicion = textoRec.indexOf(delimitador); // se busca el primer :

        if (posicion == -1){
            // si no hay : se devuelve un mensaje con nickname vacio y el texto completo
            return new Mensaje("", textoRec);
        }else {
            // si hay : se separa el nickname y el texto
            String nickname, mensaje;
            nickname = textoRec.substring(0, posicion); // se obtiene el nickname desde el inicio hasta la posicion del :
            mensaje = textoRec.substring(posicion + delimitador.length()); // se obtiene el contenido del mensaeje desde la posicion del : hasta el final
            return new Mensaje(nickname, mensaje);
        }
    }

    // imprime por pantalle el mensaje con el formato Nickname dice: Texto
    public String imprimirMensaje(){
        return nickname + " dice: " + texto;
    }
}
