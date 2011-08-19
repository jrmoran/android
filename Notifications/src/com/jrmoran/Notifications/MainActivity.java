package com.jrmoran.Notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnNotify = (Button)findViewById(R.id.btnNotify);
        btnNotify.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				/*
				 * The notification will be launched using an intent. It will be identified
				 * by the key notiId.
				 * 
				 * Since this code is inside of an anonymous OnClickListener class, the 
				 * context of this activity is reference by MainActivity.this instead
				 * of the keyword *this*.
				 * 
				 * When the user clicks on a notification the NotificationView activity
				 * will be loaded
				 */
				Intent intent = new Intent(MainActivity.this, NotificationView.class);
				intent.putExtra("notiId", 1);
				
				/*
				 * This is the notification's main information and is displayed in the 
				 * status bar
				 */
				Notification notification = new Notification(R.drawable.icon, 
						"This is a notification!",	System.currentTimeMillis());
				
				/*
				 * This PendingIntent objects allows us to persist a notification. 
				 * The notification will remain in the status bar until the user 
				 * opens even if our app is not running.
				 * 
				 * This PendingIntent object is obtained using getActivity()
				 */
				PendingIntent pIntent = 
						PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				
				/*
				 * These are the details of the notification, notice the PendingIntent object
				 * passed as fourth argument.
				 */
				notification.setLatestEventInfo(MainActivity.this, "You've been notified", 
						"These are the notification details.", pIntent);
				
				/*
				 * vibrate the phone and show the notification using a NotificationManager 
				 * object
				 */
				notification.vibrate = new long[] { 100, 200, 100, 400};
				NotificationManager nmr = 
						(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				nmr.notify(1, notification);
			}
		});
    }
}