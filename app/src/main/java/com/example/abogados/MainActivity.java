package com.example.abogados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.abogados.abogado.AbogadoActivity;
import com.example.abogados.cliente.ClienteActivity;

public class MainActivity extends AppCompatActivity {


    protected EditText correo;
    protected EditText passwd;
    protected String correoString;
    protected String passwordString;
    protected TextView registro;
    protected Button boton;
    protected Intent pasarPantalla;
    protected GestorBaseDatos gbd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        correo = (EditText) findViewById(R.id.correo_main);
        passwd = (EditText) findViewById(R.id.password_main);
        registro = (TextView) findViewById(R.id.registro_main);
        boton = (Button) findViewById(R.id.boton_main);
        gbd=new GestorBaseDatos(this);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                correoString = correo.getText().toString().trim();
                passwordString = passwd.getText().toString().trim();
                String resultado = gbd.comprobarCredenciales(correoString, passwordString, MainActivity.this);
                if (resultado == null)
                {
                    Toast.makeText(MainActivity.this,"Credenciales no validas",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (resultado.equalsIgnoreCase("cli"))
                    {
                        pasarPantalla = new Intent(MainActivity.this, ClienteActivity.class);
                        startActivity(pasarPantalla);
                        finish();
                    }
                    else if (resultado.equalsIgnoreCase("abo"))
                    {
                        pasarPantalla = new Intent(MainActivity.this, AbogadoActivity.class);
                        startActivity(pasarPantalla);
                        finish();

                    }
                }



            }
        });

        // Nos lleva a la pagina de registro
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(MainActivity.this, Registro.class);
                startActivity(pasarPantalla);

            }
        });

    }
}