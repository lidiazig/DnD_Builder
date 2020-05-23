package com.lidia.ddbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidia.ddbuilder.pojo.Token;
import com.lidia.ddbuilder.pojo.Usuario;
import com.lidia.ddbuilder.retrofit_api.RetrofitConexion;
import com.lidia.ddbuilder.retrofit_api.RetrofitObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario, txtPassword;
    private Button btnLogin;
    private TextView lbRegistro;
    public static Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        lbRegistro = findViewById(R.id.lbRegistro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if (!txtUsuario.getText().toString().isEmpty() && !txtPassword.getText().toString().isEmpty()) {
                    Usuario user = new Usuario();
                   // user.setEmail(txtUsuario.getText().toString());
                   // user.setContrasena(txtPassword.getText().toString());
                    user.setEmail("correo2@gmail.com");
                    user.setContrasena("123456");
                    RetrofitConexion conexion = RetrofitObject.getConexion().create(RetrofitConexion.class);

                    Call<String> call = conexion.doLogin(user);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    token = new Token();
                                    token.setToken(response.body());
                                    Log.i("onSuccess", response.body().toString());
                                    startActivity(new Intent(MainActivity.this, ListaPersonajesActivity.class));
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "INCORRECT USER OR PASSWORD", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("ERROR", t.getMessage());
                            Toast.makeText(MainActivity.this, "USER ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                        }
                    });
              //  } else {
              //      Toast.makeText(MainActivity.this, "COMPLETE ALL FIELDS", Toast.LENGTH_SHORT).show();
                }
        //    }
        });

        lbRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistroActivity.class));
            }
        });
    }
}
