package com.example.esteticacaninaadmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.esteticacaninaadmin.adapter.ServicioAdapter;
import com.example.esteticacaninaadmin.modelo.Servicio;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class ServiciosFragment extends Fragment {

    Button btnagregar;
    RecyclerView mRecycler;
    ServicioAdapter mAdapter;
    FirebaseFirestore mFirestore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_servicios, container, false);
        btnagregar=v.findViewById(R.id.btnagregar);
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = v.findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query query = mFirestore.collection("servicios");

        FirestoreRecyclerOptions<Servicio> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Servicio>().setQuery(query,Servicio.class).build();
        mAdapter = new ServicioAdapter(firestoreRecyclerOptions, getActivity());
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(),AgregarServicioActivity.class));
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}