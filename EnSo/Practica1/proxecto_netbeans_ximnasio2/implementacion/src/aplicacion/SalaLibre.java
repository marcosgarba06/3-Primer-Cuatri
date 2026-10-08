package aplicacion;

//clase auxiliar para gardar a informacion basica dunha sala.
//usase sobre todo para pasar datos das consultas á interface grafica
public class SalaLibre {
    private Integer idSala;
    private String nomeXimnasio;

    public SalaLibre(Integer idSala, String nomeXimnasio) {
        this.idSala = idSala;
        this.nomeXimnasio = nomeXimnasio;
    }

    //getters 
    public Integer getIdSala() { return idSala; }
    public String getNomeXimnasio() { return nomeXimnasio; }
}