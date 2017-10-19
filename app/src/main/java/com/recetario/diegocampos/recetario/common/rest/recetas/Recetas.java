
package com.recetario.diegocampos.recetario.common.rest.recetas;

import java.io.Serializable;
import java.util.ArrayList;

public class Recetas implements Serializable
{

    private String title;
    private Double version;
    private String href;
    private ArrayList<Result> results = null;
    private final static long serialVersionUID = -536166567632503368L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

}
