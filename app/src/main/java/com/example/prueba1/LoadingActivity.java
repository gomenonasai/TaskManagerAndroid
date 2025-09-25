package com.example.prueba1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;


public class LoadingActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private LottieAnimationView lottieCheck;
    private TextView txtEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        progressBar = findViewById(R.id.progressBar);
        lottieCheck = findViewById(R.id.lottieCheck);
        txtEstado = findViewById(R.id.txtEstado);

        // simular "cargando"
        new Handler().postDelayed(() -> {
            // ocultar progress bar
            progressBar.setVisibility(ProgressBar.GONE);

            // mostrar y reproducir Lottie
            lottieCheck.setVisibility(LottieAnimationView.VISIBLE);
            lottieCheck.playAnimation();

            // cambiar texto
            txtEstado.setText("¡Tarea agregada con éxito!");

            // esperar a que termine la animación (o un tiempo fijo) antes de volver
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(LoadingActivity.this, OverviewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }, 1500); // 1.5 segundos para ver el check
        }, 2000); // 2 segundos simulando carga
    }
}