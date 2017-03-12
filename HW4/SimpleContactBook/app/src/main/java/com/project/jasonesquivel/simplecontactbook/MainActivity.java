package com.project.jasonesquivel.simplecontactbook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper mOpenHelper;
    //The information about the SQLite Database.
    private static final String DATABASE_NAME = "ContactBook.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "ContactBook";
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIL = "email";
    private static final String TAG = "Main Activity Message:";

    public static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE " + TABLE_NAME + " ("
                    + NAME + " text not null, "
                    + PHONE_NUMBER + " text not null, "
                    + EMAIL + " text not null "+ ");";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
        private boolean isExists(String name)
        {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE name = '" + name + "'", null);
            boolean exist = (cur.getCount() > 0);
            cur.close();
            db.close();
            return exist;
        }
        private void addContact(String name, String phoneNumber, String email){

            if(isExists(name))
                Log.i(TAG, name + " exists");
            else if(name.equals(null) || name.equals("") || (name.trim().length() == 0))
                Log.i(TAG, "Name is Empty");
            else {
                SQLiteDatabase db = this.getWritableDatabase();

                ContentValues contact = new ContentValues();
                contact.put(NAME, name);
                contact.put(PHONE_NUMBER, phoneNumber);
                contact.put(EMAIL, email);

                db.insert(TABLE_NAME, null, contact);
                db.close();
                Log.i(TAG, name + " Added");
            }
        }
        private void deleteContact(String name){

                try{
                    SQLiteDatabase db = this.getWritableDatabase();
                    db.delete(TABLE_NAME, " name = '" + name + "'", null);
                    db.close();
                }
                catch(SQLException e){
                }
        }

        private ArrayList<String> getAllContacts() {
            ArrayList<String> contactList = new ArrayList<>();
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_NAME ;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    String temp = cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) ;
                    // Adding contact to list
                    contactList.add(temp);
                } while (cursor.moveToNext());
            }

            // return contact list
            Collections.sort(contactList, String.CASE_INSENSITIVE_ORDER);
            return contactList;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenHelper = new DatabaseHelper(this);

        Button addButton = (Button) findViewById(R.id.add_main);
        addButton.setOnClickListener(this);
        Button delButton = (Button) findViewById(R.id.del_main);
        delButton.setOnClickListener(this);
        Log.i(TAG, "onCreate");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_main:
                Intent intentAdd = new Intent(MainActivity.this, AddContactActivity.class);
                startActivityForResult(intentAdd,1);
                Log.i(TAG, "Add Contact Button Pressed");
                break;
            case R.id.del_main:
                Intent intentDel = new Intent(MainActivity.this, DeleteContactActivity.class);
                startActivityForResult(intentDel,2);
                Log.i(TAG, "Del Contact Button Pressed");
                break;
        }
    }

    //Rebuild the Table in the Database.
    private void createTable() {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String sql = "CREATE TABLE " + TABLE_NAME + " ("
                + NAME + " text not null, "
                + PHONE_NUMBER + " text not null, "
                + EMAIL + " text not null "+ ");";

        try {
            db.execSQL("DROP TABLE IF EXISTS SQLtest");
            db.execSQL(sql);
            setTitle("Build the Table Successfully.");
        }
        catch (SQLException e) {
            setTitle("Build the Table failed");
        }
    }

    //Delete the Table into the Database.
    private void dropTable() {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String sql_cmd = "drop table " + TABLE_NAME;
        //Here is anther SQL command which is used to delete the table.
        try {
            db.execSQL(sql_cmd);
            setTitle("Delete the Table Successfully.");
        }
        catch (SQLException e) {
            setTitle("Delete the Table Failed.");
        }
        createTable();
        //Also the title of the window is set to indicate the status of the background SQL operation.
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.i(TAG, "onActivityResult");
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK ){
                String name = data.getStringExtra("name");
                String phoneNumber = data.getStringExtra("phoneNumber");
                String email = data.getStringExtra("email");
                mOpenHelper.addContact(name, phoneNumber, email);
            }
        }
        if(requestCode == 2){
            if(resultCode == Activity.RESULT_OK ){
                String name = data.getStringExtra("name");
                mOpenHelper.deleteContact(name);
            }
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

        ArrayList<String> list = mOpenHelper.getAllContacts();
        ListView myListView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        myListView.setAdapter(adapter);

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
        Log.i(TAG, "onSaveInstanceState");
    }
}
