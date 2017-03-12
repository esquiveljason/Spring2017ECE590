package com.hw4.jasonesquivel.simpletimer;

import android.content.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.*;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String msg = "Activity Main : ";
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, TimerService.class);
        startService(intent);
        Log.i(msg, "Started Service");

    }
    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(msg, "The onResume() event");

        IntentFilter intentFilter = new IntentFilter("timerFilter");
        mReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                //extract our message from intent
                String msg_for_me = intent.getStringExtra("time");
                //log our message value
                Log.i(msg, msg_for_me);
                TextView textView = (TextView)findViewById(R.id.time);
                textView.setText(msg_for_me);

            }
        };
        //registering our receiver
        this.registerReceiver(mReceiver, intentFilter);
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(msg, "The onPause() event");
        this.unregisterReceiver(mReceiver);
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        Intent intent = new Intent(this, TimerService.class);
        stopService(intent);
        Log.i("Main", "Started Service");
        super.onStop();
        Log.i(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(msg, "The onDestroy() event");
    }

}
