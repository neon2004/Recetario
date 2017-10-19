package com.recetario.diegocampos.recetario.listaReceta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.recetario.diegocampos.recetario.R;
import com.recetario.diegocampos.recetario.common.adapters.RecetaAdapter;
import com.recetario.diegocampos.recetario.common.base.BaseFragment;
import com.recetario.diegocampos.recetario.common.rest.recetas.Result;
import com.recetario.diegocampos.recetario.common.utils.Constants;
import com.recetario.diegocampos.recetario.listaReceta.contract.ListRecetaContract;
import com.recetario.diegocampos.recetario.listaReceta.presenter.ListRecetaPresenter;
import com.recetario.diegocampos.recetario.receta.RecetarioActivity;


import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InjectMenu;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;


/**
 * A placeholder fragment containing a simple view.
 */

@EFragment(R.layout.list_receta_fragment)

public class ListRecetaFragments extends BaseFragment implements ListRecetaContract.View {

    @ViewById(R.id.recView)
    RecyclerView recView;
    @ViewById(R.id.activity_main)
    LinearLayout activityMain;
    @ViewById(R.id.appbar)
    Toolbar appbar;
    @ViewById(R.id.imageView)
    ImageView imageView;
    private ListRecetaPresenter heroListPresenter;

    @FragmentArg
    String query;



    @AfterViews
    protected void listHeroesFragmentsAfterViews() {

        this.heroListPresenter = new ListRecetaPresenter(this, getActivity());
        this.heroListPresenter.start(query);
        appbar.setTitle(R.string.app_name);
        ((AppCompatActivity)getActivity()).setSupportActionBar(appbar);

        setHasOptionsMenu(true);
    }

    @Override
    public void setListAdapter(RecetaAdapter adapter) {
        recView.setAdapter(adapter);
    }

    @Override
    public void setLayoutManager() {
        recView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public RecyclerView getListView() {
        return recView;
    }

    @Override
    public void showImageFondo(boolean mostrar) {
        if (mostrar){
            imageView.setVisibility(View.VISIBLE);
            recView.setVisibility(View.GONE);
        }else{
            imageView.setVisibility(View.GONE);
            recView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goToDetailContact(Result receta) {
        RecetarioActivity act = (RecetarioActivity) getActivity();
        act.changeFragment(receta, Constants.TAG_DETAILRECETAFR);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        heroListPresenter.getInteractor().cancelCall();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.busqueda, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                heroListPresenter.getDatos(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s)){
                    heroListPresenter.getDatos(s);
                }
                return false;
            }
        });
    }
}
