package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class CategoriasYProductos extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    private ArrayList<Producto> listProducts = new ArrayList<Producto>();
    NavigationView navigationView;

    ArrayList<Producto> dataList;
    RecyclerView recycler;

    FirebaseDatabase firebaseDatabase;
    Bitmap temp;
    DatabaseReference databaseReference;

    public static String correoElec = "";
    public static Boolean inicioSesion = false;


    private Handler handler;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_yproductos);

        //Variable del Scroll Bar //Aplicable a todas las activity
        navigationView = (NavigationView)findViewById(R.id.navView);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        }
        //Para añadir el scrollbar
        navigationView.bringToFront();
        navigationView.setVerticalScrollBarEnabled(true);
        //Continua de seccion del Scroll Bar //Aplicable a todas las activity
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        View headerView = navigationView.getHeaderView(0);
        TextView correoMenu = (TextView) headerView.findViewById(R.id.txtMailMenu);
        TextView nombreMenu = (TextView) headerView.findViewById(R.id.txtNombreMenu);

        if(inicioSesion == false){
            nombreMenu.setText("Invitado");
            correoMenu.setText("Invitado");
        }
        else{
            nombreMenu.setText("Pendiente");
            correoMenu.setText(String.valueOf(CategoriasYProductos.correoElec));
        }
        //Fin de lineas del scrollbar, no aplicables a todas las activities

        dataList = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.products_Recycler);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));


        dialog = new ProgressDialog(this);
        dialog.setMessage("Obteniendo datos del servidor...");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        initFB();
    }

    //Inicio de los metodos del menu //Aplicable a todas las activity
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        new RedireccionadorMenuLateral().redireccionador(id, this);
        return true;
    }

    //Fin de los metodos del menu //Aplicable a todas las activity

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadCards(1);
                dialog.dismiss();
            }
        }).start();
        Button btnCat1 = findViewById(R.id.btnCat1);
        btnCat1.setBackgroundColor(Color.rgb(235,203,195));
    }

    private void initFB(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    private void loadCards(int cat){
        dataList.clear();

        databaseReference.child("Productos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listProducts.clear();

                for(DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Producto p = objSnapshot.getValue(Producto.class);
                    if (p.getCategoryID() == cat){

                        dataList.add(new Producto(p.getProductID(), p.getName(), p.getPrice() , p.getImage(),p.getImageSlider1(), p.getImageSlider2(), p.getProductDescription(), p.getCategoryID()));
                    }
                }

                AdaptadorDatos adapter = new AdaptadorDatos(dataList);
                openDetail(adapter);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "ERROR AL CONECTAR CON LA BASE DE DATOS", Toast.LENGTH_SHORT);
            }
        });

        View recyclerView = findViewById(R.id.products_Recycler);
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }



    public void loadCat1(View view){
        loadCards(1);
        SetBtnFocus(1);
    }

    public void loadCat2(View view){
        loadCards(2);
        SetBtnFocus(2);
    }
    public void loadCat3(View view){
        loadCards(3);
        SetBtnFocus(3);
    }
    public void loadCat4(View view){
        loadCards(4);
        SetBtnFocus(4);
    }
    public void loadCat5(View view){
        loadCards(5);
        SetBtnFocus(5);
    }
    public void loadCat6(View view){
        loadCards(6);
        SetBtnFocus(6);
    }

    public void SetBtnFocus(int cat){
        Button btnCat1 = findViewById(R.id.btnCat1);
        Button btnCat2 = findViewById(R.id.btnCat2);
        Button btnCat3 = findViewById(R.id.btnCat3);
        Button btnCat4 = findViewById(R.id.btnCat4);
        Button btnCat5 = findViewById(R.id.btnCat5);
        Button btnCat6 = findViewById(R.id.btnCat6);

        btnCat1.setBackgroundColor(Color.WHITE);
        btnCat2.setBackgroundColor(Color.WHITE);
        btnCat3.setBackgroundColor(Color.WHITE);
        btnCat4.setBackgroundColor(Color.WHITE);
        btnCat5.setBackgroundColor(Color.WHITE);
        btnCat6.setBackgroundColor(Color.WHITE);

        switch (cat){
            case 1:
                btnCat1.setBackgroundColor(Color.rgb(235,203,195));
                break;
            case 2:
                btnCat2.setBackgroundColor(Color.rgb(235,203,195));
                break;
            case 3:
                btnCat3.setBackgroundColor(Color.rgb(235,203,195));
                break;
            case 4:
                btnCat4.setBackgroundColor(Color.rgb(235,203,195));
                break;
            case 5:
                btnCat5.setBackgroundColor(Color.rgb(235,203,195));
                break;
            case 6:
                btnCat6.setBackgroundColor(Color.rgb(235,203,195));
                break;
        }
    }

    private void openDetail(AdaptadorDatos adapter){
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (v.getContext(), detalleProducto.class);
                startActivity(intent);
                ProductoDetalle detalle = new ProductoDetalle();

                detalle.setCodigoSeleccionado(dataList.get(recycler.getChildAdapterPosition(v)).getProductID());
                detalle.setNombreSelccionado(dataList.get(recycler.getChildAdapterPosition(v)).getName());
                detalle.setDescripcionSeleccionada(dataList.get(recycler.getChildAdapterPosition(v)).getProductDescription());
                detalle.setPrecioSeleccionado(dataList.get(recycler.getChildAdapterPosition(v)).getPrice());

                detalle.setImage1(dataList.get(recycler.getChildAdapterPosition(v)).getImage());
                detalle.setImage2(dataList.get(recycler.getChildAdapterPosition(v)).getImageSlider1());
                detalle.setImage3(dataList.get(recycler.getChildAdapterPosition(v)).getImageSlider2());
                //Toast.makeText(getApplicationContext(), "Product ID: " +detalle.getDescripcionSeleccionada(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}