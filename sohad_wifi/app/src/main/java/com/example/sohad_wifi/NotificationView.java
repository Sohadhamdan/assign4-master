package com.example.sohad_wifi;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationView extends Activity {
    // Declare Variable
    String title;
    String text;
    TextView txtitle;
    TextView txtext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificationview);

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Dismiss Notification
        notificationmanager.cancel(0);

        // Retrive the data from MainActivity.java
        Intent i = getIntent();

        title = i.getStringExtra("title");
        text = i.getStringExtra("text");

        // Locate the TextView
        txtitle = (TextView) findViewById(R.id.title);
        txtext = (TextView) findViewById(R.id.text);

        // Set the data into TextView
        txtitle.setText(title);
        txtext.setText(text);
    }
}