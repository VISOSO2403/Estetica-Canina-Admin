package com.example.esteticacaninaadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductosActivity extends AppCompatActivity implements View.OnClickListener {

    Button agregar, servicios;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        agregar = findViewById(R.id.btnagregar);
        servicios = findViewById(R.id.btnservicios);

        agregar.setOnClickListener(this);
        servicios.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnagregar:
                i = new Intent(ProductosActivity.this, AgregarProductoActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.btnservicios:
                i = new Intent(ProductosActivity.this, ServiciosActivity.class);
                startActivity(i);
                finish();
                break;

        }
    }
}