package br.com.java.app_firebase_loja_online_master.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import br.com.java.app_firebase_loja_online_master.R;
import br.com.java.app_firebase_loja_online_master.model.ListaCompraModelo;

public class ExibirTudoActivity extends AppCompatActivity {

    public static List<ListaCompraModelo> listaComprasModeloLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_tudo);
    }
}