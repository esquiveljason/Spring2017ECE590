package com.ece2161android.layout;

import android.app.Activity;
import android.os.Bundle;

public class ActivityFrameLayout extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);;
		setContentView(R.layout.activity_frame_layout);
	//This program directly deliver all the layout to the .xml file.
	}
}