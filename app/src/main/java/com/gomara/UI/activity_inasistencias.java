package com.gomara.UI;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.adapter.Alumno;
import com.gomara.adapter.RecyclerAdapter;

import java.util.ArrayList;

public class activity_inasistencias extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inasistencias);

        recyclerView = findViewById(R.id.recycler_datos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Ivan Di Gruttola",4.4F));
        alumnos.add(new Alumno("Santiago Torres",5F));
        alumnos.add(new Alumno("Cader Lara",10F));

        RecyclerAdapter adapter = new RecyclerAdapter(alumnos);
        recyclerView.setAdapter(adapter);
    }
}
