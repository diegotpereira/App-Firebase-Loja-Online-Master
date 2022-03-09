package br.com.java.app_firebase_loja_online_master.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import br.com.java.app_firebase_loja_online_master.R;

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

            }
        });
    }
}