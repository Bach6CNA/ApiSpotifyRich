package com.richpolissystems.android.apispotipy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.richpolissystems.android.apispotipy.domain.Artist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainVolleyActivity extends AppCompatActivity implements ArtistListAdapter.CustomOnItemClickListener {

    RecyclerView recyclerView;

    public static final String LOG_TAG = MainVolleyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ejecutaPeticionConVolley("Mus");
    }

    private void setUpRecyclerView() {
        this.recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void ejecutarPeticionConAsyncTask(){
        BusquedaArtistaAsyncTask peticion = new BusquedaArtistaAsyncTask();
        peticion.execute("Daft");
    }

    public void ejecutaPeticionConVolley(String query){
        String url =  "https://api.spotify.com/v1/search?type=artist&q=" + query;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArtistListAdapter adaptador = null;

                try {
                    List<Artist> artistas = parseResponse(response);
                    adaptador = new ArtistListAdapter(MainVolleyActivity.this , artistas);
                    adaptador.setCustomOnItemClickListener(MainVolleyActivity.this);
                    recyclerView.setAdapter(adaptador);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.getMessage());
            }
        });

        VolleySingleton.getInstance(this)
                .addToRequestQueue(request);
    }

    private List<Artist> parseResponse(String response) throws JSONException {
        Log.d(LOG_TAG, response);

        ArrayList<Artist> lista = new ArrayList<>();

        JSONObject respuestaJson = new JSONObject(response);
        JSONObject artistasJson = respuestaJson.getJSONObject("artists");
        JSONArray items = artistasJson.getJSONArray("items");
        int tamano = items.length();

        for(int cont = 0; cont<tamano; cont++){
            JSONObject artista = items.getJSONObject(cont);
            String name = artista.getString("name");
            String url = artista.getString("uri");
            lista.add(new Artist(name, url));
            JSONArray images = artista.getJSONArray("images");
            int largoImages = images.length();
            for (int i = 0; i < largoImages; i++) {
                JSONObject imagenJson = images.getJSONObject(i);
                String urlImagen = imagenJson.getString("url");
                //lista.get(cont).addImage(urlImagen);
            }
        }
        return lista;
    }

    @Override
    public void onItemClick(int position) {

    }
}
