package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;
import java.util.List;

public class CategoriasYProductos extends AppCompatActivity {

    private ArrayList<Producto> listProducts = new ArrayList<Producto>();

    int counter = 0;

    ArrayList<Producto> dataList;
    RecyclerView recycler;

    Producto product;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private Handler handler;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_yproductos);
        getSupportActionBar().hide();

        dataList = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.products_Recycler);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        initFB();


        dialog = new ProgressDialog(this);
        dialog.setMessage("Espere...");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                loadCards(1);
            }
        }).start();



        /*handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                dialog.dismiss();
            };
        };*/


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
                                dataList.add(new Producto(p.getProductID(), p.getName(), p.getPrice() , p.getImage(), p.getCategoryID()));
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

        /*LISTENER*/
        recycler.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (recyclerViewReadyCallback != null) {
                    recyclerViewReadyCallback.onLayoutReady();
                }
                recycler.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        recyclerViewReadyCallback = new RecyclerViewReadyCallback() {
            @Override
            public void onLayoutReady() {
                Log.d("TAG", "LAYOUT READY");

            }
        };

        /*listener*/



    }

    public void loadCat1(View view){

        Button btnCat1 = findViewById(R.id.btnCat1);
        Button btnCat2 = findViewById(R.id.btnCat2);

        loadCards(1);
        /*
        dataList.clear();

        loadData("cat1");

        AdaptadorDatos adapter = new AdaptadorDatos(dataList);
        openDetail(adapter);
        recycler.setAdapter(adapter);
        */

        btnCat1.setBackgroundColor(Color.rgb(235,203,195));
        btnCat2.setBackgroundColor(Color.WHITE);
    }

    public void loadCat2(View view){

        Button btnCat1 = findViewById(R.id.btnCat1);
        Button btnCat2 = findViewById(R.id.btnCat2);

        loadCards(2);
        /*
        dataList.clear();

        loadData("cat2");

        AdaptadorDatos adapter = new AdaptadorDatos(dataList);
        openDetail(adapter);
        recycler.setAdapter(adapter);
        */

        btnCat1.setBackgroundColor(Color.WHITE);
        btnCat2.setBackgroundColor(Color.rgb(235,203,195));
    }

    private void openDetail(AdaptadorDatos adapter){
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Product ID: " +dataList.get(recycler.getChildAdapterPosition(v)).getProductID(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*LISTENER*/
    private RecyclerViewReadyCallback recyclerViewReadyCallback;

    public interface RecyclerViewReadyCallback {
        void onLayoutReady();
    }
    /*LISTENER*/



}