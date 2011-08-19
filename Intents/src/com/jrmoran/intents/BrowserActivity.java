package com.jrmoran.intents;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class BrowserActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
		
		String url = getIntent().getDataString();
		WebView webView = (WebView)findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url);
	}
}

