package com.example.abogados.abogado;

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

public class AbogadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_abogado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_abogado), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeAdmFragment()).commit();

        // barra de navegacion
        BottomNavigationView nav = findViewById(R.id.menu_nav_abogado);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment seleccion = null;

                if (item.getItemId() == R.id.adm_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeAdmFragment()).commit();
                    return true;
                } else if (item.getItemId() == R.id.adm_contenido){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CrearFragment()).commit();
                    return true;
                } else if (item.getItemId() == R.id.adm_cliente) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClienteFragment()).commit();
                    return true;
                } else if (item.getItemId() == R.id.adm_chat){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatAdmFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }
}
