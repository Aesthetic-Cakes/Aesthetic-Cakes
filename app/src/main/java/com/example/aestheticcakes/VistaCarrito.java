package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;

import java.nio.file.attribute.UserPrincipalLookupService;


public class VistaCarrito extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button Pagar;
    private TextView TotalPrecio, mensaje1;

    private double PrecioTotal = 0.0;
    private String CurrentUserId;
    private FirebaseAuth auth;
    //hola

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

        FirebaseRecyclerOptions<Carrito> options = new FirebaseRecyclerOptions.Builder<Carrito>().setQuery(CarListRef.child("Users").child(CurrentUserId).child("Productos"), Carrito.class).build();

        FirebaseRecyclerAdapter<Carrito, CarritoView> adapter = new FirebaseRecyclerAdapter<Carrito, CarritoView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CarritoView carritoView, int which, @NonNull Carrito carrito) {
              carritoView.carProductoNombre.setText(carrito.getNombre());
              carritoView.carProductoPrecio.setText(carrito.getPrecio());
              carritoView.carProductoCantidad.setText(carrito.getCantidad());

              double UnTipoPrecio = (Double.valueOf(carrito.getPrecio()))*Integer.valueOf(carrito.getCantidad());
              PrecioTotal = PrecioTotal + UnTipoPrecio;

              carritoView.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      CharSequence options[]=new CharSequence[]{
                              "Editar",
                              "Eliminar"
                      };
                      AlertDialog.Builder builder = new AlertDialog.Builder(VistaCarrito.this);
                      builder.setTitle("Opciones del Producto");

                      builder.setItems(options, new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              if(which==0){
                                  Intent intent = new Intent(VistaCarrito.this, ProductoDetalle.class);
                                  intent.putExtra("pid", carrito.getPid());
                              }
                              if(which==1){
                                  CarListRef.child("Users").child(CurrentUserId).child("Productos").child(carrito.getPid())
                                          .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                      @Override
                                      public void onComplete(@NonNull Task<Void> task) {
                                          if(task.isSuccessful()){
                                              Toast.makeText(VistaCarrito.this,"Producto Eliminado", Toast.LENGTH_SHORT).show();
                                              Intent intent = new Intent (VistaCarrito.this, MainActivity.class);
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

            @NonNull
            @Override
            public CarritoView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalle_producto, parent, false);
                CarritoView carritoView = new CarritoView(view);

                return carritoView;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
    private void VerificarEstado(){

    }
}