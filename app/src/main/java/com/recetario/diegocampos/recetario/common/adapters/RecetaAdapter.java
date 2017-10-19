package com.recetario.diegocampos.recetario.common.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.recetario.diegocampos.recetario.R;

import com.recetario.diegocampos.recetario.common.rest.recetas.Result;
import com.recetario.diegocampos.recetario.listaReceta.presenter.ListRecetaPresenter;

import java.util.ArrayList;

/**
 * Created by neon2004 on 28/01/2017.
 */


public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>  {


    RelativeLayout activityMain;
    public ListRecetaPresenter callback;
    private ArrayList<Result> datos;
    private static Context ctx;
    private RecetaViewHolder tvh;
    private View.OnClickListener listener;
    
     public class RecetaViewHolder extends RecyclerView.ViewHolder {

         ImageView imgHeroList;
         TextView tvNamelist;
         CardView card;


        public Result getReceta() {
            return receta;
        }

        private  Result receta;

        public RecetaViewHolder(View itemView) {
            super(itemView);

            imgHeroList = (ImageView)itemView.findViewById(R.id.imgHeroList);
            tvNamelist = (TextView)itemView.findViewById(R.id.tvNamelist);
            card = (CardView)itemView.findViewById(R.id.card);


        }

        public void bindRecetas(Result receta) {
            this.receta = receta;
            tvNamelist.setText(receta.getTitle());
            Glide.with(ctx).load(receta.getThumbnail())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .centerCrop()
                    .animate(android.R.anim.fade_in)
                    .into(imgHeroList);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.goDetail(datos.get(getAdapterPosition()));
                }
            });
        }


    }

    public RecetaAdapter(ArrayList<Result> datos, Context context) {
        this.datos = datos;
        this.ctx = context;
    }

    @Override
    public RecetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        tvh = new RecetaViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(RecetaViewHolder viewHolder, int pos) {
        Result item = datos.get(pos);
        viewHolder.bindRecetas(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

}
