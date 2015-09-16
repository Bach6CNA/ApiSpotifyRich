package com.richpolissystems.android.apispotipy;

import java.util.ArrayList;

/**
 * Created by richpolis on 14/09/15.
 */
public class Artist {

    private String name;
    private String url;
    private ArrayList<String> images;

    public Artist(String name, String url) {
        images = new ArrayList<String>();
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

    public ArrayList<String> getImages() {
        return images;
    }

    public void addImage(String image){
        this.images.add(image);
    }

}
