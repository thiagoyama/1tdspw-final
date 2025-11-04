package br.com.fiap.loja.model;

import java.time.LocalDate;

public class Doce {

    private int codigo;
    private String nome;
    private double peso;
    private double valor;
    private LocalDate dataValidade;

    public Doce(){}

    public Doce(int codigo, String nome, double peso, double valor, LocalDate dataValidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.peso = peso;
        this.valor = valor;
        this.dataValidade = dataValidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

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
