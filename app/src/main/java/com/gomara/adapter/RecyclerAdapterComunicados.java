package com.gomara.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.Comunicado;

import java.util.ArrayList;

public class RecyclerAdapterComunicados extends RecyclerView.Adapter<RecyclerAdapterComunicados.ViewHolder>{

    private ArrayList<Comunicado> comunicados;

    public RecyclerAdapterComunicados(ArrayList comunicados){
        this.comunicados = comunicados;
    }

    @NonNull
    @Override
    public RecyclerAdapterComunicados.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_comunicados_item,null,false);
        return new RecyclerAdapterComunicados.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setTitle(comunicados.get(position).getTitle());
        holder.setContenido(comunicados.get(position).getContendio());
        holder.setFechaEnvio(comunicados.get(position).getFechaEnvio());
    }

    @Override
    public int getItemCount() {
        return comunicados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView contenido;
        private TextView fechaEnvio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTitle_item_comunicado);
            contenido = itemView.findViewById(R.id.txtContenido_item_comunicado);
            fechaEnvio = itemView.findViewById(R.id.txtFechaEnvio_item_comunicado);
        }

        void setTitle(String stringtitle){
            title.setText(stringtitle);
        }
        void setContenido(String stringContenido){
            contenido.setText(stringContenido);
        }
        void setFechaEnvio(String fecha){fechaEnvio.setText(fecha);}
    }
}
