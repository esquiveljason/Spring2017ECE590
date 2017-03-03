package com.ece2161android.layout;
//The package name, mostly is the inverse sequence of the domain name.

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityMain extends Activity {
	OnClickListener listener0 = null;
	OnClickListener listener1 = null;
	OnClickListener listener2 = null;
	OnClickListener listener3 = null;
	Button button0;
	Button button1;
	Button button2;
	Button button3;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//This is a defined format for the start.
		

		listener0 = new OnClickListener() {
			public void onClick(View v) {
				setTitle("RelativeLayout");
				Intent intent1 = new Intent(ActivityMain.this, ActivityRelativeLayout.class);
				startActivity(intent1);
			}
		};
		//Here several listeners are set to connect the logic of ActivityMain to other .java files. When the intentX is triggered, the activity will jump from ActivityMain to others. 
		
		listener1 = new OnClickListener() {
			public void onClick(View v) {
				setTitle("InteractiveLayout");
				Intent intent2 = new Intent(ActivityMain.this, ActivityLayout.class);
				startActivity(intent2);
			}
		};
		
		listener2 = new OnClickListener() {
			public void onClick(View v) {
				setTitle("TableLayout");
				Intent intent3 = new Intent(ActivityMain.this, ActivityTableLayout.class);
				startActivity(intent3);
			}
		};
		
		listener3 = new OnClickListener() {
			public void onClick(View v) {
				Intent intent0 = new Intent(ActivityMain.this, ActivityFrameLayout.class);
				setTitle("FrameLayout");
				startActivity(intent0);
			}
		};
				
		setContentView(R.layout.main);
		//Define the UI layout content with main.xml.
		button0 = (Button) findViewById(R.id.button0);
		button0.setOnClickListener(listener0);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(listener1);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(listener2);
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(listener3);
		//Here set the activity view with setContentView.
		//The buttons is connected to the id of buttons in main.xml.
		//They are also linked into the listener as triggers.
	}
    
}