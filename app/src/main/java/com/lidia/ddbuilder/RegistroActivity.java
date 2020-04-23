package com.lidia.ddbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidia.ddbuilder.pojo.Usuario;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtUsuario.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty()) {
                    Usuario user = new Usuario();
                    user.setEmail(txtUsuario.getText().toString());
                    user.setContrasena(txtPassword.getText().toString());
                    RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

                    Call<Usuario> call = conexion.doCreateUser(user);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    Log.i("onSuccess", response.body().toString());
                                    Toast.makeText(RegistroActivity.this, "USER CREATED", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegistroActivity.this, MainActivity.class));
                                }
                            }else{
                                Toast.makeText(RegistroActivity.this, "USER ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Log.d("ERROR", t.getMessage());
                            Toast.makeText(RegistroActivity.this, "USER ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(RegistroActivity.this, "COMPLETE ALL FIELDS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
