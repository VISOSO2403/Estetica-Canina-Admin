package com.example.esteticacaninaadmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class PerfilFragment extends Fragment {

    TextView nameuser;
    Button cerrarSesion, acercaDe, priPoli;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore firebaseFirestore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();

        //Variables de usuario
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        //Variables de botones
        cerrarSesion = root.findViewById(R.id.btncerrarsesion);
        acercaDe = root.findViewById(R.id.btnacerde);
        priPoli = root.findViewById(R.id.btnpripoli);
        nameuser = root.findViewById(R.id.txtnomusu);

        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), activity_acerca_de.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        priPoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), politicasActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        //Acción de Botón Cerrar Sesión
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CerrarSesion ();
            }
        });

        return root;
    }
    //Metodo Cerrar Sesión
    private void CerrarSesion (){
        mAuth.signOut();
        Toast.makeText(getActivity(), "Sesión Cerrada", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }
}