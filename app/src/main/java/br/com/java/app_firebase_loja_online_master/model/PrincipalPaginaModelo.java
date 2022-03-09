package br.com.java.app_firebase_loja_online_master.model;

import java.util.List;

public class PrincipalPaginaModelo {

    public static final int BANDEIRA_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUTO_EXIBIR = 2;
    public static final int GRID_PRODUTO_EXIBIR = 3;
    private int tipo;
    private String corDeFundo;

    private List<SliderModelo> sliderModeloLista;

    public PrincipalPaginaModelo(int tipo, List<SliderModelo> sliderModeloLista) {
        this.tipo = tipo;
        this.sliderModeloLista = sliderModeloLista;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<SliderModelo> getSliderModeloLista() {
        return sliderModeloLista;
    }

    public void setSliderModeloLista(List<SliderModelo> sliderModeloLista) {
        this.sliderModeloLista = sliderModeloLista;
    }
    // STRIP AD
    private String recurso;

    public PrincipalPaginaModelo(int tipo, String corDeFundo, String recurso) {
        this.tipo = tipo;
        this.corDeFundo = corDeFundo;
        this.recurso = recurso;
    }

    public String getCorDeFundo() {
        return corDeFundo;
    }

    public void setCorDeFundo(String corDeFundo) {
        this.corDeFundo = corDeFundo;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }
    // VISUALIZAÇÃO HORIZONTAL DO PRODUTO && LAYOUT DO PRODUTO DA GRADE
    private String titulo;
    private List<HorizontalProdutoRolagemModelo> horizontalProdutoRolagemModeloLista;
    private List<ListaCompraModelo> exibirTodosProdutoLista;

    public PrincipalPaginaModelo(int tipo, String titulo, String corDeFundo, List<HorizontalProdutoRolagemModelo> horizontalProdutoRolagemModeloLista, List<ListaCompraModelo> exibirTodosProdutoLista) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.corDeFundo = corDeFundo;
        this.horizontalProdutoRolagemModeloLista = horizontalProdutoRolagemModeloLista;
        this.exibirTodosProdutoLista = exibirTodosProdutoLista;
    }

    public List<ListaCompraModelo> getExibirTodosProdutoLista() {
        return exibirTodosProdutoLista;
    }

    public void setExibirTodosProdutoLista(List<ListaCompraModelo> exibirTodosProdutoLista) {
        this.exibirTodosProdutoLista = exibirTodosProdutoLista;
    }

    public PrincipalPaginaModelo(int tipo, String titulo, String corDeFundo, List<HorizontalProdutoRolagemModelo> horizontalProdutoRolagemModeloLista) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.corDeFundo = corDeFundo;
        this.horizontalProdutoRolagemModeloLista = horizontalProdutoRolagemModeloLista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<HorizontalProdutoRolagemModelo> getHorizontalProdutoRolagemModeloLista() {
        return horizontalProdutoRolagemModeloLista;
    }

    public void setHorizontalProdutoRolagemModeloLista(List<HorizontalProdutoRolagemModelo> horizontalProdutoRolagemModeloLista) {
        this.horizontalProdutoRolagemModeloLista = horizontalProdutoRolagemModeloLista;
    }
}
