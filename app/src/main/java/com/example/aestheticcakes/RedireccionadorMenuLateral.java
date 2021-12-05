package com.example.aestheticcakes;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RedireccionadorMenuLateral {

    public void redireccionador(int id, Context context){

        switch (id){
            case R.id.btnNavInicioSesion:
                abrirInterfaz(context, MainActivity.class);
                break;
            case R.id.btnNavInicio:
                abrirInterfaz(context, CategoriasYProductos.class);
                break;
            case R.id.btnNavCarrito:
                Toast.makeText(context, "Carrito", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnNavEquipoDesarrollo:
                abrirInterfaz(context, EquipoDesarrollo.class);
                break;
            case R.id.btnNavContactanos:
                abrirInterfaz(context, Contactanos.class);
                break;
            case R.id.btnNavCerrarSesion:
                cerrarSesion(context);
                break;
            default:
                break;
        }
    }

    private void abrirInterfaz(Context context, Class clase){
        Intent interfaz = new Intent(context, clase);
        context.startActivity(interfaz);
    }

    private void cerrarSesion(Context context){
        if(CategoriasYProductos.inicioSesion == true){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder
                    .setTitle("Cerrar Sesión")
                    .setMessage("¿Estás Seguro?")
                    .setIcon(R.drawable.ic_warnign)
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CategoriasYProductos.inicioSesion = false;
                            FirebaseAuth.getInstance().signOut();
                            abrirInterfaz(context, CategoriasYProductos.class);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        else{
            Toast.makeText(context, "No ha iniciado sesión", Toast.LENGTH_SHORT).show();
        }
    }

}
