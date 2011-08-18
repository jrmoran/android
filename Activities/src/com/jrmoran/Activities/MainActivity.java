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
		
		if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
			
			// starting activity by its intent filter name as defined in the manifest file
			startActivity(new Intent("com.jrmoran.Activity2"));
		}
		
		if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
			// starting activity by its class name. This is limited to activities within 
			// the same project
			startActivity(new Intent(this, Activity2.class));
		}
		
		if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
			/*
			 * Get data from another activity, this request is identified by an integer 1
			 * 
			 * If the request code is set to -1 no result is obtained. 
			 * This is the equivalent startActivity()
			 */
			startActivityForResult(new Intent(this, Activity2.class), 1);
		}
		
		if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
			/*
			 * Pass data to the target activity
			 * 
			 * A bundle is a dictionary data structure that stores key/value pairs
			 * This is added to the intent that's passed to the target activity
			 */
			Intent intent = new Intent(this, Activity2.class);
			Bundle extras = new Bundle();
			extras.putString("Name", "Jaime");
			intent.putExtras(extras);
			startActivityForResult(intent, 1);
		}
		return false;
	}
	
	// Callback to handle data returned from another activity
	public void onActivityResult(int reqCode, int resCode, Intent intent){
		String msg = intent.getData().toString();
		if(reqCode == 1 && resCode == RESULT_OK && !msg.isEmpty()){
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
	}
}