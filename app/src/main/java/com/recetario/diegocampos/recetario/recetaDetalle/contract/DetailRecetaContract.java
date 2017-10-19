package com.recetario.diegocampos.recetario.recetaDetalle.contract;


/**
 * Created by neon2004 on 30/04/2017.
 */

public class DetailRecetaContract {
    public interface Presenter{



    }

    public interface View {
        void setTitulo(String titulo);
        void  setImage(String imagen);
        void setHref(String enlace);
        void setIngredientes(String ingredientes);
    }
}
