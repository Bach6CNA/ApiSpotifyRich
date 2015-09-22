package com.richpolissystems.android.apispotipy.api.model;

import com.google.gson.annotations.SerializedName;
import com.richpolissystems.android.apispotipy.domain.Artist;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by richpolis on 21/09/15.
 */
public class SearchArtistResponse {
    @SerializedName("artists")
    ArtistMain main;

    public ArrayList<Artist> getArtistas(){
        return main.artistas;
    }

    public class ArtistMain{

        @SerializedName("items")
        ArrayList<Artist> artistas;
    }
}
