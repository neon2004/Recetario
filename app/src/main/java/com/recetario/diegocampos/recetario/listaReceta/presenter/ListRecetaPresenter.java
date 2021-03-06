package com.recetario.diegocampos.recetario.listaReceta.presenter;

import android.app.Activity;

import com.recetario.diegocampos.recetario.common.adapters.RecetaAdapter;
import com.recetario.diegocampos.recetario.common.rest.RecetaInteractor;
import com.recetario.diegocampos.recetario.common.rest.recetas.Recetas;
import com.recetario.diegocampos.recetario.common.rest.recetas.Result;
import com.recetario.diegocampos.recetario.listaReceta.contract.ListRecetaContract;


public class ListRecetaPresenter implements ListRecetaContract.Presenter {




    private RecetaInteractor interactor;
    private ListRecetaContract.View listRecetaFragments;
    private Activity act;

    public ListRecetaPresenter(ListRecetaContract.View listRecetaFragments, Activity activity) {
        this.listRecetaFragments = listRecetaFragments;
        this.interactor = new RecetaInteractor(activity,this);
        this.act = activity;
    }

    public void start(String query ){
        getDatos(query);
    }

    public void getDatos(String query){
        interactor.getListRecetas(query);
    }

    @Override
    public void createAdapter(Recetas lisresult) {
        RecetaAdapter adapter = new RecetaAdapter(lisresult.getResults(),act.getApplicationContext());
        adapter.callback = this;
        listRecetaFragments.setListAdapter(adapter);
        listRecetaFragments.setLayoutManager();

    }

    @Override
    public void goDetail(RecetaAdapter.RecetaViewHolder viewHolder, Result receta) {
        listRecetaFragments.goToDetailContact(viewHolder,receta);
    }

    public RecetaInteractor getInteractor() {
        return interactor;
    }


}
