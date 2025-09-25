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

    //componentes de la vista
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

        // inicializar vistas
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        rgPrioridad = findViewById(R.id.rgPrioridad);
        cbImportante = findViewById(R.id.cbImportante);
        ratingBar = findViewById(R.id.ratingBar);
        btnSave = findViewById(R.id.btnSave);

        // cargar categorías desde array de categorías
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.task_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);

        // guardar tarea al presionar botón
        btnSave.setOnClickListener(v -> saveTask());
    }
    // método que guarda la tarea en TaskRepository
    private void saveTask() {
        String title = etTitulo.getText().toString().trim();
        String description = etDescripcion.getText().toString().trim();
        String category = spinnerCategoria.getSelectedItem().toString();

        // obtener prioridad seleccionada
        int selectedId = rgPrioridad.getCheckedRadioButtonId();
        String prioridad = "";
        if (selectedId != -1) {
            RadioButton selectedRadio = findViewById(selectedId);
            prioridad = selectedRadio.getText().toString();
        }

        boolean isImportant = cbImportante.isChecked(); // checkbox
        float urgencyLevel = ratingBar.getRating();  // estrellas nivel de urgencia

        // título obligatorio validación
        if (title.isEmpty()) {
            Toast.makeText(this, "El título es obligatorio", Toast.LENGTH_SHORT).show();
            return;
        }

        // guardar la tarea en el repositorio
        TaskRepository.tasks.add(
                new Task(title, description, category, prioridad, isImportant, urgencyLevel)
        );

        // lanzar LoadingActivity
        Intent intent = new Intent(AddTaskActivity.this, LoadingActivity.class);
        startActivity(intent);

        // cerrar la actividad y volver a la lista
        finish();
        }
    }