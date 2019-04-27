package com.example.sohad_wifi;

import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
    // Declare Variables
    ToggleButton wifi;
    CheckBox brbox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Locate the ToggleButton in activity_main.xml
        wifi = (ToggleButton) findViewById(R.id.wifitoggle);

        // Locate the CheckBox in activity_main.xml
        brbox = (CheckBox) findViewById(R.id.brcheckbox);

        // WifiManager to control the Wifi Service
        final WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // Capture ToggleButton clicks
        wifi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (wifi.isChecked()) {
                    // Switch On Wifi
                    wifiManager.setWifiEnabled(true);
                } else {
                    // Switch Off Wifi
                    wifiManager.setWifiEnabled(false);
                }
            }
        });

        // Capture CheckBox clicks
        brbox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (brbox.isChecked()) {
                    // Switch On Broadcast Receiver
                    PackageManager pm = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(
                            MainActivity.this, Manager.class);
                    pm.setComponentEnabledSetting(componentName,
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(),
                            "Broadcast Receiver Started", Toast.LENGTH_LONG)
                            .show();
                } else {
                    // Switch Off Broadcast Receiver
                    PackageManager pm = MainActivity.this.getPackageManager();
                    ComponentName componentName = new ComponentName(
                            MainActivity.this, Manager.class);
                    pm.setComponentEnabledSetting(componentName,
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                    Toast.makeText(getApplicationContext(),
                            "Broadcast Receiver Stopped", Toast.LENGTH_LONG)
                            .show();

                }
            }
        });
        // If Wifi already turned on switch ToggleButton to on
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            wifi.setChecked(true);
        }
    }

    // Not using options menu in this tutorial
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}