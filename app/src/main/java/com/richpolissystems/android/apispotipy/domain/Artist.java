package com.richpolissystems.android.apispotipy.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by richpolis on 14/09/15.
 */
public class Artist {

    @SerializedName("name")
    public String name;

    @SerializedName("uri")
    public String url;

    @SerializedName("images")
    public ArrayList<ArtistaImagenes> images;

    public Artist(){
        this.name = "";
        this.url = "";
        images = new ArrayList<ArtistaImagenes>();
    }

    public Artist(String name, String url) {
        images = new ArrayList<ArtistaImagenes>();
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<ArtistaImagenes> getImages() {
        return images;
    }

    public void addImage(ArtistaImagenes imagen){
        this.images.add(imagen);
    }

}
