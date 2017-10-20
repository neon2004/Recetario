package com.recetario.diegocampos.recetario.listaReceta.contract;


import android.support.v7.widget.RecyclerView;


import com.recetario.diegocampos.recetario.common.adapters.RecetaAdapter;
import com.recetario.diegocampos.recetario.common.rest.recetas.Recetas;
import com.recetario.diegocampos.recetario.common.rest.recetas.Result;


/**
 * Created by neon2004 on 30/04/2017.
 */

public class ListRecetaContract {
    public interface Presenter{
        void createAdapter(Recetas lisresult);
        void goDetail(RecetaAdapter.RecetaViewHolder viewHolder, Result receta);


    }

    public interface View {

        void  setListAdapter(RecetaAdapter adapter);
        void goToDetailContact(RecetaAdapter.RecetaViewHolder viewHolder, Result receta);
        void setLayoutManager();
    }
}
