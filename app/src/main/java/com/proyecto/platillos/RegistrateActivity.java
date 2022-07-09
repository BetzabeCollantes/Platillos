package com.proyecto.platillos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.platillos.entidad.Usuario;
import com.proyecto.platillos.modelo.DaoUsuario;


public class RegistrateActivity extends AppCompatActivity {

    EditText txtNombreRegis, txtApellidosRegis, txtPasswordRegis, txtTelefonoRegis;
    Button btnRegistrar;
    DaoUsuario dao;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrate);
        dao = new DaoUsuario(this);
        asignarReferencias();

    }

    private void asignarReferencias() {
        txtNombreRegis = findViewById(R.id.txtNombreRegis);
        txtApellidosRegis = findViewById(R.id.txtAPellidosRegis);
        txtPasswordRegis = findViewById(R.id.txtPasswordRegis);
        txtTelefonoRegis = findViewById(R.id.txtTelefonoRegis);
        btnRegistrar = (Button) findViewById(R.id.btnRegistar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setNombre(txtNombreRegis.getText().toString());
                usuario.setTelefono(txtTelefonoRegis.getText().toString());
                usuario.setPassword(txtPasswordRegis.getText().toString());
                usuario.setApellido(txtApellidosRegis.getText().toString());

                if(!usuario.isNull()) {
                    Toast.makeText(RegistrateActivity.this,"Error:Campos en blanco",Toast.LENGTH_LONG).show();
                } else if(dao.insertarUsuario(usuario)) {
                    Toast.makeText(RegistrateActivity.this,"Registro exitoso",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegistrateActivity.this,Login_AdminActivity.class);
                    startActivity(intent);
                    } else {
                    Toast.makeText(RegistrateActivity.this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                }

                }


            });
        }
    }
