package com.jasonesquivel.colormatch;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.content.Intent;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Jason Message:";
    String[] state = {"GRAY", "GRAY", "GRAY", "GRAY", "GRAY", "GRAY", "GRAY", "GRAY", "GRAY" } ;
    String[] target= {"BLUE", "GREEN", "RED", "GREEN", "GREEN", "RED", "GREEN", "BLUE", "BLUE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            state = savedInstanceState.getStringArray("state");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button targetButton = (Button) findViewById(R.id.targetbutton);
        targetButton.setOnClickListener(this);

        Button b0 = (Button) findViewById(R.id.button0);
        b0.setOnClickListener(this);

        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(this);

        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(this);

        Button b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(this);

        Button b5 = (Button) findViewById(R.id.button5);
        b5.setOnClickListener(this);

        Button b6 = (Button) findViewById(R.id.button6);
        b6.setOnClickListener(this);

        Button b7 = (Button) findViewById(R.id.button7);
        b7.setOnClickListener(this);

        Button b8 = (Button) findViewById(R.id.button8);
        b8.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.targetbutton:
                Intent intent=new Intent(MainActivity.this,TargetActivity.class);
                startActivityForResult(intent, 1);
                Log.i(TAG, "Target Button Pressed");
                break;
            case R.id.button0:
                buttonAction(R.id.button0,0);
                Log.i(TAG, "Button 0 Pressed");
                break;
            case R.id.button1:
                buttonAction(R.id.button1,1);
                Log.i(TAG, "Button 1 Pressed");
                break;
            case R.id.button2:
                buttonAction(R.id.button2,2);
                Log.i(TAG, "Button 2 Pressed");
                break;
            case R.id.button3:
                buttonAction(R.id.button3,3);
                Log.i(TAG, "Button 3 Pressed");
                break;
            case R.id.button4:
                buttonAction(R.id.button4,4);
                Log.i(TAG, "Button 4 Pressed");
                break;
            case R.id.button5:
                buttonAction(R.id.button5,5);
                Log.i(TAG, "Button 5 Pressed");
                break;
            case R.id.button6:
                buttonAction(R.id.button6,6);
                Log.i(TAG, "Button 6 Pressed");
                break;
            case R.id.button7:
                buttonAction(R.id.button7,7);
                Log.i(TAG, "Button 7 Pressed");
                break;
            case R.id.button8:
                buttonAction(R.id.button8,8);
                Log.i(TAG, "Button 8 Pressed");
                break;
        }
    }

    private void buttonAction(int buttonId, int stateId){
        Button work = (Button) findViewById(buttonId);
        Log.i(TAG, "Button id "+ buttonId + "State id" + stateId );
        Log.i(TAG, state.toString());
        if(state[stateId].equals("RED")) {
            state[stateId] = "BLUE";
            work.setBackgroundColor(Color.BLUE);
            work.setTextColor(Color.WHITE);
        }
        else if(state[stateId].equals("BLUE")) {
            state[stateId] = "GREEN";
            work.setBackgroundColor(Color.GREEN);
            work.setTextColor(Color.BLACK);
        }
        else if(state[stateId].equals("GREEN") || state[stateId].equals("GRAY")) {
            state[stateId] = "RED";
            work.setBackgroundColor(Color.RED);
            work.setTextColor(Color.BLACK);
        }

        showSuccess(checkTarget());
    }

    private Boolean checkTarget(){
        boolean success = true;
        for(int i = 0; i < state.length; i++){
            if(!state[i].equals(target[i])){
                success = false;
            }
        }
        return success;
    }

    private void showSuccess(Boolean success){
        if(success) {
            TextView text = (TextView) findViewById(R.id.success);
            text.setText("Success!!!");
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray("state", state);
        Log.i(TAG, "onSaveInstanceState");
    }
}
