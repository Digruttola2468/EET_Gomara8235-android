package com.gomara.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.ListaMain;
import com.gomara.UI.Activity_comunicados;
import com.gomara.UI.Activity_inasistencias;
import com.gomara.UI.Activity_materias;
import com.gomara.UI.View_horarios;

import java.util.ArrayList;

public class RecyclerAdapterMain extends RecyclerView.Adapter<RecyclerAdapterMain.ViewHolder>{

    private ArrayList<ListaMain> codigos;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.title.getText().equals("Ver Horarios")){
                    Intent i = new Intent(holder.itemView.getContext(), View_horarios.class);
                    holder.itemView.getContext().startActivity(i);
                }
                if(holder.title.getText().equals("Inasistencias")){
                    Intent i = new Intent(holder.itemView.getContext(), Activity_inasistencias.class);
                    holder.itemView.getContext().startActivity(i);
                }
                if(holder.title.getText().equals("Comunidados")){
                    Intent i = new Intent(holder.itemView.getContext(), Activity_comunicados.class);
                    holder.itemView.getContext().startActivity(i);
                }
                if(holder.title.getText().equals("Materias")){
                    Intent i = new Intent(holder.itemView.getContext(), Activity_materias.class);
                    holder.itemView.getContext().startActivity(i);
                }

            }
        });
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
