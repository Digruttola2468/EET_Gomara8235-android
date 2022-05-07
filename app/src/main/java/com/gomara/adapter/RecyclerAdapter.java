package com.gomara.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Alumno> alumno = new ArrayList<Alumno>();

    public RecyclerAdapter(ArrayList<Alumno> alumno) {
        this.alumno = alumno;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inasistencias_adapter,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignarDatoNombre(alumno.get(position).getAlumno());
        holder.asignarDatoInasistencias(alumno.get(position).getInasistencias());
    }

    @Override
    public int getItemCount() {
        return alumno.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre;
        private TextView num_inasistencias;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txt_adapter_nombre);
            num_inasistencias = itemView.findViewById(R.id.txt_adapter_inasistencias);
        }

        public void asignarDatoNombre(String s) { nombre.setText(s); }
        public void asignarDatoInasistencias(Float inasistencias){ num_inasistencias.setText(String.valueOf(inasistencias)); }


    }
}
