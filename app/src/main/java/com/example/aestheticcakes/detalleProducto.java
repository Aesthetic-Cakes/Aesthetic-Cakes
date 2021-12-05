package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class detalleProducto extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
        private int numeroCantidad = 1;
        private Button agregarCarrito;
        private String estado ="Normal";
        private TextView productoPrecio, productoNombre;
        private String productoID = "", CurrentUserId;
        private FirebaseAuth auth;


        NavigationView navigationView;
    private Object SimpleDateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        productoID = getIntent().getStringExtra("pid");

        productoPrecio=(TextView)findViewById(R.id.txtPrecio);
        productoNombre=(TextView) findViewById(R.id.txtNombre);
        ObtenerDatosProducto(productoID);
        auth = FirebaseAuth.getInstance();
        CurrentUserId =auth.getCurrentUser().getUid();

        agregarCarrito = (Button) findViewById(R.id.btnCarrito);
        agregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estado.equals("Pedidos")|| estado.equals("Enviado")){
                    Toast.makeText(detalleProducto.this, "Esperando a que finalice el primer pedido...", Toast.LENGTH_SHORT).show();

                }else{
                    agregarAlista();
                }
            }
        });



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

    private void ObtenerDatosProducto(String productoID) {
        DatabaseReference ProductoRef = FirebaseDatabase.getInstance().getReference().child("Productos");
        ProductoRef.child(productoID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                Carrito productos = snapshot.getValue(Carrito.class);
                productoNombre.setText(productos.getNombre());
                productoPrecio.setText(productos.getPrecio());
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void agregarAlista() {
        String CurrentTime, CurrentDate;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat data = new SimpleDateFormat("MM-dd-yyyy");
        CurrentDate=data.format(calendar.getTime());
        SimpleDateFormat time= new SimpleDateFormat("MM-dd-yyyy");
        CurrentTime=time.format(calendar.getTime());

        final DatabaseReference CarListRef = FirebaseDatabase.getInstance().getReference().child("CarritoLista");
        final HashMap<String, Object> map = new HashMap<>();
        map.put("pid", productoID);
        map.put("nombre", productoNombre.getText().toString());
        map.put("precio", productoPrecio.getText().toString());
        map.put("fecha", CurrentDate);
        map.put("hora", CurrentTime);
        map.put("cantidad",numeroCantidad);

        CarListRef.child("Users").child(CurrentUserId).child("Productos").child(productoID).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    CarListRef.child("Administracion").child(CurrentUserId).child("Productos").child(productoID).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(detalleProducto.this, "Agregado...", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(detalleProducto.this, MainActivity.class);
                                startActivity(intent);
                            }

                        }
                    });
                }
            }
        });

    }


    @Override
    protected void onStart() {
        ProductoDetalle detalle = new ProductoDetalle();
        super.onStart();
        VerificarEstadOrden();

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
//Verificar
    private void VerificarEstadOrden() {
        DatabaseReference OrdenRef;
        OrdenRef = FirebaseDatabase.getInstance().getReference().child("Orden").child(CurrentUserId);
        OrdenRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String envioStado = snapshot.child("estado").getValue().toString();
                    if(envioStado.equals("Enviado")){
                        estado = "Enviado";
                    }else if(envioStado.equals("No Enviado")){
                        estado = "Pedido";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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