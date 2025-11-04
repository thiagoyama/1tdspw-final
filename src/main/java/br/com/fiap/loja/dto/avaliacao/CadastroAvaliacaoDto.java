package br.com.fiap.loja.dto.avaliacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class CadastroAvaliacaoDto {

    @Size(max = 200)
    private String descricao;

    @Max(10)
    @PositiveOrZero
    private double nota;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
