package com.example.aestheticcakes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class detalleProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        ImageSlider imageSlider = findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slider = new ArrayList<>();
        slider.add(new SlideModel(R.drawable.cupcakefruta,null));
        slider.add(new SlideModel(R.drawable.scupcakefrutados,null));
        slider.add(new SlideModel(R.drawable.scupcakefrutatres,null));

        imageSlider.setImageList(slider);
    }
}