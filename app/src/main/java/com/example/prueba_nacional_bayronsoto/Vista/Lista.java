package com.example.prueba_nacional_bayronsoto.Vista;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.prueba_nacional_bayronsoto.Modelo.Usuario;
import com.example.prueba_nacional_bayronsoto.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Lista extends AppCompatActivity {

    private List<Usuario> listUsuario = new ArrayList<>();
    private ArrayAdapter<Usuario> arrayAdapterUsuario;
    private ListView listV_usuario;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        inicializarFirebase();
        listV_usuario = findViewById(R.id.Lista_user);
        arrayAdapterUsuario = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listUsuario);
        listV_usuario.setAdapter(arrayAdapterUsuario);

        listarDatos();
    }

    private void listarDatos() {
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listUsuario.clear();

                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    Usuario u = objSnaptshot.getValue(Usuario.class);

                    if (u != null) {
                        listUsuario.add(u);
                    }
                }
                arrayAdapterUsuario.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }
}