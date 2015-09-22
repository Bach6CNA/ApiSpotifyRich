package com.richpolissystems.android.apispotipy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by richpolis on 21/09/15.
 */
public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ArtistViewHolder> {

    private List<Artist> artistList;
    private LayoutInflater inflater;
    private CustomOnItemClickListener customOnItemClickListener;
    private Context context;

    public void setCustomOnItemClickListener(CustomOnItemClickListener customOnItemClickListener){
        this.customOnItemClickListener = customOnItemClickListener;
    }

    public ArtistListAdapter(Context context, List<Artist> artistas) {
        this.artistList         = artistas;
        this.inflater           = LayoutInflater.from(context);
        this.context            = context;

    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_artist, parent, false);
        ArtistViewHolder viewHolder = new ArtistViewHolder(view,R.id.itemImageView,
                R.id.itemTxtTitulo, R.id.itemTxtDescripcion);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder viewHolder, int position) {
        Artist artista = this.artistList.get(position);
        if(artista.getImages().size()>0) {
            viewHolder.setImageUrl(artista.getImages().get(0));
        }else{

        }
        viewHolder.setTitulo(artista.getName());
        viewHolder.setDescripcion(artista.getUrl());
    }

    @Override
    public int getItemCount() {
        return this.artistList.size();
    }


    public class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemImageView;
        private TextView itemTxtTitulo;
        private TextView itemTxtDescripcion;

        public ArtistViewHolder(View itemView, int itemImageViewId, int itemTxtTituloId, int itemTxtDescripcionId){
            super(itemView);

            this.itemImageView = (ImageView) itemView.findViewById(itemImageViewId);
            this.itemTxtTitulo = (TextView) itemView.findViewById(itemTxtTituloId);
            this.itemTxtDescripcion = (TextView) itemView.findViewById(itemTxtDescripcionId);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            customOnItemClickListener.onItemClick(getAdapterPosition());
        }

        public void setImageUrl(String url){
            Picasso.with(context).load(url).into(itemImageView);
        }

        public void setTitulo(String titulo) {
            this.itemTxtTitulo.setText(titulo);
        }

        public void setDescripcion(String descripcion) {
            this.itemTxtDescripcion.setText(descripcion);
        }
    }

    /*
    * Interfaz para pasar datos hacia la actividad
    */

    public interface CustomOnItemClickListener{

        public void onItemClick(int position);

    }
}
