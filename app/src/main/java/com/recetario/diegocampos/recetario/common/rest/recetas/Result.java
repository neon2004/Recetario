
package com.recetario.diegocampos.recetario.common.rest.recetas;

import java.io.Serializable;

public class Result implements Serializable
{

    private String title;
    private String href;
    private String ingredients;
    private String thumbnail;
    private final static long serialVersionUID = 7198932588156842794L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
