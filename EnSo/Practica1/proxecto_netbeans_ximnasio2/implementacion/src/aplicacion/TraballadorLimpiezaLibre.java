package aplicacion;

public class TraballadorLimpiezaLibre {
    private String dni;
    private String nomeCompleto;

    public TraballadorLimpiezaLibre(String dni, String nomeCompleto) {
        this.dni = dni;
        this.nomeCompleto = nomeCompleto;
    }

    public String getDni() { return dni; }
    public String getNomeCompleto() { return nomeCompleto; }
}