package com.example.aestheticcakes;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RedireccionadorMenuLateral {

    public void redireccionador(int id, Context context){

        switch (id){
            case R.id.btnNavInicioSesion:
                Toast.makeText(context, "Iniciar Sesión", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "Cerrar Sesión", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void abrirInterfaz(Context context, Class clase){
        Intent interfaz = new Intent(context, clase);
        context.startActivity(interfaz); //Iniciando la activid
    }

}
