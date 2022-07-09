package com.proyecto.platillos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap nMap;
    float latitud, longitud;
    String titulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(Mapa.this);
        Mensaje();
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        nMap=googleMap;
        nMap.getUiSettings().setZoomControlsEnabled(true);

        recuperarDatos();
        LatLng platillo = new LatLng(latitud, longitud);
        nMap.addMarker(new MarkerOptions().position(platillo).title(titulo));
        nMap.animateCamera(CameraUpdateFactory.newLatLngZoom(platillo,16));

    }
    private void recuperarDatos(){
        latitud=Float.parseFloat(getIntent().getStringExtra("latitud"));
        longitud=Float.parseFloat(getIntent().getStringExtra("longitud"));
        titulo=getIntent().getStringExtra("titulo");
    }
    private void Mensaje(){
        Toast.makeText(Mapa.this,"Pedido Registrado: Ubicar la sede que fue seleccionada", Toast.LENGTH_SHORT).show();

    }
}