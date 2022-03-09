package br.com.java.app_firebase_loja_online_master.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import br.com.java.app_firebase_loja_online_master.model.SliderModelo;

public class SliderAdapter extends PagerAdapter {
    public SliderAdapter(List<SliderModelo> listaOrganizada) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
