package com.jrmoran.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	String tag = "Events";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(tag, "onCreate()");
    }
    
    // Callbacks for standard activities' life cycle events    
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
	
	// Linking to Activity2 using an Intent
	public boolean onKeyDown(int keyCode, KeyEvent event){
		
		if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
			
			// starting activity by its intent filter name as defined in the manifest file
			startActivity(new Intent("com.jrmoran.Activity2"));
		}
		
		if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
			// starting activity by its class name. This is limited to activities within 
			// the same project
			startActivity(new Intent(this, Activity2.class));
		}
		
		if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
			/*
			 * Get data from another activity, this request is identified by an integer 1
			 * 
			 * If the request code is set to -1 no result is obtained. 
			 * This is the equivalent startActivity()
			 */
			startActivityForResult(new Intent(this, Activity2.class), 1);
		}
		return false;
	}
	
	// Callback to handle data returned from another activity
	public void onActivityResult(int reqCode, int resCode, Intent intent){
		if(reqCode == 1 && resCode == RESULT_OK){
			Toast.makeText(this, intent.getData().toString(), Toast.LENGTH_SHORT).show();
		}
	}
}