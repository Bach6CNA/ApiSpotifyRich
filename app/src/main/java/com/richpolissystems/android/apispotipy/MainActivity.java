package com.richpolissystems.android.apispotipy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.richpolissystems.android.apispotipy.api.ApiClient;
import com.richpolissystems.android.apispotipy.api.model.SearchArtistResponse;
import com.richpolissystems.android.apispotipy.domain.Artist;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;

public class MainActivity extends AppCompatActivity implements Callback<SearchArtistResponse>, ArtistListAdapter.CustomOnItemClickListener {

    RecyclerView recyclerView;
    List<Artist> artistas;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ApiClient.searchArtist("Cher", this);

    }

    private void setUpRecyclerView() {
        this.recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void success(SearchArtistResponse searchArtistResponse, retrofit.client.Response response) {
        artistas = searchArtistResponse.getArtistas();
        ArtistListAdapter adaptador = new ArtistListAdapter(MainActivity.this , artistas);
        adaptador.setCustomOnItemClickListener(MainActivity.this);
        recyclerView.setAdapter(adaptador);
    }

    @Override
    public void failure(RetrofitError error) {

    }

    @Override
    public void onItemClick(int position) {
        Log.d(LOG_TAG, artistas.get(position).getName());
    }
}
