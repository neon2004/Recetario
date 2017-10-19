package com.recetario.diegocampos.recetario.common.rest;



import com.recetario.diegocampos.recetario.common.rest.recetas.Recetas;

import com.recetario.diegocampos.recetario.common.rest.recetas.Result;
import com.recetario.diegocampos.recetario.common.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by neon2004 on 28/01/2017.
 */

public interface IRestClient {
    @GET(Constants.TAG_PARAM_URL)
    Call<Recetas> getHeroes(@Query("q") String query);


}
