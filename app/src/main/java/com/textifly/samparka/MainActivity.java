package com.textifly.samparka;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.textifly.samparka.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.webview.getContext();
        WebSettings webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.webview.setWebViewClient(new MyBrowser());
        binding.webview.getSettings().setLoadsImagesAutomatically(true);
        binding.webview.loadUrl("http://samparkasources.in/");
        binding.webview.addJavascriptInterface(new JS(this),"JS");

        //binding.progressBar.setVisibility(View.GONE);
        //binding.webview.addJavascriptInterface(new Object());

        /*binding.webview.addJavascriptInterface(new Object()
        {
            @JavascriptInterface           // For API 17+
            public void performClick(String strl)
            {
                //stringVariable = strl;
                Toast.makeText (MainActivity.this, strl, Toast.LENGTH_SHORT).show();
            }
        }, "Login");*/

    }

    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            //Toast.makeText(MainActivity.this, "HI", Toast.LENGTH_SHORT).show();
            return super.shouldOverrideKeyEvent(view, event);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            binding.progressBar.setVisibility(View.GONE);

            String script = "var terms = document.querySelector('input.btn,button.btn'); " +
                    "terms.setAttribute('id','submit_btn');" +
                    "terms.setAttribute('onclick',`androidClick()`); " +
                    "function androidClick(){ JS.Getlink();}";

            binding.webview.evaluateJavascript(script, null);
        }
    }



    /*private class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }*/

    @Override
    public void onBackPressed() {
        if (binding.webview.canGoBack()) {
            binding.webview.goBack();
        } else {
            super.onBackPressed();
        }
    }
}