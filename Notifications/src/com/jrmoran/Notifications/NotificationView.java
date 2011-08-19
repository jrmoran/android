package com.jrmoran.Notifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

public class NotificationView extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		
		/*
		 * This will run when a notification is clicked
		 */
		NotificationManager nmr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		nmr.cancel(getIntent().getExtras().getInt("notiId"));
	}
	
}
