package com.example.esteticacaninaadmin.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esteticacaninaadmin.AgregarServicioActivity;
import com.example.esteticacaninaadmin.R;
import com.example.esteticacaninaadmin.modelo.Servicio;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

public class ServicioAdapter extends FirestoreRecyclerAdapter<Servicio, ServicioAdapter.ViewHolder>{

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ServicioAdapter(@NonNull FirestoreRecyclerOptions<Servicio> options,Activity activity) {
        super(options);
        this.activity = activity;
    }


    @Override
    protected void onBindViewHolder(@NonNull ServicioAdapter.ViewHolder viewHolder, int i, @NonNull Servicio Servicio) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        //alerta de borrado


        viewHolder.nombre.setText(Servicio.getNombre());
        viewHolder.descipcion.setText(Servicio.getDescripcion());
        viewHolder.precio.setText(Servicio.getPrecio());

        viewHolder.btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, AgregarServicioActivity.class);
                i.putExtra("id_servicio",id);
                activity.startActivity(i);
            }
        });
        viewHolder.btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(activity);
                dialogo1.setTitle("AVISO IMPORTANTE");
                dialogo1.setMessage("¿Esta seguro de que desea eliminar el servicio?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo1, int i) {
                        aceptar();
                        deleteServicio(id);

                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo1, int i) {
                        cancelar();
                    }
                });
                dialogo1.show();
            }
        });
    }

    private void cancelar() {
    }

    private void aceptar() {
    }

    private void deleteServicio(String id) {
        mFirestore.collection("servicios").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public ServicioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_servicio,parent,false);

        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descipcion, precio;
        Button btn_eliminar, btn_editar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre =itemView.findViewById(R.id.nombre);
            descipcion=itemView.findViewById(R.id.descripcion);
            precio=itemView.findViewById(R.id.precio);
            btn_eliminar =itemView.findViewById(R.id.btnborrar);
            btn_editar=itemView.findViewById(R.id.btneditar);

        }
    }
}
