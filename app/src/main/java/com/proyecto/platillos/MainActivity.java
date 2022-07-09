package com.proyecto.platillos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.proyecto.platillos.entidad.Plato;
import com.proyecto.platillos.modelo.DAOPlato;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText txtNombre, txtPrecio;
    TextView lblTitulo;
    RadioButton rbMenu, rbCarta, rbBebidas, rbPostres;
    Button btnRegistrar,btnSede;
    Plato plato;
    boolean registra = true;
    int id;

    HashMap map = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
        obtenerValores();
    }

    private void obtenerValores(){
        if(getIntent().hasExtra("pid")){
            registra = false;
            lblTitulo.setText("MODIFICAR PLATILLO");
            btnRegistrar.setText("ACTUALIZAR");
            id = Integer.parseInt(getIntent().getStringExtra("pid"));
            txtNombre.setText(getIntent().getStringExtra("pnombre"));
            txtPrecio.setText(getIntent().getStringExtra("pprecio"));
        }
    }

    private void asignarReferencias(){
        lblTitulo = findViewById(R.id.lblTitulo);
        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        rbMenu = findViewById(R.id.rbMenu);
        rbBebidas = findViewById(R.id.rbBebidas);
        rbCarta = findViewById(R.id.rbCarta);
        rbPostres = findViewById(R.id.rbPostres);
        btnRegistrar = findViewById(R.id.btnRegistrarPedido);


        btnRegistrar.setOnClickListener(v -> {
            if(capturarDatos()){
                DAOPlato daoPlato = new DAOPlato(MainActivity.this);
                daoPlato.abrirBD();
                String msj = "";
                if(registra) {
                    msj = daoPlato.registrarPlato(plato);
                }else{
                    msj = daoPlato.modificarPlato(plato);
                }

                AlertDialog.Builder ventana = new AlertDialog.Builder(MainActivity.this);
                ventana.setTitle("INFORMACION");
                ventana.setMessage(msj);
                ventana.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombre;
                        nombre=getIntent().getStringExtra("u");
                       if(nombre=="admin") {
                           Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                           startActivity(intent);
                       }
                       else{
                           Intent intent = new Intent(MainActivity.this, MapaActivity.class);
                           startActivity(intent);
                       }
                    }
                });
                ventana.create().show();
            }
        });


    }

    private boolean capturarDatos(){
        String nombre = txtNombre.getText().toString();
        String tipo = "";
        if(rbMenu.isChecked()){
            tipo = "Menu";
        }else if(rbBebidas.isChecked()){
            tipo = "Bebidas y más";
        }else if(rbCarta.isChecked()){
            tipo = "Platos a la Carta";
        }else if(rbPostres.isChecked()){
            tipo = "Postres";
        }
        String precio = txtPrecio.getText().toString();
        boolean valida = true;

        if(nombre.equals("")){
            txtNombre.setError("El nombre es obligatorio");
            valida = false;
        }
        if(tipo.equals("")){
            valida = false;
        }
        if(precio.equals("")){
            txtPrecio.setError("El precio es obligatorio");
            valida = false;
        }

        if(valida) {
            if (registra){
                plato = new Plato(nombre, tipo, Integer.parseInt(precio));
            } else {
                plato = new Plato(id, nombre, tipo, Integer.parseInt(precio));
            }
        }

        return valida;
    }
}