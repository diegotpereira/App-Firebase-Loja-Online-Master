package br.com.java.app_firebase_loja_online_master.model;

public class CategoriaModelo {

    private String CategoriaIconeLink;
    private String categoriaNome;

    public CategoriaModelo(String categoriaIconeLink, String categoriaNome) {
        CategoriaIconeLink = categoriaIconeLink;
        this.categoriaNome = categoriaNome;
    }

    public String getCategoriaIconeLink() {
        return CategoriaIconeLink;
    }

    public void setCategoriaIconeLink(String categoriaIconeLink) {
        CategoriaIconeLink = categoriaIconeLink;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }
}
