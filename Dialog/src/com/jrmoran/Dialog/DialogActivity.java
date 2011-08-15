package com.jrmoran.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DialogActivity extends Activity {
	
	CharSequence[] items = { "Google", "Apple", "Microsoft" };
	boolean[] itemsChecked = new boolean[items.length];
	
	private ProgressDialog progressDialog;
	private int progress;
	private Handler progressHandler;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnDialog = (Button)findViewById(R.id.btn_dialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// When pressed this will call the onCreateDialog() callback
				// The integer argument is used to identify which dialog is needed
				showDialog(0);
			}
		});
        
        Button btnProgressDialog = (Button) findViewById(R.id.btn_progressDialog);
        btnProgressDialog.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Display progress dialog, set progress and run handler 
				showDialog(1);
				progress = 0;
				progressDialog.setProgress(0);
				progressHandler.sendEmptyMessage(0);
			}
		});
        
        // This handler runs a background thread, which counts up to 100
        progressHandler = new Handler(){
        	public void handleMessage(Message msg){
        		super.handleMessage(msg);
        			if(progress >= 100){
        				progressDialog.dismiss();
        			}else{
        				progress++;
        				progressDialog.incrementProgressBy(1);
        				progressHandler.sendEmptyMessageDelayed(0, 100);
        			}
        	}
        };
    }
    
    // This is the callback used for creating dialogs managed by the activity
    @Override
    protected Dialog onCreateDialog(int id){
    	switch (id) {
		case 0:
			// Using the constructor Builder() of the class AlertDialog to create a Dialog
			return new AlertDialog.Builder(this)
			.setIcon(R.drawable.icon)
			.setTitle("This is a Dialog")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// Since the Toast class is being used inside the AlertDialog the method
					// getBaseContext() is needed to get the Context.
					//
					// Context can be obtained with the keyword *this* when used directly from 
					// the activity class because Activity is a subclass of Context
					Toast.makeText(getBaseContext(), 
							"OK pressed!", Toast.LENGTH_SHORT).show();
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getBaseContext(), 
							"Cancel pressed!", Toast.LENGTH_SHORT).show();
				}
			})
			.setMultiChoiceItems(items, itemsChecked, 
										new DialogInterface.OnMultiChoiceClickListener() {
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					Toast.makeText(getBaseContext(), 
							items[which] + (isChecked? " is checked" : " is unchecked"), 
							Toast.LENGTH_SHORT).show();
				}
			}).create();
		case 1:
			// creating progress dialog, buttons are set using the dialog interface
			progressDialog = new ProgressDialog(this);
			progressDialog.setIcon(R.drawable.icon);
			progressDialog.setTitle("Testing Progress...");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "hide", 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getBaseContext(), "Hiding!", Toast.LENGTH_SHORT).show();
					}
				});
			progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getBaseContext(), "Canceling", Toast.LENGTH_LONG).show();
					}
				});
			return progressDialog;
		}
		return null;
    }
}