package com.idevs.webviewfw;

import android.content.Context;
import android.util.Base64;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.InputStream;
import java.util.List;

public class WVHandler {

    WebView webView = null;

    public WVHandler(final Context context, final WebView webView, boolean jsEnabled, boolean appCacheEnabled, boolean databaseEnabled, boolean domStorageEnabled, boolean horizontalScrollEnabled, final List<String> jsFiles, final List<String> cssFiles, final String jsScript, String cssStyle) {

        this.webView = webView;
        this.webView.setWebChromeClient(new WebChromeClient());

        this.webView.getSettings().setJavaScriptEnabled(jsEnabled);
        this.webView.getSettings().setAppCacheEnabled(appCacheEnabled);
        this.webView.getSettings().setDatabaseEnabled(databaseEnabled);
        this.webView.getSettings().setDomStorageEnabled(domStorageEnabled);
        this.webView.setHorizontalScrollBarEnabled(horizontalScrollEnabled);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if(jsScript != null) {
                   injectJS(jsScript);
                }

                for (String fileName : jsFiles) {
                    injectJSFile(fileName,context);
                }

                for (String fileName : cssFiles) {
                    injectCSSFile(fileName,context);
                }
            }

        });
    }

    public void injectCSSFile(String cssFile, Context context) {

        try {
            InputStream inputStream = context.getAssets().open(cssFile);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);

            webView.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void injectJS(String jsScript){
        webView.loadUrl("javascript:" + jsScript);
    }

    public void injectJSFile(String jsFile, Context context) {

        try {
            InputStream inputStream = context.getAssets().open(jsFile);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);

            webView.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var script = document.createElement('script');" +
                    "script.type = 'text/javascript';" +
                    "script.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(script)" +
                    "})()");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkAndGoBack(){
        if(this.webView.canGoBack()) {
            this.webView.goBack();
        }
    }

    public void goTo(String url) {
        this.webView.loadUrl(url);
    }

}
