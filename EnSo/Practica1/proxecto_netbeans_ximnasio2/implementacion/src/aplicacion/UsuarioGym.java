package aplicacion;

import java.sql.Date;

public class UsuarioGym {
    private String dni;
    private String passwordHash;
    private String nome;
    private String apelido1;
    private String apelido2;
    private String direccion;
    private String movil;
    private String email;
    private Date dataNacemento;
    private String nomeXimnasio;
    private TipoUsuarioGym tipoUsuario;

    public UsuarioGym(String dni, String passwordHash, String nome, String apelido1, String apelido2,
                      String direccion, String movil, String email, Date dataNacemento,
                      String nomeXimnasio, TipoUsuarioGym tipoUsuario) {
        this.dni = dni;
        this.passwordHash = passwordHash;
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.direccion = direccion;
        this.movil = movil;
        this.email = email;
        this.dataNacemento = dataNacemento;
        this.nomeXimnasio = nomeXimnasio;
        this.tipoUsuario = tipoUsuario;
    }

    public String getDni() { return dni; }
    public String getPasswordHash() { return passwordHash; }
    public String getNome() { return nome; }
    public String getApelido1() { return apelido1; }
    public String getApelido2() { return apelido2; }
    public String getDireccion() { return direccion; }
    public String getMovil() { return movil; }
    public String getEmail() { return email; }
    public Date getDataNacemento() { return dataNacemento; }
    public String getNomeXimnasio() { return nomeXimnasio; }
    public TipoUsuarioGym getTipoUsuario() { return tipoUsuario; }

    public void setNome(String nome) { this.nome = nome; }
    public void setApelido1(String apelido1) { this.apelido1 = apelido1; }
    public void setApelido2(String apelido2) { this.apelido2 = apelido2; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setMovil(String movil) { this.movil = movil; }
    public void setEmail(String email) { this.email = email; }
    public void setNomeXimnasio(String nomeXimnasio) { this.nomeXimnasio = nomeXimnasio; }
}