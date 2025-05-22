package com.example.abogados.cliente;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.abogados.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_cliente), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeCliFragment()).commit();

        BottomNavigationView nav = findViewById(R.id.menu_nav_cliente);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment seleccion = null;

                if (item.getItemId() == R.id.cli_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeCliFragment()).commit();
                    return true;
                } else if (item.getItemId() == R.id.cli_cita){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NoticasCliFragment()).commit();
                    return true;
                } else if (item.getItemId() == R.id. cli_chat){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatCliFragment()).commit();
                    return true;
                }

                return false;
            }
        });


    }
}