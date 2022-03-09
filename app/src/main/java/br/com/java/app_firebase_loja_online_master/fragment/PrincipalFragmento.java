package br.com.java.app_firebase_loja_online_master.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.java.app_firebase_loja_online_master.R;


public class PrincipalFragmento extends Fragment {

    public PrincipalFragmento() {
        // Required empty public constructor
    }
    private RecyclerView categoriaRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_principal, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        categoriaRecyclerView.setLayoutManager(layoutManager);
        return view;
    }
}