package com.project.jasonesquivel.simplecontactbook;

import android.app.Activity;
import android.os.Bundle;

import android.widget.*;
import android.view.View;
import android.content.Intent;

/**
 * Created by Jason Esquivel on 3/7/2017.
 */

public class DeleteContactActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_contact);

        Button delButton = (Button) findViewById(R.id.del);
        delButton.setOnClickListener(delListener);
    }

    private Button.OnClickListener delListener = new Button.OnClickListener(){

        public void onClick(View v){
            EditText firstNameET = (EditText) findViewById(R.id.firstname_del);
            EditText lastNameET = (EditText) findViewById(R.id.lastname_del);
            String name = firstNameET.getText().toString().trim() + " " + lastNameET.getText().toString().trim();

            Intent i = new Intent( DeleteContactActivity.this, MainActivity.class);
            i.putExtra("name", name);

            setResult(Activity.RESULT_OK,i);
            finish();
        }
    };


}
