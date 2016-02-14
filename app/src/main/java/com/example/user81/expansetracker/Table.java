package com.example.user81.expansetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Table extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        ListView list=(ListView)findViewById(R.id.table);
        Catagories categories=(Catagories)getIntent().getSerializableExtra("data");

        ArrayList<TableData> table=new ArrayList<>();

        for(int i=0;i<categories.catagories.size();i++){
            table.add(new TableData(categories.catagories.get(i),categories.spendings.get(i)));
        }

        list.setAdapter(new Adapting(this,table));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table, menu);
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

    public void home(View view) {
        startActivity(new Intent(this,Status.class));
    }
}
