package com.gomara.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.Comunicado;
import com.gomara.Prosecer.Materias;

import java.util.ArrayList;

public class RecyclerAdapterMaterias extends RecyclerView.Adapter<RecyclerAdapterMaterias.ViewHolder>{

    private ArrayList<Materias> materias;

    public RecyclerAdapterMaterias(ArrayList materias){
        this.materias = materias;
    }

    @NonNull
    @Override
    public RecyclerAdapterMaterias.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_materias_item,null,false);
        return new RecyclerAdapterMaterias.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setMateria(materias.get(position).getMateria());
        holder.setFechaEvaluacion(materias.get(position).getFechaPrueba());
        holder.setTemasEvaluacion(materias.get(position).getTemasEvaluacion());
    }

    @Override
    public int getItemCount() {
        return materias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView materia,fechaEvaluacion,temasEvaluacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            materia = itemView.findViewById(R.id.txtMateria_materia);
            fechaEvaluacion = itemView.findViewById(R.id.txtFechaEvaluacion_materia);
            temasEvaluacion = itemView.findViewById(R.id.txtTemasEvaluacion_materias_item);
        }

        void setMateria(String stringtitle){
            materia.setText(stringtitle);
        }
        void setFechaEvaluacion(String fecha){
            if(fecha == null)
                fechaEvaluacion.setVisibility(View.GONE);
            else
                fechaEvaluacion.setText("Evaluacion: " + fecha);
        }
        void setTemasEvaluacion(String temas){
            if(temas == null)
                temasEvaluacion.setVisibility(View.GONE);
            else
                temasEvaluacion.setText("Temas: " + temas);
        }
    }
}

