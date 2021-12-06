package com.example.aestheticcakes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.interfaces.ItemClickListener;
//import com.example.aestheticcakes.ItemClickListener;

public class CarritoView extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView carProductoNombre, carProductoCantidad, carProductoPrecio;
    private ItemClickListener itemClickListener;

    public CarritoView(@NonNull View itemView) {
        super(itemView);
        /*carProductoNombre = itemView.findViewById(R.id.cart_product_name);
        carProductoCantidad = itemView.findViewById(R.id.cart_product_price);
        carProductoPrecio =itemView.findViewById(R.id.cart_product_quantity);*/
    }

    @Override
    public void onClick(View view) {

        //itemClickListener.onClick(view, getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
