package com.example.esteticacaninaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    InicioFragment inicioFragment = new InicioFragment();
    ServiciosFragment mensajesFragment = new ServiciosFragment();
    AgendaFragment agendaFragment = new AgendaFragment();
    PerfilFragment perfilFragment = new PerfilFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, inicioFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.inicio:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, inicioFragment).commit();
                        return true;
                    case R.id.mensaje:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, mensajesFragment).commit();
                        return true;
                    case R.id.agenda:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, agendaFragment).commit();
                        return true;
                    case R.id.perfil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, perfilFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}