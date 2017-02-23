package com.formacion.juanjosecanotena.jjct_calculaimc;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by juanjosecanotena on 9/2/17.
 */


public class PaginaWebView extends Activity {

    public String url="http://www.who.int/dietphysicalactivity/factsheet_recommendations/es/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.webview);

        final TextView tvUrl = (TextView)findViewById(R.id.tvUrl);

        tvUrl.setText(url);

        final WebView webView = (WebView)findViewById(R.id.wv);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                url = request.getUrl().toString();
                tvUrl.setText(url);
                webView.loadUrl(url);
                return true;
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

}
