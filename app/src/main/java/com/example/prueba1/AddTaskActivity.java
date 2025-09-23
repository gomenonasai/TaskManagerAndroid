package com.example.prueba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText etTitle = findViewById(R.id.etTitle);
        EditText etDescription = findViewById(R.id.etDescription);
        RadioGroup rgPriority = findViewById(R.id.rgPriority);
        CheckBox cbImportant = findViewById(R.id.cbImportant);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String description = etDescription.getText().toString();

            int selectedId = rgPriority.getCheckedRadioButtonId();
            RadioButton rb = findViewById(selectedId);
            String priority = (rb != null) ? rb.getText().toString() : "Sin prioridad";

            if (cbImportant.isChecked()) {
                priority = "Importante";
            }

            // Guardar en lista estática crear la tarea
            TaskRepository.tasks.add(new Task(title, description, priority));

            // Cerrar esta Activity y volver a Overview
            Intent intent = new Intent(AddTaskActivity.this, OverviewActivity.class);
            startActivity(intent);

            // opcional: cerrar AddTaskActivity para no volver atrás
            finish();
        });
    }
}