package com.example.prueba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText etTitulo, etDescripcion;
    private Spinner spinnerCategoria;
    private RadioGroup rgPrioridad;
    private CheckBox cbImportante;
    private RatingBar ratingBar;
    private Button btnSave;
    private int selectedColor = Color.LTGRAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        rgPrioridad = findViewById(R.id.rgPrioridad);
        cbImportante = findViewById(R.id.cbImportante);
        ratingBar = findViewById(R.id.ratingBar);
        btnSave = findViewById(R.id.btnSave);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.task_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);


        btnSave.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String title = etTitulo.getText().toString().trim();
        String description = etDescripcion.getText().toString().trim();
        String category = spinnerCategoria.getSelectedItem().toString();

        int selectedId = rgPrioridad.getCheckedRadioButtonId();
        String prioridad = "";
        if (selectedId != -1) {
            RadioButton selectedRadio = findViewById(selectedId);
            prioridad = selectedRadio.getText().toString();
        }

        boolean isImportant = cbImportante.isChecked();
        float urgencyLevel = ratingBar.getRating();

        if (title.isEmpty()) {
            Toast.makeText(this, "El título es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }

        TaskRepository.tasks.add(new Task(title, description, category, prioridad, isImportant, urgencyLevel));

        finish();
    }
}