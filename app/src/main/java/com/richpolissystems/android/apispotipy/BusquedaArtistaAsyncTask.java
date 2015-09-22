package com.richpolissystems.android.apispotipy;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.richpolissystems.android.apispotipy.domain.Artist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by richpolis on 14/09/15.
 */
public class BusquedaArtistaAsyncTask extends AsyncTask<String, Void, List<Artist>>{

    public static final String LOG_TAG = "ApiDemo";

    private AsyncResponse responseListener;

    public BusquedaArtistaAsyncTask() {

    }

    @Override
    protected List<Artist> doInBackground(String... params) {

        if (params.length == 0) {
            //responseListener.onError();
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String artistQuery = params[0];
        String response = null;

        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority("api.spotify.com")
                .appendPath("v1")
                .appendPath("search")
                .appendQueryParameter("type","artist")
                .appendQueryParameter("q",artistQuery)
                .build();

        try {

            //Apertura de conexión
            URL url = new URL(uri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Obtener el stream de respuesta
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                //responseListener.onError();
                return null;
            }

            //Acceso de lectura al stream de respuesta
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Construcción de la respuesta en un string
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line)
                        .append("\n");
            }

            if (buffer.length() == 0) {
                //responseListener.onError();
                return null;
            }
            response = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            //responseListener.onError();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    //responseListener.onError();
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return (List<Artist>) parseResponse(response);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
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
    public void onPostExecute(List<Artist> artists) {

        for (int i = 0; i < artists.size(); i++) {
            Log.d(LOG_TAG, artists.get(i).getName());
            for (int cont = 0; cont < artists.get(i).getImages().size(); cont++) {
                //Log.d(LOG_TAG, artists.get(i).getImages().get(cont));
            }
        }
    }

    public interface AsyncResponse {
        public void onResponse(ArrayList<Artist> artists);
        public void onError();
    }
}