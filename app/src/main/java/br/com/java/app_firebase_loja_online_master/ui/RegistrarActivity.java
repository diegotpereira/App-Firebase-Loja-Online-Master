package br.com.java.app_firebase_loja_online_master.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import br.com.java.app_firebase_loja_online_master.R;

public class RegistrarActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    public static boolean definirFragmentoInscricao =  false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        frameLayout = findViewById(R.id.registrar_framelayout);
        if (definirFragmentoInscricao) {
            definirFragmentoInscricao = false;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
    private void definirFragmentoInscricao(Fragment fragment) {
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}