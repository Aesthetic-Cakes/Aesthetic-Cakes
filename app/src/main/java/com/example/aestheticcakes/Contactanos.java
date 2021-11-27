package com.example.aestheticcakes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.navigation.NavigationView;

public class Contactanos extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);

        //Variable del Scroll Bar //Aplicable a todas las activity
        navigationView = (NavigationView)findViewById(R.id.navView);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        }
        //Para añadir el scrollbar
        navigationView.bringToFront();
        navigationView.setVerticalScrollBarEnabled(true);
        //Fin de seccion del Scroll Bar //Aplicable a todas las activity

        WebView web = (WebView) findViewById(R.id.mapa); //Objeto pantalla
        web.setWebViewClient(new MyWebViewClient());
        WebSettings settings = web.getSettings(); // Variable websetting
        settings.setJavaScriptEnabled(true); //Habilitar JavaScript
        web.loadUrl("file:///android_asset/mapa.html");

    }

    //Inicio de los metodos del menu //Aplicable a todas las activity
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        new RedireccionadorMenuLateral().redireccionador(id, this);
        return true;
    }

    //Fin de los metodos del menu //Aplicable a todas las activity

    //Inicio de las redirecciones
    public void abrirWhatsApp(View view){
        abrirIntent("https://api.whatsapp.com/send?phone=50499998888");
    }

    public void abrirFacebook(View view){
        abrirIntent("fb://");
    }

    public void abrirTelefono(View view){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "50499998888", null)));
    }

    public void abrirInstagram(View view){
        abrirIntent("instagram://user?username=put_here_account");
    }

    private void abrirIntent(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    //Fin de las redirecciones

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}