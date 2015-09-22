package com.richpolissystems.android.apispotipy.api;

import com.richpolissystems.android.apispotipy.api.model.SearchArtistResponse;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by richpolis on 21/09/15.
 */
public class ApiClient {

    private static ApiService API_SERVICE;
    private static RestAdapter API_ADAPTER;

    public static ApiService getInstance(){
        //The adapter will be a singleton
        if(API_ADAPTER == null) {
            API_ADAPTER = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .setEndpoint(Constants.URL_BASE)
                    .build();


        }
        API_SERVICE = API_ADAPTER.create(ApiService.class);
        return API_SERVICE;
    }

    public static void searchArtist(String query, Callback<SearchArtistResponse> response){
        getInstance().searchArtist(query, response);
    }
}
