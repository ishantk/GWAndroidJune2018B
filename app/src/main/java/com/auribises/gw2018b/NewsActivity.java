package com.auribises.gw2018b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends AppCompatActivity {

    WebView webView;
    WebViewClient client;

    void initViews(){

        webView = findViewById(R.id.webView);

        client = new WebViewClient();

        webView.setWebViewClient(client);
        webView.getSettings().setJavaScriptEnabled(true); // Turn On JS
        webView.loadUrl("https://aajtak.intoday.in/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setTitle("Aaj Tak");

        initViews();
    }
}
