package com.richpolissystems.android.apispotipy.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by richpolis on 21/09/15.
 */
public class ArtistaImagenes {

    @SerializedName("height")
    public int height;


    @SerializedName("width")
    public int width;


    @SerializedName("url")
    public String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
