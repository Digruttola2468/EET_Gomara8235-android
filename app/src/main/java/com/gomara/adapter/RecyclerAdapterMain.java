package com.gomara.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.ListaMain;

import java.util.ArrayList;

public class RecyclerAdapterMain extends RecyclerView.Adapter<RecyclerAdapterMain.ViewHolder>{

    ArrayList<ListaMain> codigos = new ArrayList<>();

    public RecyclerAdapterMain(ArrayList<ListaMain> codigos){
        this.codigos = codigos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_main_item,null,false);
        return new RecyclerAdapterMain.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignarImagen(codigos.get(position).getImageView());
        holder.asignarTexto(codigos.get(position).getTexto());
    }

    @Override
    public int getItemCount() {
        return codigos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imageView_mainItem);
            title = itemView.findViewById(R.id.txt_main_item);
        }

        public void asignarImagen(int resId){
            imagen.setImageResource(resId);
        }
        public void asignarTexto(String texto){
            title.setText(texto);
        }

    }
}
