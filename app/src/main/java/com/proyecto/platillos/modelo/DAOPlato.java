package com.proyecto.platillos.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.proyecto.platillos.entidad.Plato;
import com.proyecto.platillos.util.BaseDatos;

import java.util.ArrayList;
import java.util.List;

public class DAOPlato {
    BaseDatos base;
    SQLiteDatabase db;
    Context context;

    public DAOPlato(Context context){
        this.base = new BaseDatos(context);
        this.context = context;
    }

    public void abrirBD(){
        db = base.getWritableDatabase();
    }

    public String registrarPlato(Plato plato){
        String rpta = "";
        try {
            ContentValues valores = new ContentValues();
            valores.put("nombre", plato.getNombre());
            valores.put("tipo", plato.getTipo());
            valores.put("precio", plato.getPrecio());

            long resultado = db.insert("platos", null, valores);
            if(resultado == -1){
                rpta = "Error al insertar";
            }else{
                rpta = "Se registró correctamente";
            }
        }catch (Exception e){
            Log.d("===>", e.toString());
        }
        return rpta;
    }

    public String modificarPlato(Plato plato){
        String rpta = "";
        try {
            ContentValues valores = new ContentValues();
            valores.put("nombre", plato.getNombre());
            valores.put("tipo", plato.getTipo());
            valores.put("precio", plato.getPrecio());

            long resultado = db.update("platos", valores, "id = "+plato.getId(), null);
            if(resultado == -1){
                rpta = "Error al actualizar";
            }else{
                rpta = "Se actualizó correctamente";
            }
        }catch (Exception e){
            Log.d("===>", e.toString());
        }
        return rpta;
    }

    public List<Plato> cargarPlato(){
        List<Plato> listaPlatos = new ArrayList<>();
        try{
            Cursor c = db.rawQuery("SELECT * FROM platos", null);
            while (c.moveToNext()){
                listaPlatos.add(new Plato(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3)));
            }
        }catch (Exception e){
            Log.d("===>", e.toString());
        }
        return listaPlatos;
    }

    public String eliminarPlato(int id){
        String rpta = "";
        try {
            long resultado = db.delete("platos", "id="+id, null);
            if(resultado == -1){
                rpta = "Error al eliminar";
            }else{
                rpta = "Se eliminó correctamente";
            }
        }catch (Exception e){
            Log.d("===>", e.toString());
        }
        return rpta;
    }
}
