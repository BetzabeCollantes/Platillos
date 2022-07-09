package com.proyecto.platillos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.platillos.entidad.Plato;
import com.proyecto.platillos.modelo.DAOPlato;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.MyViewHolder> {
    private Context context;
    private List<Plato> listaPlatos = new ArrayList<>();

    public AdaptadorPersonalizado(Context context, List<Plato> listaPlatos) {
        this.context = context;
        this.listaPlatos = listaPlatos;
    }

    @NonNull
    @Override
    public AdaptadorPersonalizado.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.fila, parent, false);
        return new MyViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.filaNombre.setText(listaPlatos.get(position).getNombre()+"");
        holder.filaTipo.setText(listaPlatos.get(position).getTipo()+"");
        holder.filaPrecio.setText(listaPlatos.get(position).getPrecio()+"");
        holder.fila.setOnLongClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("pid", listaPlatos.get(position).getId()+"");
            intent.putExtra("pnombre", listaPlatos.get(position).getNombre()+"");
            intent.putExtra("ptipo", listaPlatos.get(position).getTipo()+"");
            intent.putExtra("pprecio", listaPlatos.get(position).getPrecio()+"");
            context.startActivity(intent);
            return false;
        });
        holder.filaEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setTitle("Confirmación");
                ventana.setMessage("Desea eliminar el plato "+listaPlatos.get(position).getNombre());
                ventana.setNegativeButton("NO", null);
                ventana.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DAOPlato daoPlato = new DAOPlato(context);
                        daoPlato.abrirBD();
                        String msj = daoPlato.eliminarPlato(listaPlatos.get(position).getId());

                        AlertDialog.Builder v2 = new AlertDialog.Builder(context);
                        v2.setTitle("Mensaje informativo");
                        v2.setMessage(msj);
                        v2.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, ListarActivity.class);
                                context.startActivity(intent);
                            }
                        });
                        v2.create().show();
                    }
                });
                ventana.create().show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPlatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView filaNombre, filaTipo, filaPrecio;
        ImageView filaImagen;
        LinearLayout fila;
        ImageButton filaEliminar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            filaNombre = itemView.findViewById(R.id.filaNombre);
            filaTipo = itemView.findViewById(R.id.filaTipo);
            filaPrecio = itemView.findViewById(R.id.filaPrecio);
            filaImagen = itemView.findViewById(R.id.filaImagen);
            fila = itemView.findViewById(R.id.fila);
            filaEliminar = itemView.findViewById(R.id.filaEliminar);
        }
    }
}
