package com.example.user81.expansetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Status extends AppCompatActivity {
    TextView display;
    EditText input;
    Catagories categories;
    Spinner listing;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        file=new File(getFilesDir(),"data.dat");
        if(!file.exists()){
            Intent intent=new Intent(this,StartPage.class);
            startActivity(intent);
        }

        ObjectInputStream dataFile=null;

        try {
            dataFile=new ObjectInputStream(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dataFile != null) {
            try {
                categories=(Catagories)dataFile.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        display=(TextView)findViewById(R.id.home_display);
        input=(EditText)findViewById(R.id.spendings);
        listing=(Spinner)findViewById(R.id.catagory_list);
        listing.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,categories.catagories));

        update();
    }

    private void update() {
        double total=0.0;

        for (int i=0;i<categories.spendings.size();i++){
            total=total+categories.spendings.get(i);
        }

        display.setText("Balance is " + (categories.limit - total));
    }


    public void submit(View view) throws IOException {
        String selection=listing.getSelectedItem().toString();
        int index;

        for(index=0;index<categories.catagories.size();index++){
            if(selection.matches(categories.catagories.get(index))) break;
        }

        Double spending=0.0+Integer.parseInt(input.getText().toString());
        categories.addSpending(index,spending);
        new ObjectOutputStream(new FileOutputStream(file)).writeObject(categories);
        update();
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void reset(View view) {
        startActivity(new Intent(this,StartPage.class));
    }

    public void nothing(View view) {}

    public void tables(View view) {
        Intent intent=new Intent(this,Table.class);

        intent.putExtra("data",categories);

        startActivity(intent);
    }
}
