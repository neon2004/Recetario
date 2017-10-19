package com.recetario.diegocampos.recetario.recetaDetalle.prsenter;

import android.app.Activity;


import com.recetario.diegocampos.recetario.common.rest.recetas.Result;
import com.recetario.diegocampos.recetario.recetaDetalle.contract.DetailRecetaContract;


public class DetailRecetaPresenter implements DetailRecetaContract.Presenter{

    private final Activity act;
    private final Result receta;
    private DetailRecetaContract.View detailRecetaFragments;

    public DetailRecetaPresenter(DetailRecetaContract.View detailRecetaFragments, Activity activity, Result receta) {
        this.detailRecetaFragments = detailRecetaFragments;
        this.act = activity;
        this.receta = receta;
    }

    public void start(){
        detailRecetaFragments.setTitulo(receta.getTitle());
        detailRecetaFragments.setImage(receta.getThumbnail());
        detailRecetaFragments.setHref(receta.getHref());
        detailRecetaFragments.setIngredientes(receta.getIngredients());
    }
}
