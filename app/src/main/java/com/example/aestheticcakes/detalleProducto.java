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
        private int numeroCantidad = 1;
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
        precio.setText("Precio: " + detalle.getPrecioSeleccionado() + " Lps.");


    }


    @Override
    protected void onStart() {
        ProductoDetalle detalle = new ProductoDetalle();
        super.onStart();

        TextView cantidad = findViewById(R.id.txtCantidad);
        Button aumentar= findViewById(R.id.btnAumentar);
        Button disminuir= findViewById(R.id.btnDisminuir);
        Button carrito = (Button)findViewById(R.id.btnCarrito);

        cantidad.setText(String.valueOf(numeroCantidad));
        carrito.setText("AÑADIR A CARRITO \n (TOTAL: " + String.valueOf(detalle.getPrecioSeleccionado()) + " LPS)");

        aumentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numeroCantidad =numeroCantidad + 1;
                cantidad.setText(String.valueOf(numeroCantidad));
                carrito.setText("AÑADIR A CARRITO \n (TOTAL: " + String.valueOf(detalle.getPrecioSeleccionado() * numeroCantidad)  + " LPS)");
            }
        });

        disminuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numeroCantidad > 1){
                    numeroCantidad =numeroCantidad -1;
                }
                    cantidad.setText(String.valueOf(numeroCantidad));
                    carrito.setText("AÑADIR A CARRITO \n (TOTAL: " + String.valueOf(detalle.getPrecioSeleccionado() * numeroCantidad) + " LPS)");
            }
        });

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


    public void carritoOpen(View view){
        ProductoDetalle detalle = new ProductoDetalle();


        //detalle.setCantidad(numeroCantidad);
        //Toast.makeText(getApplicationContext(), "ID: " +detalle.getCodigoSeleccionado() + "  CANTIDAD: " + detalle.getCantidad(), Toast.LENGTH_SHORT).show();

    }
}