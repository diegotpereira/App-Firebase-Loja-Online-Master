package br.com.java.app_firebase_loja_online_master.queries;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import br.com.java.app_firebase_loja_online_master.adapter.CategoriaAdapter;
import br.com.java.app_firebase_loja_online_master.adapter.PrincipalPaginaAdapter;
import br.com.java.app_firebase_loja_online_master.fragment.PrincipalFragmento;
import br.com.java.app_firebase_loja_online_master.model.CategoriaModelo;
import br.com.java.app_firebase_loja_online_master.model.PrincipalPaginaModelo;
import br.com.java.app_firebase_loja_online_master.model.SliderModelo;

public class DBConsultas {

    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<CategoriaModelo> categoriaModeloLista = new ArrayList<>();

    public static List<List<PrincipalPaginaModelo>> listas = new ArrayList<>();
    public static List<String> carregarCategoriasNomes = new ArrayList<>();

    public static void carregarCategorias(final RecyclerView categoriaRecyclerView, final Context context) {

        firebaseFirestore.collection("CATEGORIAS").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        categoriaModeloLista.add(new CategoriaModelo(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoriaNome").toString()));
                    }
                    CategoriaAdapter categoriaAdapter = new CategoriaAdapter(categoriaModeloLista);
                    categoriaRecyclerView.setAdapter(categoriaAdapter);
                    categoriaAdapter.notifyDataSetChanged();
                } else {
                    String erro = task.getException().getMessage();

                    Toast.makeText(context, erro, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public static void carregarFragmentoDado(final RecyclerView principalPaginaRecyclerview, final Context context, final int index, String categoriaNome) {
        firebaseFirestore.collection("CATEGORIAS")
                .document(categoriaNome.toUpperCase())
                .collection("PRINCIPAIS_OFERTAS")
                .orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                if ((long) documentSnapshot.get("exibir_tipo") == 0) {
                                    List<SliderModelo> sliderModeloLista = new ArrayList<>();
                                    long sem_bandeiras = (long) documentSnapshot.get("sem_bandeiras");
                                    for(long x =1; x < sem_bandeiras + 1; x++) {
                                        sliderModeloLista.add(new SliderModelo(documentSnapshot.get("bandeira_" + x).toString(), documentSnapshot.get("bandeira_" + x + "background").toString()));
                                    }
                                    listas.get(index).add(new PrincipalPaginaModelo(0, sliderModeloLista));
                                } else if((long) documentSnapshot.get("exibir_tipo") == 1) {
                                    listas.get(index).add(new PrincipalPaginaModelo(1, documentSnapshot.get("faixa_anuncio_bandeira").toString(), documentSnapshot.get("background").toString()));
                                } else if ((long) documentSnapshot.get("exibir_tipo") == 2) {

                                }
                            }
                            PrincipalPaginaAdapter adapter = new PrincipalPaginaAdapter(listas.get(index));
                            principalPaginaRecyclerview.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            PrincipalFragmento.swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
    }
}
