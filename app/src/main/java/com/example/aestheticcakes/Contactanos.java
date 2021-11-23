package com.example.aestheticcakes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.aestheticcakes.NavMenuOptions;

import java.io.InputStream;
import java.net.URL;

public class Contactanos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);

        WebView web = (WebView) findViewById(R.id.mapa); //Objeto pantalla
        web.setWebViewClient(new MyWebViewClient());
        WebSettings settings = web.getSettings(); // Variable websetting
        settings.setJavaScriptEnabled(true); //Habilitar JavaScript
        web.loadUrl("file:///android_asset/mapa.html");

    }

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

    //Inicio de los metodos del menu

    //Fin de los metodos del menu

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}