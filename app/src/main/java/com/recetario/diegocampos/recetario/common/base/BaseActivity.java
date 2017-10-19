package com.recetario.diegocampos.recetario.common.base;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.recetario.diegocampos.recetario.R;
import com.recetario.diegocampos.recetario.common.rest.IRestClient;
import com.recetario.diegocampos.recetario.common.utils.Constants;


import org.androidannotations.annotations.EActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {
    public Retrofit retrofit;
    public static IRestClient interfaces;

    public void showSnackbar(String msg) {
        final Snackbar snackbar = Snackbar.make(this.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                snackbar.dismiss();
            }
        });
        final View sbView = snackbar.getView();
        final TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        snackbar.show();
    }

    public void crearRetrofitGson(){
        if(isOnline())
        {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.TAG_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            interfaces = retrofit.create(IRestClient.class);

        } else{
            showSnackbar(getString(R.string.errorNoInternet));
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public IRestClient getInterfaces() {
        return interfaces;
    }
}
