package com.example.prueba1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // cargar el layout de home a la pantalla principal
        setContentView(R.layout.activity_home);

        // botón de comenzar
        Button btnComenzar = findViewById(R.id.btnComenzar);

        // método al hacer click en botón comenzar
        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ir desde HomeActivity hacia OverviewActivity (pantalla de lista de tareas)
                Intent intent = new Intent(HomeActivity.this, OverviewActivity.class);
                startActivity(intent);
            }
        });
    }
}

