package com.idevs.webviewfw;


import android.content.Context;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

public class WVBuilder {

    private WebView webView = null;

    private boolean jsEnabled = true;
    private boolean appCacheEnabled = true;
    private boolean databaseEnabled = true;
    private boolean domStorageEnabled = true;
    private boolean horizontalScrollEnabled = true;

    private List<String> jsFiles = new ArrayList<>();
    private List<String> cssFiles = new ArrayList<>();

    private String jsScript;
    private String cssStyle;


    public WVHandler build(Context context){
        return new WVHandler(context,webView,jsEnabled,appCacheEnabled,databaseEnabled,domStorageEnabled,horizontalScrollEnabled,jsFiles,cssFiles,jsScript,cssStyle);
    }

    public WVBuilder(WebView webView){
        this.webView = webView;
    }


    public boolean isJsEnabled() {
        return jsEnabled;
    }

    public WVBuilder setJsEnabled(boolean jsEnabled) {
        this.jsEnabled = jsEnabled;
        return this;
    }

    public boolean isAppCacheEnabled() {
        return appCacheEnabled;
    }

    public WVBuilder setAppCacheEnabled(boolean appCacheEnabled) {
        this.appCacheEnabled = appCacheEnabled;
        return this;
    }

    public boolean isDatabaseEnabled() {
        return databaseEnabled;
    }

    public WVBuilder setDatabaseEnabled(boolean databaseEnabled) {
        this.databaseEnabled = databaseEnabled;
        return this;
    }

    public boolean isDomStorageEnabled() {
        return domStorageEnabled;
    }

    public WVBuilder setDomStorageEnabled(boolean domStorageEnabled) {
        this.domStorageEnabled = domStorageEnabled;
        return this;
    }

    public boolean isHorizontalScrollEnabled() {
        return horizontalScrollEnabled;
    }

    public WVBuilder setHorizontalScrollEnabled(boolean horizontalScrollEnabled) {
        this.horizontalScrollEnabled = horizontalScrollEnabled;
        return this;
    }

    public List<String> getJsFiles() {
        return jsFiles;
    }

    public WVBuilder setJsFiles(List<String> jsFiles) {
        this.jsFiles = jsFiles;
        return this;
    }

    public List<String> getCssFiles() {
        return cssFiles;
    }

    public WVBuilder setCssFiles(List<String> cssFiles) {
        this.cssFiles = cssFiles;
        return this;
    }

    public String getJsScript() {
        return jsScript;
    }

    public WVBuilder setJsScript(String jsScript) {
        this.jsScript = jsScript;
        return this;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public WVBuilder setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
        return this;
    }
}
