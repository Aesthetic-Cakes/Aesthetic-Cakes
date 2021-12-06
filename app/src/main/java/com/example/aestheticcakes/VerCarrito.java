package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VerCarrito extends AppCompatActivity {
    private ArrayList<Carrito> listProducts = new ArrayList<Carrito>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<Carrito> dataList;
    RecyclerView recycler;
    TextView totalAlPagar;

    private int totalAPagar = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carrito);

        dataList = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.cartRecyclerView2);
        totalAlPagar = (TextView) findViewById(R.id.TotalPrecio);
        recycler.setLayoutManager(new GridLayoutManager(this, 1));

        initFB();
        loadCart();
    }

    private void initFB(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void loadCart(){
        dataList.clear();

        databaseReference.child("CarritoLista").child("Users").child(""+FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Productos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listProducts.clear();

                for(DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Carrito c = objSnapshot.getValue(Carrito.class);

                    totalAPagar += Integer.parseInt(c.getCantidad()) * Double.parseDouble(c.getPrecio());
                    dataList.add(new Carrito(c.getPid(), c.getNombre(), c.getPrecio(), c.getCantidad(), c.getDescuento()));
                }
                AdaptadorDatosCarrito adapter = new AdaptadorDatosCarrito(dataList);
                whenClick(adapter);
                recycler.setAdapter(adapter);

                totalAlPagar.setText("Total: " +totalAPagar);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "ERROR AL CONECTAR CON LA BASE DE DATOS", Toast.LENGTH_SHORT);
            }
        });

    }

    private void whenClick(AdaptadorDatosCarrito adapter){
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence options[]=new CharSequence[]{
                        "Eliminar"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(VerCarrito.this);
                builder.setTitle("Opciones del Producto");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){
                            databaseReference.child("CarritoLista").child("Users").child(""+FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Productos").child(dataList.get(recycler.getChildAdapterPosition(v)).getPid())
                                    .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Producto Eliminado", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent (v.getContext(), VerCarrito.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                });
                builder.show();
            }
        });
    }

    public void deleteAllUsersCart(View view){
        databaseReference.child("CarritoLista").child("Users").child(""+FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Productos").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"PEDIDO ENVIADO", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent (VerCarrito.this, CategoriasYProductos.class);
                    startActivity(intent);
                }
            }
        });
    }
}