package com.jrmoran.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	
	String tag = "Events";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(tag, "onCreate()");
    }
    protected void onStart() {
    	super.onStart();
    	Log.d(tag, "onStart()");
    }
    protected void onRestart() {
    	super.onRestart();
    	Log.d(tag, "onRestart()");
    }
	protected void onPause() {
		super.onPause();
		Log.d(tag, "onPause()");
	}
	protected void onResume() {
		super.onResume();
		Log.d(tag, "onResume()");
	}
	protected void onStop() {
		super.onStop();
		Log.d(tag, "onStop()");
	}
	protected void onDestroy() {
		super.onDestroy();
		Log.d(tag, "onDestroy()");
	}
}