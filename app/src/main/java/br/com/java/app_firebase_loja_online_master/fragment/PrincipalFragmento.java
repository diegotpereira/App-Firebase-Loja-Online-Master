package br.com.java.app_firebase_loja_online_master.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import br.com.java.app_firebase_loja_online_master.R;
import br.com.java.app_firebase_loja_online_master.adapter.CategoriaAdapter;
import br.com.java.app_firebase_loja_online_master.adapter.PrincipalPaginaAdapter;
import br.com.java.app_firebase_loja_online_master.model.CategoriaModelo;
import br.com.java.app_firebase_loja_online_master.model.PrincipalPaginaModelo;
import br.com.java.app_firebase_loja_online_master.model.SliderModelo;


public class PrincipalFragmento extends Fragment {

    public PrincipalFragmento() {
        // Required empty public constructor
    }
    private RecyclerView categoriaRecyclerView;
    private List<CategoriaModelo> categoriaModeloFakeLista = new ArrayList<>();
    private CategoriaAdapter categoriaAdapter;
    private List<PrincipalPaginaModelo> principalPaginaModeloFakeLista = new ArrayList<>();
    private RecyclerView principalPaginaRecyclerView;
    private ImageView naoConectadoInternet;
    public static SwipeRefreshLayout swipeRefreshLayout;
    private PrincipalPaginaAdapter adapter;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_principal, container, false);
        naoConectadoInternet = view.findViewById(R.id.nao_conectado_internet);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.colorPrimary));
        categoriaRecyclerView = view.findViewById(R.id.categoria_recyclerview);
        principalPaginaRecyclerView = view.findViewById(R.id.principalpagina_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        LinearLayoutManager testandoLayoutManager = new LinearLayoutManager(getContext());
        testandoLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        principalPaginaRecyclerView.setLayoutManager(testandoLayoutManager);

        // Categorias Lista Falsa
        categoriaModeloFakeLista.add(new CategoriaModelo("null", ""));
        categoriaModeloFakeLista.add(new CategoriaModelo("", ""));
        categoriaModeloFakeLista.add(new CategoriaModelo("", ""));
        categoriaModeloFakeLista.add(new CategoriaModelo("", ""));
        categoriaModeloFakeLista.add(new CategoriaModelo("", ""));
        categoriaModeloFakeLista.add(new CategoriaModelo("", ""));
        categoriaModeloFakeLista.add(new CategoriaModelo("", ""));
        categoriaModeloFakeLista.add(new CategoriaModelo("", ""));

        // PrincipalPagina Lista Falsa
        List<SliderModelo> sliderModeloFakeLista = new ArrayList<>();
        sliderModeloFakeLista.add(new SliderModelo("null", "#FFFFFF"));
        sliderModeloFakeLista.add(new SliderModelo("null", "#FFFFFF"));
        sliderModeloFakeLista.add(new SliderModelo("null", "#FFFFFF"));
        sliderModeloFakeLista.add(new SliderModelo("null", "#FFFFFF"));

        principalPaginaModeloFakeLista.add(new PrincipalPaginaModelo(0, sliderModeloFakeLista));
        principalPaginaModeloFakeLista.add(new PrincipalPaginaModelo(1, "", "#FFFFFF"));
        principalPaginaModeloFakeLista.add(new PrincipalPaginaModelo(2, "", "#FFFFFF"));
        principalPaginaModeloFakeLista.add(new PrincipalPaginaModelo(3, "", "#FFFFFF"));

        // PrincipalPagina Fake Lista
        categoriaAdapter = new CategoriaAdapter(categoriaModeloFakeLista);
        adapter = new PrincipalPaginaAdapter(principalPaginaModeloFakeLista);

        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() == true) {
            naoConectadoInternet.setVisibility(View.GONE);

//            if (categoriaModeloLista.size() == 0) {
//
//            }
        }
        // Atualizar layout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

            }
        });

        return view;
    }
}