package com.example.newsreader.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.newsreader.R;

public class WebActivity extends AppCompatActivity {
    private static final String ADDRESS_EXTRA = "address";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        webView = findViewById(R.id.web_view);
        WebChromeClient chromeClient = new WebChromeClient();
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getIntent().getStringExtra(ADDRESS_EXTRA));
    }

    public static Intent getIntentInstance(Context context, String address){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(ADDRESS_EXTRA, address);
        return intent;
    }
}
