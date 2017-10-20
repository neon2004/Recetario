package com.recetario.diegocampos.recetario.listaReceta;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.recetario.diegocampos.recetario.R;
import com.recetario.diegocampos.recetario.common.adapters.RecetaAdapter;
import com.recetario.diegocampos.recetario.common.base.BaseFragment;
import com.recetario.diegocampos.recetario.common.rest.recetas.Result;
import com.recetario.diegocampos.recetario.common.utils.Constants;
import com.recetario.diegocampos.recetario.listaReceta.contract.ListRecetaContract;
import com.recetario.diegocampos.recetario.listaReceta.presenter.ListRecetaPresenter;
import com.recetario.diegocampos.recetario.receta.RecetarioActivity;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
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
    private ListRecetaPresenter recetaListPresenter;

    @FragmentArg
    String query;



    @AfterViews
    protected void listRecetaFragmentsAfterViews() {

        this.recetaListPresenter = new ListRecetaPresenter(this, getActivity());
        this.recetaListPresenter.start(query);
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
    public void goToDetailContact(RecetaAdapter.RecetaViewHolder viewHolder, Result receta) {
        RecetarioActivity act = (RecetarioActivity) getActivity();
        act.changeFragment(viewHolder, receta, Constants.TAG_DETAILRECETAFR);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recetaListPresenter.getInteractor().cancelCall();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.busqueda, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                recetaListPresenter.getDatos(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s)){
                    recetaListPresenter.getDatos(s);
                }
                return false;
            }
        });
    }
}
