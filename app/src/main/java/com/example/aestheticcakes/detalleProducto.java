package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class detalleProducto extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        ProductoDetalle detalle = new ProductoDetalle();

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

        List<SlideModel> slider = new ArrayList<>();


        slider.add(new SlideModel(detalle.getImage1(),null));
        slider.add(new SlideModel(detalle.getImage2(),null));
        slider.add(new SlideModel(detalle.getImage3(), null));

        imageSlider.setImageList(slider);

        TextView descripcion = findViewById(R.id.txtDescripcion);
        TextView nombre = findViewById(R.id.txtNombre);
        TextView precio = findViewById(R.id.txtPrecio);

        nombre.setText(detalle.getNombreSelccionado());
        descripcion.setText(detalle.getDescripcionSeleccionada());
        precio.setText("Lps. " + detalle.getPrecioSeleccionado());

    }

    //Inicio de los metodos del menu //Aplicable a todas las activity
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        new RedireccionadorMenuLateral().redireccionador(id, this);
        return true;
    }

    private void abrirInterfaz(Class clase){
        Intent interfaz = new Intent(this, clase);
        startActivity(interfaz); //Iniciando la activid
    }
    //Fin de los metodos del menu //Aplicable a todas las activity

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void carritoOpen(View view){
        ProductoDetalle detalle = new ProductoDetalle();

        //VALIDAR CANTIDAD
        detalle.setCantidad(4);

        Toast.makeText(getApplicationContext(), "ID: " +detalle.getCodigoSeleccionado() + "  CANTIDAD: " + detalle.getCantidad(), Toast.LENGTH_SHORT).show();

    }
}