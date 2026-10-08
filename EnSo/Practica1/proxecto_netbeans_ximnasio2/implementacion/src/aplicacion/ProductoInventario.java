package aplicacion;

import java.math.BigDecimal;

//clase que representa calquera produto ou material do ximnasio
public class ProductoInventario {
    
    //atributos xerais compartidos por todos os materiais
    private String codigoIdentificador;
    private String nome;
    private EstadoMaterial estado;
    private Integer idSala;
    private TipoMaterial tipoMaterial;

    //atributos especificos que dependen do subtipo de material.
    //algúns poden ir a null dependendo de se o material é unha máquina, saco, etc.
    private String subtipo;
    private BigDecimal peso;
    private String materialFabrica;

    //constructor con todos os datos posibles
    public ProductoInventario(String codigoIdentificador, String nome, EstadoMaterial estado,
                              Integer idSala, TipoMaterial tipoMaterial,
                              String subtipo, BigDecimal peso, String materialFabrica) {
        this.codigoIdentificador = codigoIdentificador;
        this.nome = nome;
        this.estado = estado;
        this.idSala = idSala;
        this.tipoMaterial = tipoMaterial;
        this.subtipo = subtipo;
        this.peso = peso;
        this.materialFabrica = materialFabrica;
    }

    //getters básicos
    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public String getNome() {
        return nome;
    }

    public EstadoMaterial getEstado() {
        return estado;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public String getMaterialFabrica() {
        return materialFabrica;
    }
}