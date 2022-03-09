package br.com.java.app_firebase_loja_online_master.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.com.java.app_firebase_loja_online_master.MainActivity;
import br.com.java.app_firebase_loja_online_master.R;


public class RegistrarFragmento extends Fragment {


    public RegistrarFragmento() {
        // Required empty public constructor
    }
    private TextView jatemumaconta;
    private FrameLayout parentFrameLayout;
    private EditText email, nomecompleto, password, confirmapassword;
    private Button btn_registrar;
    private ImageButton btn_fechar;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String emailPadrao = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_registrar, container, false);

        jatemumaconta = view.findViewById(R.id.tv_ja_tem_uma_conta);
        parentFrameLayout = getActivity().findViewById(R.id.registrar_framelayout);
        email = view.findViewById(R.id.registrar_email);
        nomecompleto = view.findViewById(R.id.registrar_nome);
        password = view.findViewById(R.id.registrar_password);
        confirmapassword = view.findViewById(R.id.registrar_confirmar_password);
        btn_fechar = view.findViewById(R.id.btn_registrar_fechar);
        btn_registrar = view.findViewById(R.id.dialog_registrar_btn);
        progressBar = view.findViewById(R.id.registrar_progressbar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        jatemumaconta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                definirFragmento(new RegistrarFragmento());
            }
        });
        btn_fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                principalIntent();
            }
        });
        nomecompleto.addTextChangedListener(new TextWatcher() {
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
        confirmapassword.addTextChangedListener(new TextWatcher() {
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
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarEmailEhPassword();
            }
        });
    }
    private void definirFragmento(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.deslize_na_esquerda, R.anim.deslize_na_direita);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
    private void verificarEntradas() {
        Drawable iconeErroPersonalizado = getResources().getDrawable(R.mipmap.custom_error_icon);
        iconeErroPersonalizado.setBounds(0, 0, iconeErroPersonalizado.getIntrinsicWidth(), iconeErroPersonalizado.getIntrinsicHeight());
        if (!TextUtils.isEmpty(nomecompleto.getText())) {
            if(!TextUtils.isEmpty(email.getText())) {
                if (!TextUtils.isEmpty(password.getText()) && password.length() >= 8) {
                    if (!TextUtils.isEmpty(confirmapassword.getText())) {
                        btn_registrar.setEnabled(true);
                        btn_registrar.setTextColor(Color.rgb(0, 0, 0));
                    } else {
                        btn_registrar.setEnabled(false);
                        btn_registrar.setTextColor(Color.argb(50, 0, 0, 0));
                    }
                } else {
                    password.setError("A senha deve ter no minímo 8 caracteres");
                    btn_registrar.setEnabled(false);
                    btn_registrar.setTextColor(Color.argb(50, 0, 0, 0));
                }
            } else {
                btn_registrar.setEnabled(false);
                btn_registrar.setTextColor(Color.argb(50, 0, 0, 0));
            }
        } else {
            btn_registrar.setEnabled(false);
            btn_registrar.setTextColor(Color.argb(50, 0, 0, 0));
        }
    }
    private void verificarEmailEhPassword() {
        Drawable iconeErroPersonalizado = getResources().getDrawable(R.mipmap.custom_error_icon);
        iconeErroPersonalizado.setBounds(0, 0, iconeErroPersonalizado.getIntrinsicWidth(), iconeErroPersonalizado.getIntrinsicHeight());
        if (email.getText().toString().matches(emailPadrao)) {
            if (password.getText().toString().equals(confirmapassword.getText().toString())) {
                progressBar.setVisibility(View.VISIBLE);
                btn_registrar.setEnabled(false);
                btn_registrar.setTextColor(Color.argb(50, 0, 0, 0));

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Map<Object, String> usuarioDado = new HashMap<>();
                            usuarioDado.put("nomecompleto", nomecompleto.getText().toString());
                            firebaseFirestore.collection("USUARIOS").add(usuarioDado).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()) {
                                        principalIntent();
                                    } else {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        btn_registrar.setEnabled(true);
                                        String erro = task.getException().getMessage();

                                        Toast.makeText(getActivity(), erro, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            btn_registrar.setEnabled(true);
                            String erro = task.getException().getMessage();

                            Toast.makeText(getActivity(), erro, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                confirmapassword.setError("A senha não corresponde!", iconeErroPersonalizado);
            }
        } else {
            email.setError("Email inválido!", iconeErroPersonalizado);
        }
    }
    private void principalIntent() {
        Intent principalIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(principalIntent);
        getActivity().finish();
    }
}