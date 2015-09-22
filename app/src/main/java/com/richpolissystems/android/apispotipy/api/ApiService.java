package com.richpolissystems.android.apispotipy.api;

import com.richpolissystems.android.apispotipy.api.model.SearchArtistResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by richpolis on 21/09/15.
 */
public interface ApiService {

    @GET(Constants.URL_SEARCH_ARTIST)
    public void searchArtist(@Query(Constants.PARAM_QUERY) String query,
                             Callback<SearchArtistResponse> serverResponse);



}
