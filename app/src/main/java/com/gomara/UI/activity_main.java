package com.gomara.UI;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomara.R;
import com.gomara.Prosecer.ListaMain;
import com.gomara.adapter.RecyclerAdapterMain;

import java.util.ArrayList;

public class activity_main extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView_main);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        ArrayList<ListaMain> lista = new ArrayList<>();
        lista.add(new ListaMain(R.drawable.image_inasistencias,"Inasistencias"));
        lista.add(new ListaMain(R.drawable.image_horarios,"Ver Horarios"));

        RecyclerAdapterMain adapter = new RecyclerAdapterMain(lista);
        recyclerView.setAdapter(adapter);
    }
}
