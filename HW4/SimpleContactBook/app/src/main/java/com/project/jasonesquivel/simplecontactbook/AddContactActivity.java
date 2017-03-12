package com.project.jasonesquivel.simplecontactbook;

import android.app.Activity;
import android.os.Bundle;

import android.widget.*;
import android.view.View;
import android.content.Intent;

/**
 * Created by Jason Esquivel on 3/7/2017.
 */

public class AddContactActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(addListener);
    }

    private Button.OnClickListener addListener = new Button.OnClickListener(){

        public void onClick(View v){
            EditText firstNameET = (EditText) findViewById(R.id.firstname_add);
            EditText lastNameET = (EditText) findViewById(R.id.lastname_add);
            EditText phoneNumberET = (EditText) findViewById(R.id.phonenumber_add);
            EditText emailET = (EditText) findViewById(R.id.email_add);

            String name = firstNameET.getText().toString().trim() + " " + lastNameET.getText().toString().trim();
            String phoneNumber = phoneNumberET.getText().toString().trim();
            String email = emailET.getText().toString().trim();

            Intent i = new Intent( AddContactActivity.this, MainActivity.class);
            i.putExtra("name", name);
            i.putExtra("phoneNumber", phoneNumber);
            i.putExtra("email", email);

            setResult(Activity.RESULT_OK,i);
            finish();
        }
    };


}
