package com.proyecto.platillos.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyecto.platillos.entidad.Usuario;

import java.util.ArrayList;

public class DaoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "BDUsuarios";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement,nombre text,apellido text,password text,telefono text, tipo text)";

    public DaoUsuario(Context c) {
        this.c = c;
        sql = c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        sql.execSQL(tabla);
        u = new Usuario();
    }

    public boolean insertarUsuario(Usuario u) {
        if(buscar(u.getNombre()) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("nombre",u.getNombre());
            cv.put("apellido",u.getApellido());
            cv.put("password",u.getPassword());
            cv.put("telefono",u.getTelefono());
            cv.put("tipo","cliente");
            return sql.insert("usuario",null,cv) > 0;
        }
            else {
                return false;
            }

    }

    public int buscar(String u) {
        int x = 0;
        lista=selectUsuario();
        for(Usuario us:lista ) {
            if(us.getNombre().equals(u)) {
                x++;
            }
        }
        return x;

    }

    public ArrayList<Usuario> selectUsuario() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr!=null&&cr.moveToFirst()) {
            do{
                Usuario usuario = new Usuario();
                usuario.setId(cr.getInt(0));
                usuario.setNombre(cr.getString(1));
                usuario.setApellido(cr.getString(2));
                usuario.setPassword(cr.getString(3));
                usuario.setTelefono(cr.getString(4));
                usuario.setTipo(cr.getString(5));
                lista.add(usuario);

            } while(cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p) {
        int i = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if (cr.getString(1).equals(u) && cr.getString(3).equals(p)) {
                    i++;
                }
            } while (cr.moveToNext());
        }
        return i;
    }

    public Usuario getUsuario(String u, String p) {
        lista = selectUsuario();
        for (Usuario us:lista) {
            if(us.getNombre().equals(u)&&us.getPassword().equals(p)) {
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id) {
        lista = selectUsuario();
        for (Usuario us:lista) {
            if(us.getId()==id) {
                return us;
            }
        }
        return null;
    }

    
}
