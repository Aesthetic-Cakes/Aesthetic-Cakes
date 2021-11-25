package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class detalleProducto extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        //Variable del Scroll Bar //Aplicable a todas las activity
        navigationView = (NavigationView)findViewById(R.id.navView);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        }
        //Para añadir el scrollbar
        navigationView.bringToFront();
        navigationView.setVerticalScrollBarEnabled(true);
        //Fin de seccion del Scroll Bar //Aplicable a todas las activity

        ImageSlider imageSlider = findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slider = new ArrayList<>();
        /*slider.add(new SlideModel(R.drawable.cupcakefruta,null));
        slider.add(new SlideModel(R.drawable.scupcakefrutados,null));
        slider.add(new SlideModel(R.drawable.scupcakefrutatres,null));*/

        imageSlider.setImageList(slider);
    }

    //Inicio de los metodos del menu //Aplicable a todas las activity
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.btnNavInicioSesion:
                Toast.makeText(this, "Iniciar Sesión", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnNavInicio:
                abrirInterfaz(MainActivity.class);
                break;
            case R.id.btnNavCarrito:
                Toast.makeText(this, "Carrito", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnNavEquipoDesarrollo:
                Toast.makeText(this, "Equipo de desarrollo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnNavContactanos:
                abrirInterfaz(Contactanos.class);
                break;
            case R.id.btnNavCerrarSesion:
                Toast.makeText(this, "Cerrar Sesión", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    private void abrirInterfaz(Class clase){
        Intent interfaz = new Intent(this, clase);
        startActivity(interfaz); //Iniciando la activid
    }
    //Fin de los metodos del menu //Aplicable a todas las activity
}