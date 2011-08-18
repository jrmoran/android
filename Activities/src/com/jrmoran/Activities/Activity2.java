package com.jrmoran.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends Activity {

	private EditText txtUsername;
	private Button btnOk;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Using class generated from res/layout/main2.xml
		setContentView(R.layout.main2);
		
		
		btnOk = (Button)findViewById(R.id.btnOk);
		txtUsername = (EditText) findViewById(R.id.txtUsername);
		
		/*
		 * Get data from original Activity
		 * 
		 * Extract bundle object from origin intent
		 */
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			txtUsername.setHint(extras.getString("Name"));
		}
		
		btnOk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String username = txtUsername.getText().toString();
				
				/*
				 * Returning data to the origin activity using Intents
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
