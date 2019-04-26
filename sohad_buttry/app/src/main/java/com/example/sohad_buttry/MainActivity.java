package com.example.sohad_buttry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mBatInfoReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent i) {
            int level = i.getIntExtra("level",0);
            ProgressBar pd = (ProgressBar)findViewById(R.id.prog);
            pd.setProgress(level);
            TextView tv=(TextView)findViewById(R.id.test);
            tv.setText("Battery Level: "+Integer.toString(level)+"%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(mBatInfoReciver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
