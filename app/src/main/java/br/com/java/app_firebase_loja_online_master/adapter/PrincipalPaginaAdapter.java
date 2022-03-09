package br.com.java.app_firebase_loja_online_master.adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.java.app_firebase_loja_online_master.R;
import br.com.java.app_firebase_loja_online_master.model.HorizontalProdutoRolagemModelo;
import br.com.java.app_firebase_loja_online_master.model.ListaCompraModelo;
import br.com.java.app_firebase_loja_online_master.model.PrincipalPaginaModelo;
import br.com.java.app_firebase_loja_online_master.model.SliderModelo;
import br.com.java.app_firebase_loja_online_master.ui.ExibirTudoActivity;

public class PrincipalPaginaAdapter extends RecyclerView.Adapter {

    private List<PrincipalPaginaModelo> principalPaginaModeloLista;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private int ultimaPosicao = -1;

    public PrincipalPaginaAdapter(List<PrincipalPaginaModelo> principalPaginaModeloLista) {
        this.principalPaginaModeloLista = principalPaginaModeloLista;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public int getItemViewType(int position) {
        switch (principalPaginaModeloLista.get(position).getTipo()) {
            case 0:
                return PrincipalPaginaModelo.BANDEIRA_SLIDER;
            case 1:
                return PrincipalPaginaModelo.STRIP_AD_BANNER;
            case 2:
                return PrincipalPaginaModelo.HORIZONTAL_PRODUTO_EXIBIR;
            case 3:
                return PrincipalPaginaModelo.GRID_PRODUTO_EXIBIR;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case PrincipalPaginaModelo.BANDEIRA_SLIDER:
                View bandeiraSliderExibir = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_de_anuncio_deslizante, parent, false);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class BandeiraSliderExibirHolder extends RecyclerView.ViewHolder {

        private ViewPager bandeiraSliderExibirPager;
        private int atualPagina;
        private Timer timer;
        final private long ATRASO_TEMPO = 3000;
        final private long PERIODO_TEMPO = 3000;
        private List<SliderModelo> listaOrganizada;

        public BandeiraSliderExibirHolder(@NonNull View itemView) {
            super(itemView);
            bandeiraSliderExibirPager = itemView.findViewById(R.id.bandeira_slider_exibir_pager);
        }

        public void setBandeiraSliderExibirPager(final List<SliderModelo> sliderModeloLista) {
            atualPagina = 2;
            if (timer != null) {
                timer.cancel();
            }
            listaOrganizada = new ArrayList<>();

            for(int x = 0; x < sliderModeloLista.size(); x++) {
                listaOrganizada.add(x, sliderModeloLista.get(x));
            }
            listaOrganizada.add(0, sliderModeloLista.get(sliderModeloLista.size() - 2));
            listaOrganizada.add(1, sliderModeloLista.get(sliderModeloLista.size() - 1));
            listaOrganizada.add(sliderModeloLista.get(0));
            listaOrganizada.add(sliderModeloLista.get(1));

            SliderAdapter sliderAdapter = new SliderAdapter(listaOrganizada);
            bandeiraSliderExibirPager.setAdapter(sliderAdapter);
            bandeiraSliderExibirPager.setClipToPadding(false);
            bandeiraSliderExibirPager.setPageMargin(20);

            bandeiraSliderExibirPager.setCurrentItem(atualPagina);

            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int i) {
                    atualPagina = i;
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                    if (i == ViewPager.SCROLL_STATE_IDLE) {
                        repetidorPagina(listaOrganizada);
                    }
                }
            };
            bandeiraSliderExibirPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    repetidorPagina(listaOrganizada);
                    paraBandeiraSlideExibir();

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        iniciarBandeiraSlideExibir(listaOrganizada);
                    }
                    return false;
                }
            });
        }
        private void repetidorPagina(List<SliderModelo> sliderModeloLista) {
            if (atualPagina == sliderModeloLista.size() -2) {
                atualPagina = 2;
                bandeiraSliderExibirPager.setCurrentItem(atualPagina, false);
            }
        }
        private void iniciarBandeiraSlideExibir(final List<SliderModelo> sliderModeloLista) {
            final Handler handler = new Handler();
            final Runnable atualizar = new Runnable() {
                @Override
                public void run() {
                    if (atualPagina >= sliderModeloLista.size()) {

                    }
                    bandeiraSliderExibirPager.setCurrentItem(atualPagina++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(atualizar);
                }
            }, ATRASO_TEMPO, PERIODO_TEMPO);
        }
        private void paraBandeiraSlideExibir() {
            timer.cancel();
        }
    }
    public class FaixaAnuncioViewHolder extends RecyclerView.ViewHolder {

        private ImageView faixaAnuncioImagem;
        private ConstraintLayout faixaAnuncioContainer;

        public FaixaAnuncioViewHolder(@NonNull View itemView) {
            super(itemView);
            faixaAnuncioImagem = itemView.findViewById(R.id.faixa_ad_imagem);
            faixaAnuncioContainer = itemView.findViewById(R.id.faixa_ad_container);
        }
        private void definirFaixaAnundio(String recurso, String cor) {
            Glide.with(itemView.getContext()).load(recurso).apply(new RequestOptions().placeholder(R.mipmap.icon_placeholder)).into(faixaAnuncioImagem);
            faixaAnuncioContainer.setBackgroundColor(Color.parseColor(cor));
        }
    }
    public class HorizontalProdutoViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout container;
        private TextView horizontalLayoutTitulo;
        private Button horizontalExibirTodosBtn;
        private RecyclerView horizontalRecyclerView;

        public HorizontalProdutoViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            horizontalLayoutTitulo = itemView.findViewById(R.id.horizontal_scroll_layout_titulo);
            horizontalExibirTodosBtn = itemView.findViewById(R.id.horizontal_scroll_layout_exibir_todos);
            horizontalRecyclerView = itemView.findViewById(R.id.horizonatal_scroll_layout_recyclerview);
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);
        }
        private void definirHorizontalProdutoLayout(List<HorizontalProdutoRolagemModelo> horizontalProdutoRolagemModeloLista, final String titulo, String cor, final List<ListaCompraModelo> exibirTodosProdutosLista) {
            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(cor)));
            horizontalLayoutTitulo.setText(titulo);
            if (horizontalProdutoRolagemModeloLista.size() > 8) {
                horizontalExibirTodosBtn.setVisibility(View.VISIBLE);
                horizontalExibirTodosBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ExibirTudoActivity.listaComprasModeloLista = exibirTodosProdutosLista;
                        Intent exibirTudoIntent = new Intent(itemView.getContext(), ExibirTudoActivity.class);
                        exibirTudoIntent.putExtra("layout_code", 0);
                        exibirTudoIntent.putExtra("titulo", titulo);
                        itemView.getContext().startActivity(exibirTudoIntent);
                    }
                });
            } else {
                horizontalExibirTodosBtn.setVisibility(View.INVISIBLE);
            }

        }
    }
}
