package com.jasonesquivel.colormatch;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

/**
 * Created by Jason Esquivel on 2/26/2017.
 */

public class TargetActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        Button mainButton = (Button) findViewById(R.id.mainbutton);
        mainButton.setOnClickListener(mainListener);
    }
    private Button.OnClickListener mainListener = new Button.OnClickListener(){ //创建button listener
        public void onClick(View v){
            setResult(1);
            finish();
            //Log.i(TAG, "CAO");
        }
    };
}
