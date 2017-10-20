package com.recetario.diegocampos.recetario.recetaDetalle;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.recetario.diegocampos.recetario.R;
import com.recetario.diegocampos.recetario.common.base.BaseFragment;
import com.recetario.diegocampos.recetario.common.rest.recetas.Result;
import com.recetario.diegocampos.recetario.recetaDetalle.contract.DetailRecetaContract;
import com.recetario.diegocampos.recetario.recetaDetalle.prsenter.DetailRecetaPresenter;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;


/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.detail_receta_fragment)
public class DetailRecetaFragments extends BaseFragment implements DetailRecetaContract.View {

    @ViewById(R.id.scroll)
    NestedScrollView scroll;
    @ViewById(R.id.imageDetail)
    ImageView imageDetail;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.collapser)
    CollapsingToolbarLayout collapser;
    @ViewById(R.id.app_bar)
    AppBarLayout appBar;
    @ViewById(R.id.coordinator)
    CoordinatorLayout coordinator;
    @ViewById(R.id.tvIngredientes)
    TextView tvIngredientes;
    @ViewById(R.id.tvEnlace)
    TextView tvEnlace;

    @FragmentArg
    Result receta;

    private DetailRecetaPresenter recetaDetailPresenter;

    @AfterViews
    protected void detailHeroFragmentsAfterViews() {
        this.recetaDetailPresenter = new DetailRecetaPresenter(this, getActivity(), receta);
        this.recetaDetailPresenter.start();


    }

    @Override
    public void setImage(String url) {
        Glide.with(this).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.error)
                .fitCenter()
                .into(imageDetail);
    }

    @Override
    public void setHref(String enlace) {
        tvEnlace.setText(Html.escapeHtml(enlace));
    }

    @Override
    public void setIngredientes(String ingredientes) {
        tvIngredientes.setText(ingredientes);
    }

    @Override
    public void setTitulo(String titulo) {
        collapser.setTitle(titulo);
    }



}
