package com.example.esteticacaninaadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class politicasActivity extends AppCompatActivity implements View.OnClickListener {

    Button cancelar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicas);

        cancelar = findViewById(R.id.btncancelar);

        cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btncancelar:
                i = new Intent(politicasActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}