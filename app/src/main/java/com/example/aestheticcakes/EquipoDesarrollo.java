package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class EquipoDesarrollo extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_desarrollo);

        //Variable del Scroll Bar //Aplicable a todas las activity
        navigationView = (NavigationView)findViewById(R.id.navView);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        }
        //Para añadir el scrollbar
        navigationView.bringToFront();
        navigationView.setVerticalScrollBarEnabled(true);
        //Fin de seccion del Scroll Bar //Aplicable a todas las activity

    }

    //Inicio de los metodos del menu //Aplicable a todas las activity
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        new RedireccionadorMenuLateral().redireccionador(id, this);
        return true;
    }

    //Fin de los metodos del menu //Aplicable a todas las activity
}