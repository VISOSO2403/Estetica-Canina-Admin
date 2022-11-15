package com.example.esteticacaninaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText email, contraseña;
    private Button ingresar;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Hay que conectar el Front-end con el Back-end mediante sus id´s
        email = findViewById(R.id.etxtemail);
        contraseña = findViewById(R.id.etxtpassword);
        //Hay que conectar el Front-end con el Back-end mediante sus id´s
        ingresar = findViewById(R.id.btningresar);
        //Hay que conectar con la base de datos
        mAuth = FirebaseAuth.getInstance();

        ingresar.setOnClickListener(view -> {
            login();
        });
    }

    private void login() {
        String correo = email.getText().toString();
        String pass = contraseña.getText().toString();

        if (TextUtils.isEmpty(correo)){
            email.setError("Este campo no debe estar vacio");
            email.requestFocus();
        }else if (TextUtils.isEmpty(pass)){
            contraseña.setError("Este campo no debe estar vacio");
            contraseña.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                        finish();
                    }else{
                        Log.w("TAG", "Error fatal: ", task.getException());
                    }
                }
            });
        }
    }
}