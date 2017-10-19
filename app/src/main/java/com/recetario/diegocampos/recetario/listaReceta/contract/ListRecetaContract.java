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
        void goDetail(Result receta);


    }

    public interface View {

        void  setListAdapter(RecetaAdapter adapter);
        void goToDetailContact(Result receta);
        void setLayoutManager();
        RecyclerView getListView();
        void showImageFondo(boolean mostrar);

    }
}
