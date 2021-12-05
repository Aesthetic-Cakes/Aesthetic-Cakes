package com.example.aestheticcakes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.interfaces.ItemClickListener;

public class CarritoView extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ItemClickListener itemClickListener;
    public TextView carProductoNombre, carProductoCantidad, carProductoPrecio;
    public CarritoView(@NonNull View itemView) {
        super(itemView);
        carProductoNombre = itemView.findViewById(R.id.txtNombre);
        carProductoCantidad = itemView.findViewById(R.id.txtCantidad);
        carProductoPrecio =itemView.findViewById(R.id.txtPrecio);
    }

    @Override
    public void onClick(View view) {

    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
}
