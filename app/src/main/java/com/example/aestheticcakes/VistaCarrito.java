package com.example.aestheticcakes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.file.attribute.UserPrincipalLookupService;

public class VistaCarrito extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button Pagar;
    private TextView TotalPrecio, mensaje1;

    private double PrecioTotal = 0.0;
    private String CurrentUserId;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_carrito);

        recyclerView=(RecyclerView)findViewById(R.id.carrito_lista);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);

        Pagar=(Button) findViewById(R.id.Pagar);
        TotalPrecio=(TextView) findViewById(R.id.TotalPrecio);
        mensaje1=(TextView) findViewById(R.id.mensaje1);
        auth = FirebaseAuth.getInstance();
        CurrentUserId= auth.getCurrentUser().getUid();

        Pagar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VistaCarrito.this, ConfirmarOrden.class);
                intent.putExtra("Total", String.valueOf(PrecioTotal));
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected void onStart() {

        super.onStart();

        VerificarEstado();

        TotalPrecio.setText("Total a Pagar:"+String.valueOf(PrecioTotal));
        final DatabaseReference CarListRef = FirebaseDatabase.getInstance().getReference().child("CarritoLista");

       FirebaseRecycleOptions<VistaCarrito> options = new FirebaseRecycleOptions.Builder<Carrito>()
    }
    private void VerificarEstado(){

    }
}