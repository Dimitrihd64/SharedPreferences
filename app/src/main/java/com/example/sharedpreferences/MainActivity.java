package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private EditText txtNombre, txtEdad;
    private Button btAceptar, btLimpiar;
    private ImageButton btBorrarNombre, btBorrarEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //asociar a la variable el fichero xml (usuario.xml)
        sharedPreferences = getSharedPreferences(Constantes.USER, MODE_PRIVATE);
        txtNombre = findViewById(R.id.txtNombre);
        txtEdad = findViewById(R.id.txtEdad);
        btAceptar = findViewById(R.id.btAceptar);
        btLimpiar = findViewById(R.id.btLimpiar);
        btBorrarNombre = findViewById(R.id.btBorrarNombre);
        btBorrarEdad = findViewById(R.id.btBorrarEdad);
        inicializaValores();

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //escribir --> editor sharedpreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Constantes.NOMBRE, txtNombre.getText().toString());
                editor.putInt(Constantes.EDAD, Integer.parseInt(txtEdad.getText().toString()));

                editor.apply();
            }
        });
        btLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();

                editor.clear();
                editor.apply();
            }
        });
        btBorrarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(Constantes.NOMBRE);
                editor.apply();

            }
        });
        btBorrarEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(Constantes.EDAD);
                editor.apply();
            }
        });
    }

    private void inicializaValores() {
        String nombre = sharedPreferences.getString(Constantes.NOMBRE, null);
        int edad = sharedPreferences.getInt(Constantes.EDAD, -1);
        if (nombre != null) {
            txtNombre.setText(nombre);
        }
        if (edad != -1) {
            txtEdad.setText(String.valueOf(edad));
        }
    }
}