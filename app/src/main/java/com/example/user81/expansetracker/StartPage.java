package com.example.user81.expansetracker;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;

public class StartPage extends AppCompatActivity {
    Catagories catagories;
    ListView listView;
    EditText input;
    ArrayAdapter<String> adapt;
    EditText newCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        input=(EditText)findViewById(R.id.monthly_limit);
        newCategory=(EditText)findViewById(R.id.new_category);
        catagories=new Catagories();
        listView=(ListView)findViewById(R.id.lists);

        populateList();
    }

    private void populateList() {
        ArrayList<String> listing=catagories.catagories;
        adapt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listing);
        listView.setAdapter(adapt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_page, menu);
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

    public void addCatagory(View view) {
        catagories.catagories.add(newCategory.getText().toString());
        catagories.spendings.add(0.0);
        populateList();
    }

    public void done(View view) throws IOException {
        catagories.limit=0.0+Integer.parseInt(input.getText().toString());
        File file=new File(getFilesDir(),"data.dat");
        new ObjectOutputStream(new FileOutputStream(file)).writeObject(catagories);
        Intent intent=new Intent(this,Status.class);
        startActivity(intent);
    }
}
