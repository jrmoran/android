package com.jrmoran.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Using class generated from res/layout/main2.xml
		setContentView(R.layout.main2);
		
		
		Button btnOk = (Button)findViewById(R.id.btnOk);
		btnOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
				String username = txtUsername.getText().toString();
				
				/*
				 * Returning data from an Intent
				 * 
				 * setResult() sends the data back to the origin, the constants 
				 * RESULT_OK or RESULT_CANCELLED are needed along an Intent object
				 * containing the data
				 */			
				Intent intent = new Intent();
				intent.setData(Uri.parse(username));
				setResult(RESULT_OK, intent);
				
				// Finish this activity and returns to previous one
				finish();
				
			}
		});
	}
	
}
