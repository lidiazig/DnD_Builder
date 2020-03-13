package com.lidia.ddbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtUsuario, txtPassword;
    private Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtUsuario = findViewById(R.id.txtEmailRegistro);
        txtPassword = findViewById(R.id.txtPassRegistro);
        btnRegistro = findViewById(R.id.btnRegistro);
    }
}
