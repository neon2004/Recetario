package com.recetario.diegocampos.recetario.receta;

import android.app.FragmentTransaction;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.transition.Fade;
import android.widget.LinearLayout;


import com.recetario.diegocampos.recetario.R;
import com.recetario.diegocampos.recetario.common.DetailsTransition;
import com.recetario.diegocampos.recetario.common.adapters.RecetaAdapter;
import com.recetario.diegocampos.recetario.common.base.BaseActivity;
import com.recetario.diegocampos.recetario.common.base.BaseFragment;
import com.recetario.diegocampos.recetario.common.dialogs.DialogoWifi;
import com.recetario.diegocampos.recetario.common.rest.IRestClient;
import com.recetario.diegocampos.recetario.common.rest.recetas.Result;
import com.recetario.diegocampos.recetario.common.utils.Constants;
import com.recetario.diegocampos.recetario.listaReceta.ListRecetaFragments;
import com.recetario.diegocampos.recetario.listaReceta.ListRecetaFragments_;
import com.recetario.diegocampos.recetario.recetaDetalle.DetailRecetaFragments;
import com.recetario.diegocampos.recetario.recetaDetalle.DetailRecetaFragments_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_recetario)
public class RecetarioActivity extends BaseActivity {

    @ViewById(R.id.contenedor)
    LinearLayout contenedor;


    private ListRecetaFragments listRecetaFragments;
    private DetailRecetaFragments detailRecetaFragments;
    public IRestClient interfaces;
    public BaseFragment FRAGMENT_ACTUAL;

    @AfterViews
    protected void RecetarioActivityAfterViews() {

        crearRetrofitGson();

        if(isOnline()){
            changeFragment(null, null, Constants.TAG_LISTARECETASFR);
        }else{
            FragmentManager fragmentManager = getSupportFragmentManager();
            DialogoWifi dialogo = new DialogoWifi();
            dialogo.show(fragmentManager, "tagShowWifi");
        }
    }


    @Override
    public void onBackPressed() {
        if(FRAGMENT_ACTUAL instanceof ListRecetaFragments){
            finish();
        }else{
            changeFragment(null, null, Constants.TAG_LISTARECETASFR);
        }
    }

    // CAMBIAMOS EL FRAGMENT A MOSTRAR
    public void changeFragment(RecetaAdapter.RecetaViewHolder viewHolder, Result receta, String framgenCargar) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);


        switch (framgenCargar) {
            case Constants.TAG_LISTARECETASFR:
                if (listRecetaFragments == null) {
                    listRecetaFragments = ListRecetaFragments_.builder().query("").build();
                }
                FRAGMENT_ACTUAL = listRecetaFragments;
                ft.replace(android.R.id.content, listRecetaFragments);
                ft.commit();

                break;
            case Constants.TAG_DETAILRECETAFR:

                detailRecetaFragments = DetailRecetaFragments_.builder().receta(receta).build();
                FRAGMENT_ACTUAL = detailRecetaFragments;


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    detailRecetaFragments.setSharedElementEnterTransition(new DetailsTransition());
                    detailRecetaFragments.setEnterTransition(new Fade());
                    listRecetaFragments.setExitTransition(new Fade());
                    detailRecetaFragments.setSharedElementReturnTransition(new DetailsTransition());
                }

                ft .addSharedElement(viewHolder.getImgRecetaList(), "imgReceta");
                ft.addToBackStack(null);
                ft.replace(android.R.id.content, detailRecetaFragments);
                ft.commit();
                break;
        }
    }
}