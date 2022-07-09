package com.proyecto.platillos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyecto.platillos.entidad.Plato;
import com.proyecto.platillos.modelo.DAOPlato;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {
    FloatingActionButton btnNuevo;
    RecyclerView rvPlatos;
    private List<Plato> listaPlatos = new ArrayList<>();
    AdaptadorPersonalizado adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        asignarReferencias();
        mostrarPlatos();
    }

    private void mostrarPlatos(){
        DAOPlato daoPlato = new DAOPlato(this);

        daoPlato.abrirBD();
        listaPlatos = daoPlato.cargarPlato();
        adaptador = new AdaptadorPersonalizado(ListarActivity.this, listaPlatos);
        rvPlatos.setAdapter(adaptador);
        rvPlatos.setLayoutManager(new LinearLayoutManager(ListarActivity.this));
    }

    private void asignarReferencias(){
        btnNuevo = findViewById(R.id.btnNuevo);
        rvPlatos = findViewById(R.id.rvPlatos);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}