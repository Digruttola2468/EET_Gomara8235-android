package com.gomara.adapter;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.ListaMain;
import com.gomara.UI.activity_inasistencias;
import com.gomara.UI.view_horarios;

import java.util.ArrayList;

public class RecyclerAdapterMain extends RecyclerView.Adapter<RecyclerAdapterMain.ViewHolder>{

    private ArrayList<ListaMain> codigos = new ArrayList<>();
    public RecyclerAdapterMain(ArrayList<ListaMain> codigos){
        this.codigos = codigos;
    }

    private String anio;
    private String curso;
    public RecyclerAdapterMain(ArrayList<ListaMain> codigos,String anio,String curso){
        this.codigos = codigos;
        this.anio = anio;
        this.curso = curso;
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
                    Intent i = new Intent(holder.itemView.getContext(), view_horarios.class);
                    holder.itemView.getContext().startActivity(i);
                }
                if(holder.title.getText().equals("Inasistencias")){
                    Intent i = new Intent(holder.itemView.getContext(), activity_inasistencias.class);
                    holder.itemView.getContext().startActivity(i);
                }
                if(holder.title.getText().equals("Materias")){

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
