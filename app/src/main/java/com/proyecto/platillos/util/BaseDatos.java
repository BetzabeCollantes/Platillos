package com.proyecto.platillos.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context){
        super(context,"restaurante.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE platos"+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " nombre TEXT NOT NULL, "+
                        " tipo TEXT NOT NULL, "+
                        " precio INTEGER NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion==3) {
            db.setVersion(oldVersion);
        }
    }
}
