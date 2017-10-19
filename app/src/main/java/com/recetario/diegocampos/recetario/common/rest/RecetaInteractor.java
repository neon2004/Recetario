package com.recetario.diegocampos.recetario.common.rest;

import android.app.Activity;

import  com.recetario.diegocampos.recetario.R;
import com.recetario.diegocampos.recetario.common.base.BaseActivity;
import com.recetario.diegocampos.recetario.common.rest.recetas.Recetas;
import com.recetario.diegocampos.recetario.common.rest.recetas.Result;
import com.recetario.diegocampos.recetario.listaReceta.presenter.ListRecetaPresenter;
import com.recetario.diegocampos.recetario.receta.RecetarioActivity;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neon2004 on 29/01/2017.
 */

public class RecetaInteractor {
    private RecetarioActivity act;
    private ListRecetaPresenter callback;
    private Call<Recetas> call;


    public RecetaInteractor(Activity activity, ListRecetaPresenter listRecetaPresenter) {
        act = (RecetarioActivity) activity;
        callback = listRecetaPresenter;
    }

    public ArrayList<Recetas> getListHeroes(String query) {
        // final ArrayList<Superhero> lisresult = new ArrayList<Superhero>();

        call = BaseActivity.interfaces.getHeroes(query);
        call.enqueue(new Callback<Recetas>() {
            @Override
            public void onResponse(Call<Recetas> call, Response<Recetas> response) {
                if (response.isSuccessful()) {
                    Recetas lisresult = new Recetas();
                    lisresult.setResults(response.body().getResults());
                    callback.createAdapter(lisresult);
                } else {
                    act.showSnackbar(act.getString(R.string.errorGetDatos));
                }
            }

            @Override
            public void onFailure(Call<Recetas> call, Throwable t) {
                act.showSnackbar(act.getString(R.string.errorGetDatos));
            }
        });
        return null;
    }

    public void cancelCall() {
        if (!call.isCanceled()) {
            call.cancel();
        }
    }


}
