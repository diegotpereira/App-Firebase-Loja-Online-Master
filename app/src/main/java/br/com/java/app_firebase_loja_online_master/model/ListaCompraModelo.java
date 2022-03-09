package br.com.java.app_firebase_loja_online_master.model;

public class ListaCompraModelo {

    private String produtoImagem;
    private String produtoTitulo;
    private String cuponsGratis;
    private String avaliacao;
    private long totalAvaliacoes;
    private String produtoPreco;
    private String precoReduzido;
    private boolean COD;

    public ListaCompraModelo(String produtoImagem, String produtoTitulo, String cuponsGratis, String avaliacao, long totalAvaliacoes, String produtoPreco, String precoReduzido, boolean COD) {
        this.produtoImagem = produtoImagem;
        this.produtoTitulo = produtoTitulo;
        this.cuponsGratis = cuponsGratis;
        this.avaliacao = avaliacao;
        this.totalAvaliacoes = totalAvaliacoes;
        this.produtoPreco = produtoPreco;
        this.precoReduzido = precoReduzido;
        this.COD = COD;
    }

    public String getProdutoImagem() {
        return produtoImagem;
    }

    public void setProdutoImagem(String produtoImagem) {
        this.produtoImagem = produtoImagem;
    }

    public String getProdutoTitulo() {
        return produtoTitulo;
    }

    public void setProdutoTitulo(String produtoTitulo) {
        this.produtoTitulo = produtoTitulo;
    }

    public String getCuponsGratis() {
        return cuponsGratis;
    }

    public void setCuponsGratis(String cuponsGratis) {
        this.cuponsGratis = cuponsGratis;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public long getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(long totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }

    public String getProdutoPreco() {
        return produtoPreco;
    }

    public void setProdutoPreco(String produtoPreco) {
        this.produtoPreco = produtoPreco;
    }

    public String getPrecoReduzido() {
        return precoReduzido;
    }

    public void setPrecoReduzido(String precoReduzido) {
        this.precoReduzido = precoReduzido;
    }

    public boolean isCOD() {
        return COD;
    }

    public void setCOD(boolean COD) {
        this.COD = COD;
    }
}
