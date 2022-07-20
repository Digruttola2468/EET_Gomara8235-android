package com.gomara.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.Alumno;
import com.gomara.Prosecer.Materias;

import java.util.ArrayList;

public class RecyclerAdapterGestionInasistencias extends RecyclerView.Adapter<RecyclerAdapterGestionInasistencias.ViewHolder> {

    private ArrayList<Alumno> alumno;

    public RecyclerAdapterGestionInasistencias(ArrayList alumno) {
        this.alumno = alumno;
    }

    public ArrayList<Alumno> getAlumno(){
        return alumno;
    }

    @NonNull
    @Override
    public RecyclerAdapterGestionInasistencias.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_gestion_inasistencias, null, false);
        return new RecyclerAdapterGestionInasistencias.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterGestionInasistencias.ViewHolder holder, int position) {
        holder.setTxtNombre(alumno.get(position).getAlumno());
        holder.setInasistencia(alumno.get(position).getInasistencias());
        holder.position = holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return alumno.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNombre, txtInasistencias, txtInc, txtDec;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre_gestionInasistencias);
            txtInasistencias = itemView.findViewById(R.id.txtInasistencias_gestionInasistencias);
            txtInc = itemView.findViewById(R.id.txtIncInasistencias_gestion);
            txtDec = itemView.findViewById(R.id.txtDecInasistencias_gestion);

            txtInc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    float aux = (alumno.get(position).getInasistencias() + 0.5F);
                    alumno.get(position).setInasistencias(aux);
                    setInasistencia(aux);
                }
            });
            txtDec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    float aux = (alumno.get(position).getInasistencias() - 0.5F);
                    alumno.get(position).setInasistencias(aux);
                    setInasistencia(aux);
                }
            });
        }

        void setTxtNombre(String texto) {
            txtNombre.setText(texto);
        }

        void setInasistencia(float n) {
            txtInasistencias.setText(String.valueOf(n));
        }
    }
}