package br.com.java.app_firebase_loja_online_master.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.java.app_firebase_loja_online_master.ui.MainActivity;
import br.com.java.app_firebase_loja_online_master.R;

//import static br.com.java.app_firebase_loja_online_master.fragment.RegistrarFragmento.emRedefinirFragmentodeSenha;

public class EntrarFragmento extends Fragment {

    public EntrarFragmento() {
        // Required empty public constructor
    }

    private TextView naotenhoumaconta;
    private FrameLayout parentFrameLayout;
    private EditText email, password;
    private Button btn_entrar;
    private ImageButton btn_fechar;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private String emailPadrao = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private TextView esqueceSenha;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_entrar, container, false);
        naotenhoumaconta = view.findViewById(R.id.tv_nao_tem_conta);
        parentFrameLayout = getActivity().findViewById(R.id.registrar_framelayout);
        email = view.findViewById(R.id.entrar_email);
        password = view.findViewById(R.id.entrar_password);
        btn_fechar = view.findViewById(R.id.entrar_fechar_btn);
        progressBar = view.findViewById(R.id.progressBar2);
        firebaseAuth = FirebaseAuth.getInstance();

        btn_entrar = view.findViewById(R.id.entrar_btn);
        esqueceSenha = view.findViewById(R.id.entrar_esqueceu_senha);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        naotenhoumaconta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                definirFragmento(new RegistrarFragmento());
            }
        });
        esqueceSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                emRedefinirFragmentodeSenha = true;
            }
        });
        btn_fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                principalIntent();
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verificarEntradas();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verificarEntradas();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarEmailEhPadrao();
            }
        });
    }
    private void definirFragmento(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.deslize_na_direita, R.anim.deslize_na_esquerda);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
    private void verificarEntradas() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(password.getText())) {
                btn_entrar.setEnabled(true);
                btn_entrar.setTextColor(Color.rgb(0, 0, 0));
            } else {
                btn_entrar.setEnabled(false);
                btn_entrar.setTextColor(Color.argb(50, 0, 0, 0));
            }
        } else {
            btn_entrar.setEnabled(false);
            btn_entrar.setTextColor(Color.argb(50, 0, 0, 0));
        }
    }
    private void verificarEmailEhPadrao() {
        if (email.getText().toString().matches(emailPadrao)) {
            if (password.length() >= 8) {
                progressBar.setVisibility(View.VISIBLE);
                btn_entrar.setEnabled(true);
                btn_entrar.setTextColor(Color.argb(50, 0, 0, 0));

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            principalIntent();
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            btn_entrar.setEnabled(true);
                            btn_entrar.setTextColor(Color.rgb(0, 0, 0));
                            String erro = task.getException().getMessage();

                            Toast.makeText(getActivity(), erro, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                Toast.makeText(getActivity(), "Email ou Senha incorretos", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity(), "Email ou Senha Incorretos", Toast.LENGTH_LONG).show();
        }
    }
    private void principalIntent() {
        Intent principalIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(principalIntent);
        getActivity().finish();
    }
}