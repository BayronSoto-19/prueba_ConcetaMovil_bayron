package com.example.prueba_nacional_bayronsoto.Vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

public class Modificar extends AppCompatActivity {
    private List<Usuario> listUsuario = new ArrayList<>();
    private ArrayAdapter<Usuario> arrayAdapterUsuario;
    private ListView listModificar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    EditText modificarNombre, modificarEdad, modificarCorreo, modificarContraseña2;
    Button modificar;

    Usuario usuarioSelect;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);



        modificarNombre = findViewById(R.id.txtnombre4);
        modificarEdad = findViewById(R.id.txtedad4);
        modificarCorreo = findViewById(R.id.txtcorreo4);
        modificarContraseña2 = findViewById(R.id.txtcontraseña4);

        listModificar = findViewById(R.id.lista_users_3);

        arrayAdapterUsuario = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listUsuario);

        listModificar.setAdapter(arrayAdapterUsuario);

        listModificar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                usuarioSelect = (Usuario) parent.getItemAtPosition(position);

                modificarNombre.setText(usuarioSelect.getNombre());
                modificarEdad.setText(usuarioSelect.getEdad());
                modificarCorreo.setText(usuarioSelect.getCorreo());
                modificarContraseña2.setText(usuarioSelect.getContraseña());

            }
        });

        inicializarFirebase();
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