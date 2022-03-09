package br.com.java.app_firebase_loja_online_master.model;

public class SliderModelo {

    private String bandeira;
    private String corDeFundo;

    public SliderModelo(String bandeira, String corDeFundo) {
        this.bandeira = bandeira;
        this.corDeFundo = corDeFundo;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getCorDeFundo() {
        return corDeFundo;
    }

    public void setCorDeFundo(String corDeFundo) {
        this.corDeFundo = corDeFundo;
    }
}
