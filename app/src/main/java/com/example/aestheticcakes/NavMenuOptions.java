package com.example.aestheticcakes;

import static androidx.core.content.ContextCompat.startActivity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NavMenuOptions extends AppCompatActivity {

    /*Button inicio = (Button) findViewById(R.id.btnNavInicio);
    public static Context context;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.btnNavInicio:
                Toast.makeText(context, "Inicio", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.btnNavCarrito:
                Toast.makeText(context, "Carrito", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }*/

    //Para el login
    /*public void botonLogin(View view){
        Intent loginView = new Intent(this, Menu.class);
        startActivity(boton_menu);
    }*/

    //Para el inicio
    public void botonInicio(View view, Context context){
        Intent inicioView = new Intent(context, MainActivity.class);
        context.startActivity(inicioView);
    }

}
