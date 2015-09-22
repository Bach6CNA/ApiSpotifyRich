package com.richpolissystems.android.apispotipy.api;

/**
 * Created by richpolis on 21/09/15.
 */
public class Constants {
    public static final String URL_BASE = "https://api.spotify.com/v1";
    public static final String PATH_SEARCH = "/search";
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_QUERY = "q";
    public static final String VALUE_ARTIST = "artist";
    public static final String URL_SEARCH_ARTIST = PATH_SEARCH + '?'
                                                + PARAM_TYPE + "=" + VALUE_ARTIST;

}
