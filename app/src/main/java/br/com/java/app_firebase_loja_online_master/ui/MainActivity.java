package br.com.java.app_firebase_loja_online_master.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import br.com.java.app_firebase_loja_online_master.R;
import br.com.java.app_firebase_loja_online_master.fragment.PrincipalFragmento;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int PRINCIPAL_FRAGMENTO = 0;
    public static Boolean exibirCarrinho = false;
    private FrameLayout frameLayout;
    private ImageView actionBarLogo;
    private int atualFragmento = -1;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.actionbar_logo);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        frameLayout = findViewById(R.id.main_framelayout);

        definirFragmento(new PrincipalFragmento(), PRINCIPAL_FRAGMENTO);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (atualFragmento == PRINCIPAL_FRAGMENTO) {
                atualFragmento = -1;
                super.onBackPressed();
            } else {
                if (exibirCarrinho) {
                    exibirCarrinho = false;
                    finish();
                } else {
                    actionBarLogo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    definirFragmento(new PrincipalFragmento(), PRINCIPAL_FRAGMENTO);
                    navigationView.getMenu().getItem(0).setChecked(true);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar o menu; isso adiciona itens à barra de ação, se estiver presente.
        if (atualFragmento == PRINCIPAL_FRAGMENTO) {
            getSupportActionBar().setDisplayShowCustomEnabled(false);
            getMenuInflater().inflate(R.menu.principal, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Manipula os cliques do item da barra de ação aqui. A barra de ação irá
        // trata automaticamente os cliques no botão Home/Up, tanto tempo
        // conforme você especifica uma atividade pai em AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.principal_pesquisar_icone) {
            return true;
        } else if (id == R.id.principal_notificacao_icone) {
            return true;
        } else if (id == R.id.principal_carrinho_icone) {
            final Dialog entrarDialog = new Dialog(MainActivity.this);
            entrarDialog.setContentView(R.layout.entrar_dialog);
            entrarDialog.setCancelable(true);
            entrarDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Button dialogEntrarBtn = entrarDialog.findViewById(R.id.dialog_entrar_btn);
            Button dialogRegistrarBtn = entrarDialog.findViewById(R.id.dialog_registrar_btn);

            final Intent registrarIntent = new Intent(MainActivity.this, RegistrarActivity.class);

            dialogEntrarBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    entrarDialog.dismiss();

                    startActivity(registrarIntent);
                }
            });
            dialogRegistrarBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    entrarDialog.dismiss();

                    startActivity(registrarIntent);
                }
            });
            entrarDialog.show();

            return true;
        } else if(id == android.R.id.home);

        return super.onOptionsItemSelected(item);
    }
    private  void irParaFragmento(String titulo, Fragment fragment, int fragmentoNo) {
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(titulo);
        invalidateOptionsMenu();
        definirFragmento(fragment, fragmentoNo);

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Lidar com os cliques do item de visualização de navegação aqui.
        int id = item.getItemId();

        if (id == R.id.nav_meu_principal) {
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            definirFragmento(new PrincipalFragmento(), PRINCIPAL_FRAGMENTO);

        }
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    private void definirFragmento(Fragment fragment, int fragmentoNo) {
        if (fragmentoNo != atualFragmento) {
            atualFragmento = fragmentoNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.desaparecer_in, R.anim.desaparecer_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }

    }
}