package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import android.os.Bundle;


public class AdaptadorDatosCarrito extends RecyclerView.Adapter<AdaptadorDatosCarrito.ViewHolderDatos>
        implements View.OnClickListener {


    ArrayList<Carrito> dataList;
    private View.OnClickListener listener;

    public AdaptadorDatosCarrito(ArrayList<Carrito> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdaptadorDatosCarrito.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elm_list_cart,null,false);

        view.setOnClickListener(this);
        return new AdaptadorDatosCarrito.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDatosCarrito.ViewHolderDatos holder, int position) {
        double precioIndividual = 0;
        int cantidad = 0;

        holder.productName.setText(dataList.get(position).getNombre());
        holder.productPrice.setText("Cantidad: " + dataList.get(position).getCantidad().toString());

        precioIndividual = Double.parseDouble(dataList.get(position).getPrecio());
        cantidad = Integer.parseInt(dataList.get(position).getCantidad());

        holder.productTotal.setText("Total Lps. " + precioIndividual * cantidad);


        Log.d("TAG", "ALL DATA READY");
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView productName, productPrice, productTotal;


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.cart_product_name);
            productPrice = (TextView) itemView.findViewById(R.id.cart_product_price);
            productTotal = (TextView) itemView.findViewById(R.id.cart_product_total);
        }

    }

}

