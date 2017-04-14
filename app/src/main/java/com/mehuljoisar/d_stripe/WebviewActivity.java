package com.mehuljoisar.d_stripe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebviewActivity extends AppCompatActivity {

    private WebView wvBrowser;
    private Intent mIntent;
    private ProgressDialog pdLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        init();


    }

    private void init() {

        mIntent = getIntent();

        pdLoading = new ProgressDialog(WebviewActivity.this);
        pdLoading.setMessage("Loading...");
        pdLoading.setIndeterminate(true);
        wvBrowser = (WebView) findViewById(R.id.wvBrowser);

        wvBrowser.getSettings().setJavaScriptEnabled(true);
        wvBrowser.getSettings().setLoadWithOverviewMode(true);
        wvBrowser.getSettings().setUseWideViewPort(true);

        wvBrowser.setWebViewClient(new WebViewClient()
        {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url){
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pdLoading.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pdLoading.dismiss();

                if(url.contains(mIntent.getStringExtra("key"))){
                    String value = Uri.parse(url).getQueryParameter(mIntent.getStringExtra("key"));
                    setResult(RESULT_OK, new Intent().putExtra(mIntent.getStringExtra("key"),value));
                    finish();
                }
            }
        });

        wvBrowser.loadUrl(mIntent.getStringExtra("url"));
//        wvBrowser.loadUrl("http://www.google.com");
    }
}
