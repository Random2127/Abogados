package com.example.abogados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registro extends AppCompatActivity {

    protected EditText usuario;
    protected EditText email;
    protected EditText password1;
    protected EditText password2;

    protected Button volver;
    protected Button aceptar;

    protected Intent pasraPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuario = (EditText) findViewById(R.id.nombre_registro);
        email = (EditText) findViewById(R.id.email_registro);
        password1 = (EditText) findViewById(R.id.password1_registro);
        password2 = (EditText) findViewById(R.id.password2_registro);
        volver = (Button) findViewById(R.id.volver_registro);
        aceptar = (Button) findViewById(R.id.aceptar_registro);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasraPantalla = new Intent(Registro.this, MainActivity.class);
                startActivity(pasraPantalla);

            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // MManda informaciómn a la DB para registrar usuario

                // retorna a main

            }
        });



    }
}