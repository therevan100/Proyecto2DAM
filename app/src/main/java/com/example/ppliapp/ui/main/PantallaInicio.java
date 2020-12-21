package com.example.ppliapp.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.ppliapp.R;

public class PantallaInicio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent intent = new Intent(PantallaInicio.this, PantallaInicio.class);
                    startActivity(intent);
            }
        }, 4000);
    }
}