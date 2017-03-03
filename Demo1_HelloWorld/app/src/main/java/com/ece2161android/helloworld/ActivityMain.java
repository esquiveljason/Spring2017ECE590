package com.ece2161android.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityMain extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv1 = new TextView(this);
        tv1.setText("foo");
        setContentView(tv1);
    }
}

