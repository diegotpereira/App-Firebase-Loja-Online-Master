package br.com.java.app_firebase_loja_online_master.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import br.com.java.app_firebase_loja_online_master.R;
import br.com.java.app_firebase_loja_online_master.model.CategoriaModelo;
import br.com.java.app_firebase_loja_online_master.ui.CategoriaActivity;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {

    private List<CategoriaModelo> categoriaModeloLista;
    private int ultimaposicao = 1;

    public CategoriaAdapter(List<CategoriaModelo> categoriaModeloLista) {
        this.categoriaModeloLista = categoriaModeloLista;
    }
    @NonNull
    @Override
    public CategoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdapter.ViewHolder holder, int position) {
        String icone = categoriaModeloLista.get(position).getCategoriaIconeLink();
        String nome = categoriaModeloLista.get(position).getCategoriaNome();

        holder.definirCategoria(nome, position);
        holder.definirIconeCategoria(icone);

        if (ultimaposicao < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.desaparecer_in);
            holder.itemView.setAnimation(animation);
            ultimaposicao = position;
        }
    }

    @Override
    public int getItemCount() {
        return categoriaModeloLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView categoriaIcone;
        private TextView categoriaNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriaIcone = itemView.findViewById(R.id.categoria_icone);
            categoriaNome = itemView.findViewById(R.id.categoria_nome);
        }
        private void definirIconeCategoria(String iconeUrl) {
            if (!iconeUrl.equals("null")) {
                Glide.with(itemView.getContext()).load(iconeUrl).apply(new RequestOptions().placeholder(R.mipmap.icon_placeholder)).into(categoriaIcone);
            } else {
                categoriaIcone.setImageResource(R.mipmap.home_icon);
            }
        }
        private void definirCategoria(final String nome, final int posicao) {
            categoriaNome.setText(nome);
            if (!nome.equals("")) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (posicao != 0) {
                            Intent categoriaIntent = new Intent(itemView.getContext(), CategoriaActivity.class);
                            categoriaIntent.putExtra("CategoriaNome", nome);
                            itemView.getContext().startActivity(categoriaIntent);
                        }
                    }
                });
            }
        }
    }
}
