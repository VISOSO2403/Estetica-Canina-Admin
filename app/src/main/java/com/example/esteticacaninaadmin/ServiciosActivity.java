package com.example.esteticacaninaadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiciosActivity extends AppCompatActivity implements View.OnClickListener {

    Button agregar, productos;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        agregar = findViewById(R.id.btnagregar);
        productos = findViewById(R.id.btnproductos);

        agregar.setOnClickListener(this);
        productos.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnagregar:
                i = new Intent(ServiciosActivity.this, AgregarServicioActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.btnproductos:
                i = new Intent(ServiciosActivity.this, ProductosActivity.class);
                startActivity(i);
                finish();
                break;

        }
    }
}