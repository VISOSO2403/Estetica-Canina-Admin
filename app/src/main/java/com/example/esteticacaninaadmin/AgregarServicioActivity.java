package com.example.esteticacaninaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarServicioActivity extends AppCompatActivity {

    Button btnagregar;
    EditText nombre, descripcion, precio;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicio);
        String id = getIntent().getStringExtra("id_servicio");
        mfirestore = FirebaseFirestore.getInstance();

        btnagregar = (Button) findViewById(R.id.btnagregar);
        nombre = (EditText) findViewById(R.id.etxtnom);
        descripcion = (EditText) findViewById(R.id.etxtdesc);
        precio= (EditText) findViewById(R.id.etxtcosto);

        if (id == null || id == ""){
            btnagregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nservicio = nombre.getText().toString().trim();
                    String dservicio = descripcion.getText().toString().trim();
                    String pservicio = precio.getText().toString().trim();
                    if(nservicio.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Ingresar los datos", Toast.LENGTH_SHORT).show();

                    }else if (dservicio.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Ingresar los datos", Toast.LENGTH_SHORT).show();
                    }else if (pservicio.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Ingresar los datos", Toast.LENGTH_SHORT).show();
                    }else {
                        postServicio(nservicio, dservicio, pservicio);
                    }
                }
            });

        }else {
            btnagregar.setText("Actualizar");
            getServicio(id);
            btnagregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nservicio = nombre.getText().toString().trim();
                    String dservicio = descripcion.getText().toString().trim();
                    String pservicio = precio.getText().toString().trim();
                    if(nservicio.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Ingresar los datos", Toast.LENGTH_SHORT).show();

                    }else if (dservicio.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Ingresar los datos", Toast.LENGTH_SHORT).show();
                    }else if (pservicio.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Ingresar los datos", Toast.LENGTH_SHORT).show();
                    }else {
                        updateServicio(nservicio, dservicio, pservicio, id);
                    }
                }
            });
        }
    }

    private void updateServicio(String nservicio, String dservicio, String pservicio, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nservicio);
        map.put("descripcion", dservicio);
        map.put("precio", pservicio);

        mfirestore.collection("servicios").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Actualizado correctamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AgregarServicioActivity.this,MenuActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error al actualizar",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getServicio(String id) {
        mfirestore.collection("servicios").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nServicio = documentSnapshot.getString("nombre");
                String dServicio = documentSnapshot.getString("descripcion");
                String pServicio = documentSnapshot.getString("precio");

                nombre.setText(nServicio);
                descripcion.setText(dServicio);
                precio.setText(pServicio);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postServicio(String nservicio, String dservicio, String pservicio) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nservicio);
        map.put("descripcion", dservicio);
        map.put("precio", pservicio);

        mfirestore.collection("servicios").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Creado Exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AgregarServicioActivity.this, MenuActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error al ingresar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}