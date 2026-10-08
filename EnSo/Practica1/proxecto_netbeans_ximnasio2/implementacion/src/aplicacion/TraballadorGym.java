package aplicacion;

import java.math.BigDecimal;
import java.sql.Date;

//clase que herda de UsuarioGym para representar aos empregados do centro
public class TraballadorGym extends UsuarioGym {
    
    //atributos especificos dos traballadores.
    //cargo e especialidade poden ir a null dependendo do rol (limpeza non os usa, por exemplo)
    private BigDecimal soldo;
    private String horario;
    private TipoTraballadorGym tipoTraballador;
    private String cargo;
    private String especialidade;

    public TraballadorGym(String dni, String passwordHash, String nome, String apelido1, String apelido2,
                          String direccion, String movil, String email, Date dataNacemento,
                          String nomeXimnasio, TipoUsuarioGym tipoUsuario,
                          BigDecimal soldo, String horario, TipoTraballadorGym tipoTraballador,
                          String cargo, String especialidade) {
        
        //chamamos ao construtor da clase pai cos datos xerais do usuario
        super(dni, passwordHash, nome, apelido1, apelido2, direccion, movil, email, dataNacemento, nomeXimnasio, tipoUsuario);
        
        this.soldo = soldo;
        this.horario = horario;
        this.tipoTraballador = tipoTraballador;
        this.cargo = cargo;
        this.especialidade = especialidade;
    }

    //getters
    public BigDecimal getSoldo() { return soldo; }
    public String getHorario() { return horario; }
    public TipoTraballadorGym getTipoTraballador() { return tipoTraballador; }
    public String getCargo() { return cargo; }
    public String getEspecialidade() { return especialidade; }
}