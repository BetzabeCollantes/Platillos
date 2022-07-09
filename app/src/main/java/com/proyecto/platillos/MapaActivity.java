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

import java.util.Map;

public class MapaActivity extends AppCompatActivity {


    Button btn_mapa,btn_mapa2,btn_mapa3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
        asignarReferencias();

    }

    private void asignarReferencias(){
        btn_mapa=findViewById(R.id.btn_mapa);
        btn_mapa2=findViewById(R.id.btn_mapa2);
        btn_mapa3=findViewById(R.id.btn_mapa3);
        btn_mapa.setOnClickListener(view->{
            Intent intent=new Intent(MapaActivity.this,Mapa.class);
            intent.putExtra("latitud","-12.026589");
            intent.putExtra("longitud","-77.005271");
            intent.putExtra("titulo","LA ROSITA - SJL");
            startActivity(intent);
        });
        btn_mapa2.setOnClickListener(view->{
            Intent intent=new Intent(MapaActivity.this, Mapa.class);
            intent.putExtra("latitud","-12.078234");
            intent.putExtra("longitud","-77.089119");
            intent.putExtra("titulo","LA ROSITA - SAN MIGUEL");
            startActivity(intent);
        });
        btn_mapa3.setOnClickListener(view->{
            Intent intent=new Intent(MapaActivity.this,Mapa.class);
            intent.putExtra("latitud","-12.047147");
            intent.putExtra("longitud","-76.967752");
            intent.putExtra("titulo","LA ROSITA - SANTA ANITA");
            startActivity(intent);
        });
    }

}