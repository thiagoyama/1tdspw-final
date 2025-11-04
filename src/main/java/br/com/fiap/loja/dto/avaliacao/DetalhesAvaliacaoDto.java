package br.com.fiap.loja.dto.avaliacao;

import java.time.LocalDateTime;

public class DetalhesAvaliacaoDto {

    private int codigo;

    private String descricao;

    private LocalDateTime dataCadastro;

    private double nota;

    private int codigoDoce;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getCodigoDoce() {
        return codigoDoce;
    }

    public void setCodigoDoce(int codigoDoce) {
        this.codigoDoce = codigoDoce;
    }
}
