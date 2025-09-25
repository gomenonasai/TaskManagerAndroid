package com.example.prueba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class OverviewActivity extends AppCompatActivity {

    private TaskAdapter adapter; // adaptador que conecta la lista de tareas con el RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Cargar layout de la vista de tareas
        setContentView(R.layout.activity_overview);

        setContentView(R.layout.activity_overview);

        // recyclerView lista donde se mostrarán las tareas
        RecyclerView recyclerView = findViewById(R.id.recyclerViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // organizar en lista vertical

        // adaptador con la lista de tareas guardadas en TaskRepository
        adapter = new TaskAdapter(TaskRepository.tasks);
        recyclerView.setAdapter(adapter);

        // botón para agregar nueva tarea
        Button btnAddTask = findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(v -> {
            // abrir pantalla para agregar tarea
            Intent intent = new Intent(OverviewActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // refrescar la lista al volver de AddTaskActivity
        adapter.notifyDataSetChanged();
    }
}