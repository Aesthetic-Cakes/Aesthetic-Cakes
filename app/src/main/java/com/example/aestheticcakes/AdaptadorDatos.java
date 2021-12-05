package com.example.aestheticcakes;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDatos>
                            implements View.OnClickListener{



    ArrayList<Producto> dataList;
    private View.OnClickListener listener;

    public AdaptadorDatos(ArrayList<Producto> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list,null,false);

        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        String rate = "";
        int noRate = 0;

        holder.productName.setText(dataList.get(position).getName());
        holder.productPrice.setText("Lps: " + dataList.get(position).getPrice().toString());


        for (int i = 0; i < dataList.get(position).getProductRate(); i++){
            rate += "★";
            noRate += 1;
        }

        for (int j = 0; j < 5 - noRate; j++){
            rate += "☆";
        }

        holder.productRating.setText(rate);

        Picasso.get().load(dataList.get(position).getImage()).fit().centerInside().noFade().placeholder(R.drawable.clock).into(holder.productImage);

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

        TextView productName, productPrice, productRating;
        ImageView productImage;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
            productRating = (TextView) itemView.findViewById(R.id.productRate);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);
        }

    }
}
