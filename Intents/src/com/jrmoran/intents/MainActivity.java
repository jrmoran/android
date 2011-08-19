package com.jrmoran.intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button btnBrowser, btnCall, btnMap, btnContact, btnWebView;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnBrowser = (Button) findViewById(R.id.btnBrowser);
        btnBrowser.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				/*
				 * Visit a web site
				 * 
				 * Intents consist of actions and data, in this example the action is
				 * the constant ACTION_VIEW and the data is the url parsed as an Uri object
				 */
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://i.reddit.com/"));
				startActivity(intent);
			}
		});
        
        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				/*
				 * Dial a telephone number
				 * 
				 * Instead of using setData() we can pass an Uri object to the
				 * Intent constructor
				 */
				startActivity(new Intent(android.content.Intent.ACTION_DIAL,
										 Uri.parse("tel:+555555555")));
			}
		});
        
        btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				/*
				 * Show a Map
				 * AVD must support Google APIs
				 * 
				 * The geo scheme is used instead of http in the Uri object
				 */
				startActivity(new Intent(android.content.Intent.ACTION_VIEW,
										 Uri.parse("geo:40.742567,-73.987561")));
			}
		});
        
        btnContact = (Button) findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				/*
				 * Get a contact 
				 * 
				 * setType() specifies the expected data type of the return value, to
				 * view and select only contacts with phone numbers the return type should
				 * be set to ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE 
				 */
				Intent intent = new Intent(android.content.Intent.ACTION_PICK);
				intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
				startActivityForResult(intent, 1);
			}
		});
        
        btnWebView = (Button) findViewById(R.id.btnWebView);
        btnWebView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				/*
				 * Visiting a web site on a web view using intent filters in the manifest
				 * file
				 * 
				 * action name: com.jrmoran.Browser
				 * 
				 * category must be "android.intent.category.DEFAULT" so the activity can
				 * be called from other activities. 
				 * 
				 * Since the activity expects data to start with the prefix "http" the
				 * data scheme is set "http"
				 * 
				 * Because a web view requires the app to connect to the web, permission is
				 * required
				 * 
				 *     uses-permission: "android.permission.INTERNET"
				 */
				startActivity(new Intent("com.jrmoran.Browser", 
										  Uri.parse("http://i.reddit.com")));
			}
		});
    }
    
    /*
     * Callback for activities that return data
     */
    public void onActivityResult(int reqCode, int resCode, Intent intent){
    	if(reqCode == 1 && resCode == RESULT_OK){
    		/*
    		 *  when using ACTION_PICK, the contacts app will return a URL pointing to the
    		 *  selected contact.
    		 *  
    		 *      content://com.android.contacts/contacts/loopup/0r1...
    		 *      
    		 *  This will be shown in a Toast, but that doesn't tell us much, so a new 
    		 *  intent object is needed to render it
    		 */
    		String url = intent.getDataString();
    		Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
    		startActivity(new Intent(android.content.Intent.ACTION_VIEW,
    					  Uri.parse(url)));
    	}
    }
}