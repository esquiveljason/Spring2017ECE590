package com.ece2161android.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ActivityLayout extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout layoutMain = new LinearLayout(this);
		layoutMain.setOrientation(LinearLayout.HORIZONTAL);
		//Same function in linear layout, however the java presentation is kind different from XML.
		//In this mixed layout, two groups are linearly aligned and each object in group is aligned relatively to each other.
		
		setContentView(layoutMain);
		//With setContentview, the layout file of .xml will be linked into the logic file of .java.
		LayoutInflater Linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//Here we can grab LayoutInflater named as Linflater to analysis the XML layout based on LAYOUT_INFLATER_SERVICE to create a view named as RelativeLayout. 
		RelativeLayout layoutLeft = (RelativeLayout) Linflater.inflate(R.layout.left, null);
		RelativeLayout layoutRight = (RelativeLayout) Linflater.inflate(R.layout.right, null);
		//Using LayoutInflater, the right.xml and left.xml is analyzed to create to RelativeLayout layoutLeft and layoutRight which will assemble a new view.
	
		RelativeLayout.LayoutParams relParam = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		//LayoutParams is used to describe the absolute position of the layout content.
		//Here, the relParam is defined as a RlativeLayout.LayoutParams array with both WRAP_CONTENT method.
		//For LayoutParams there are three kinds of assignment: exact number, FILL_PARENT and WRAP_CONTENT.
		//FILL_PARENT will make the view as big as its higher level container, which has minimal padding or margin area.
		//WRAP_CONTENT will make the view just fix its content.
		
		layoutMain.addView(layoutLeft, 100, 100);
		layoutMain.addView(layoutRight, relParam);
		//Using addView, two RelatvieLayouts is added into layoutMain.
		//layoutMain.addView(layoutLeft, 100, 100);--the first 100 means width, and the second means length. The 100 without any units is also from LayoutParams as  pixel constant.
		
		//layoutMain.addView(layoutRight, relParam);--just use relParam defined before to adjust the content.
		//Here, the two created RelativeLayouts are assembled.
		
		//From this code we can see that the UI layout can be also implemented in a logic level.
	}
}