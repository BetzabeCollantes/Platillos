package com.proyecto.platillos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.platillos.entidad.Usuario;
import com.proyecto.platillos.modelo.DaoUsuario;

public class Login_AdminActivity extends AppCompatActivity {

    Button btnIniciarSesion;
    Button btnRegistrarSesion;
    EditText txtNomb, txtContra;
    DaoUsuario dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        asignarReferencias();
        dao = new DaoUsuario(this);

    }

    public void asignarReferencias() {
        txtNomb = findViewById(R.id.txtNomb);
        txtContra = findViewById(R.id.txtContra);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
        btnRegistrarSesion = (Button) findViewById(R.id.btnRegistrarSesion);


        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = txtNomb.getText().toString();
                String p = txtContra.getText().toString();
                if (u.equals("admin")&&p.equals("1234")) {
                    Intent intent = new Intent(Login_AdminActivity.this,ListarActivity.class);
                    intent.putExtra("u","admin");
                    startActivity(intent);
                } else if(u.equals("")&&p.equals("")) {

                } else if(dao.login(u,p) == 1) {
                    Usuario ux = dao.getUsuario(u,p);
                    Intent intent = new Intent(Login_AdminActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnRegistrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Login_AdminActivity.this,RegistrateActivity.class);
                startActivity(intent2);
            }
        });
    }


}