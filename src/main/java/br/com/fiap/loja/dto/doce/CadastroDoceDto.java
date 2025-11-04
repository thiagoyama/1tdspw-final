package br.com.fiap.loja.dto.doce;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CadastroDoceDto {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 80)
    private String nome;

    @PositiveOrZero
    private double peso;

    @PositiveOrZero
    private double valor;

    @Future
    private LocalDate dataValidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
