package com.example.aestheticcakes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        //Animaciones

        Animation ani = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation ani2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        ImageView Logo = findViewById(R.id.imgLogo);
        TextView Label = findViewById(R.id.txtAestetick);

        Logo.setAnimation(ani);
        Label.setAnimation(ani2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}