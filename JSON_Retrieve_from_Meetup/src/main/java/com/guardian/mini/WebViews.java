package com.guardian.mini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class WebViews extends Activity {
	WebView mWebView;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mainwebview);
        Intent intent = getIntent();
        intent.getExtras();
        String urls = intent.getStringExtra("key1"); 

	    mWebView = (WebView) findViewById(R.id.webview);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    mWebView.loadUrl(urls);
	}

}
