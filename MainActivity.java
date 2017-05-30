package com.example.ryan.practical5part1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public DatabaseManager test;
   public TextView id1;
   public TextView fName ;
   public TextView lName;
   public TextView dob ;
   public TextView gender;
    public EditText studentId;
    public EditText firstName;
    public EditText lastName;
    public EditText year;
    public EditText sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         id1 = (TextView) findViewById(R.id.textView);
         fName = (TextView) findViewById(R.id.textView2);
         lName = (TextView) findViewById(R.id.textView3);
         dob = (TextView) findViewById(R.id.textView4);
        gender = (TextView) findViewById(R.id.textView5);

        studentId = (EditText) findViewById(R.id.editText);
        firstName = (EditText) findViewById(R.id.editText2);
        lastName = (EditText) findViewById(R.id.editText3);
        year = (EditText) findViewById(R.id.editText4);
        sex = (EditText) findViewById(R.id.editText5);

        id1.setText("Student id: ");
        fName.setText("First Name: ");
        lName.setText("Last Name");
        dob.setText("DOB: ");
        gender.setText("Gender");

        id1.setVisibility(View.INVISIBLE);
        fName.setVisibility(View.INVISIBLE);
        lName.setVisibility(View.INVISIBLE);
        dob.setVisibility(View.INVISIBLE);
        gender.setVisibility(View.INVISIBLE);
        studentId.setVisibility(View.INVISIBLE);
        firstName.setVisibility(View.INVISIBLE);
        lastName.setVisibility(View.INVISIBLE);
        year.setVisibility(View.INVISIBLE);
        sex.setVisibility(View.INVISIBLE);
        findViewById(R.id.button).setVisibility(View.INVISIBLE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id  == R.id.viewRecord) {

            return listRow();
        }
        if (id  == R.id.addRec){
            id1.setVisibility(View.VISIBLE);
            fName.setVisibility(View.VISIBLE);
            lName.setVisibility(View.VISIBLE);
            dob.setVisibility(View.VISIBLE);
            gender.setVisibility(View.VISIBLE);
            studentId.setVisibility(View.VISIBLE);
            firstName.setVisibility(View.VISIBLE);
            lastName.setVisibility(View.VISIBLE);
            year.setVisibility(View.VISIBLE);
            sex.setVisibility(View.VISIBLE);
            findViewById(R.id.button).setVisibility(View.VISIBLE);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public void addRecord(View view){
        test = new DatabaseManager(this);
        test.addRow(Integer.parseInt(String.valueOf(studentId.getText())),firstName.getText().toString(), lastName.getText().toString(), Integer.parseInt(String.valueOf(year.getText())),sex.getText().toString());
        test.close();

    }
    public boolean listRow()
    {
       test = new DatabaseManager(this);
        TextView text = (TextView) findViewById(R.id.textView6) ;

        text.setText(test.getRows());

        test.close();

        return true;
    }


}