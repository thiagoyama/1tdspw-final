package br.com.fiap.loja.dto.avaliacao;

public class MediaAvaliacaoDto {

    private int codigoDoce;

    private double media;

    private int quantidade;

    public int getCodigoDoce() {
        return codigoDoce;
    }

    public void setCodigoDoce(int codigoDoce) {
        this.codigoDoce = codigoDoce;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
