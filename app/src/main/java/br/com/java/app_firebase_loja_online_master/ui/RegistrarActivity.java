package br.com.java.app_firebase_loja_online_master.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import br.com.java.app_firebase_loja_online_master.R;
import br.com.java.app_firebase_loja_online_master.fragment.EntrarFragmento;
import br.com.java.app_firebase_loja_online_master.fragment.RegistrarFragmento;

public class RegistrarActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    public static boolean emRedefinirFragmentodeSenha = false;
    public static boolean definirFragmentoInscricao =  false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        frameLayout = findViewById(R.id.registrar_framelayout);
        if (definirFragmentoInscricao) {
            definirFragmentoInscricao = false;
            definirFragmentoPadrao(new RegistrarFragmento());
        } else {
            definirFragmentoPadrao(new RegistrarFragmento());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (emRedefinirFragmentodeSenha) {
                emRedefinirFragmentodeSenha = false;
                definirFragmento(new EntrarFragmento());

                return false;
            }
        } else {

        }
        return super.onKeyDown(keyCode, event);
    }
    private void definirFragmentoPadrao(Fragment fragment) {
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
    private void definirFragmento(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.deslize_na_esquerda, R.anim.deslize_na_direita);
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}