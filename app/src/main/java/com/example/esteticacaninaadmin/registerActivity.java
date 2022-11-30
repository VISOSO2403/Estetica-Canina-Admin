package com.example.esteticacaninaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registerActivity extends AppCompatActivity {

    private EditText nombre, apellido, email, contraseña;
    private Button registrar;

    private String admin;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nombre = (EditText) findViewById(R.id.etxtnombre);
        apellido = (EditText) findViewById(R.id.etxtapellido);
        email = (EditText) findViewById(R.id.etxtemail);
        contraseña = (EditText) findViewById(R.id.etxtpassword);
        registrar = (Button) findViewById(R.id.btnregistrarse);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        admin = mAuth.getCurrentUser().getUid();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String nom = nombre.getText().toString();
                 String apell = apellido.getText().toString();
                 String mail = email.getText().toString();
                 String contra = contraseña.getText().toString();
                if (!nom.isEmpty() && !apell.isEmpty() && !mail.isEmpty() && !contra.isEmpty()){
                    if (contra.length() >= 6){
                        agregarAdmin();
                    }else{
                        Toast.makeText(registerActivity.this, "La contraseña debe tener minimo 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(registerActivity.this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
                }
            }

            private void agregarAdmin() {
                String nom = nombre.getText().toString();
                String apell = apellido.getText().toString();
                String mail = email.getText().toString();
                String contra = contraseña.getText().toString();

                mAuth.createUserWithEmailAndPassword(mail, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            DocumentReference dbReference = db.collection("Admins").document(admin);
                            admin = mAuth.getCurrentUser().getUid();
                            Map<String, Object> admin = new HashMap<>();
                            admin.put("Nombre", nom);
                            admin.put("Correo", mail);
                            admin.put("Apellido", apell);
                            admin.put("Contraseña", contra);
                            admin.put("Rol", "admin");
                            admin.put("ID", admin);


                            dbReference.set(admin).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task2) {
                                    if (task2.isSuccessful()){
                                        Log.d("Finalizado", "Datos registrados" + admin);
                                        //Toast.makeText(SigInActivity.this, "Nuevo trabajador registrado", Toast.LENGTH_SHORT).show();
                                        //startActivity(new Intent(SigInActivity.this, PerfilFragment.class));
                                        //finish(); revisalo mañana
                                    }
                                }
                            });
                            Toast.makeText(registerActivity.this, "Nuevo trabajador registrado", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(registerActivity.this, MenuActivity.class));
                            finish();
                        }else{
                            Toast.makeText(registerActivity.this, "Trabajador no registrado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}