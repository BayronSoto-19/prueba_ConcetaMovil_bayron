package com.example.prueba_nacional_bayronsoto.Vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class Register extends AppCompatActivity {

    private List<Usuario> listUsuario = new ArrayList<Usuario>();
    ArrayAdapter<Usuario> arrayAdapterUsuario;

    EditText Nombre, Edad, Correo, Contraseña2;
    Button registrar, limpiar;

    ListView listV_usuario;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Usuario usuarioSelect;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Nombre = findViewById(R.id.txtnombre);
        Edad = findViewById(R.id.txtedad);
        Correo = findViewById(R.id.txtcorreo2);
        Contraseña2 = findViewById(R.id.txtcontraseña2);

        registrar = findViewById(R.id.btnActualizar);
        limpiar = findViewById(R.id.btnAgregarImg);

        listV_usuario = findViewById(R.id.lv_datosUsuarios2);

        inicializarfirebase();

        listarDatos();

        registar();

        limpiar();

    }

    private void listarDatos() {

        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listUsuario.clear();
                for(DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    listaUsuario();
                    Usuario u = objSnaptshot.getValue(Usuario.class);
                    listUsuario.add(u);

                    arrayAdapterUsuario = new ArrayAdapter<Usuario>(Register.this, android.R.layout.simple_list_item_1, listUsuario);
                    listV_usuario.setAdapter(arrayAdapterUsuario);
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


            private void listaUsuario() {
            }

        });

    }

    private void inicializarfirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();

    }

    private void registar(){
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Nombre.getText().toString().trim().isEmpty()
                        || Edad.getText().toString().trim().isEmpty()
                        || Correo.getText().toString().trim().isEmpty()
                        || Contraseña2.getText().toString().trim().isEmpty()) {

                    Toast.makeText(Register.this, "Atencion, complrete los campos que le restan", Toast.LENGTH_SHORT).show();
                }

                else {
                    String nombre = Nombre.getText().toString();
                    int Edad2 = Integer.parseInt(Edad.getText().toString());
                    String correo = Correo.getText().toString();
                    String contraseña2 = Contraseña2.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Usuario.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Usuario usuario = new Usuario(nombre, Edad2, correo, contraseña2);
                            dbref.push().setValue(usuario);
                            Toast.makeText(Register.this, "Atencion, se a registrado correctamente", Toast.LENGTH_SHORT).show();
                            Nombre.setText("");
                            Edad.setText("");
                            Correo.setText("");
                            Contraseña2.setText("");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }

    private void limpiar(){
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nombre.setText("");
                Edad.setText("");
                Correo.setText("");
                Contraseña2.setText("");
            }
        });
    }


}


