package com.richpolissystems.android.apispotipy.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by richpolis on 21/09/15.
 */
public class Artist {
    String name;

    String id;

    @SerializedName("popularity")
    int popularity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
