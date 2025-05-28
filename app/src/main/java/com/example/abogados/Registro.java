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

    protected EditText nombre;
    protected EditText apellido1;
    protected EditText apellido2;
    protected EditText dni;
    protected EditText telefono;
    protected EditText movil;
    protected EditText email;
    protected EditText password1;
    protected EditText password2;
    private Button next;
    private Button back;
    private Button accept;
    int currentStep = 1;

    private View step1, step2, step3;
    protected Intent pasarPantalla;

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

        nombre = findViewById(R.id.caja_nombre_registro);
        apellido1 = findViewById(R.id.caja_apellido1_registro);
        apellido2 = findViewById(R.id.caja_apellido2_registro);
        dni = findViewById(R.id.caja_dni_registro);
        telefono = findViewById(R.id.caja_telefono_registro);
        movil = findViewById(R.id.caja_movil_registro);
        email = findViewById(R.id.caja_email_registro);
        password1 = findViewById(R.id.caja_password1_registro);
        password2 = findViewById(R.id.caja_password2_registro);

        next = findViewById(R.id.button_next);
        back = findViewById(R.id.button_back);
        accept = findViewById(R.id.button_accept);
        //"paginas" del registro
        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);

        // Muestro solo el primer paso
        step1.setVisibility(View.VISIBLE);
        step2.setVisibility(View.GONE);
        step3.setVisibility(View.GONE);


        showStep(currentStep);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentStep < 3){
                    currentStep++;
                    showStep(currentStep);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep > 1) {
                    currentStep--;
                    showStep(currentStep);
                }
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamamos a la BD para guardar datos
            }
        });

    }

    // Metodo para cambiar elementos durante registro
    private void showStep(int step) {
        step1.setVisibility(step == 1 ? View.VISIBLE : View.GONE);
        step2.setVisibility(step == 2 ? View.VISIBLE : View.GONE);
        step3.setVisibility(step == 3 ? View.VISIBLE : View.GONE);

        back.setVisibility(step == 1 ? View.GONE : View.VISIBLE);
        next.setVisibility(step < 3 ? View.VISIBLE : View.GONE);
        accept.setVisibility(step == 3 ? View.VISIBLE : View.GONE);
    }
}