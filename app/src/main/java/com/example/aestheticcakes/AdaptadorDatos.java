package com.example.aestheticcakes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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
        holder.productName.setText(dataList.get(position).getName());
        holder.productPrice.setText("Lps: " + dataList.get(position).getPrice().toString());

        Picasso.get().load(dataList.get(position).getImage()).into(holder.productImage);
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

        TextView productName, productPrice;
        ImageView productImage;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);
        }

    }
}
