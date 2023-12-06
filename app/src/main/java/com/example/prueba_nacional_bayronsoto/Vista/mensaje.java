package com.example.prueba_nacional_bayronsoto.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prueba_nacional_bayronsoto.R;

public class mensaje extends AppCompatActivity {

    private Button btnPerfilUsuario;
    private Button btnListarUsuarios;
    private Button btnModificarUsuarios;
    private Button btnMensajeria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        // Inicializar botones
        btnPerfilUsuario = findViewById(R.id.btnPerfilUsuario);
        btnListarUsuarios = findViewById(R.id.btnListarUsuarios);
        btnModificarUsuarios = findViewById(R.id.btnModificarUsuarios);
        btnMensajeria = findViewById(R.id.btnMensajeria);

        // Configurar clic de botones
        btnPerfilUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de perfil de usuario
                Intent intent = new Intent(mensaje.this, Perfil.class);
                startActivity(intent);
            }
        });

        btnListarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de listar usuarios
                Intent intent = new Intent(mensaje.this, Lista.class);
                startActivity(intent);
            }
        });

        btnModificarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de modificar usuarios
                Intent intent = new Intent(mensaje.this, Modificar.class);
                startActivity(intent);
            }
        });

        // Puedes agregar la lógica para el botón de mensajería si es necesario
    }
}
