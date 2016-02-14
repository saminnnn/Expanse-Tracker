package com.example.user81.expansetracker;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapting extends ArrayAdapter{
    public Adapting(Context context, ArrayList<TableData> object) {
        super(context, 0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_unit, null, false);

        TextView left=(TextView)view.findViewById(R.id.leftSide);
        TextView right=(TextView)view.findViewById(R.id.rightside);
        TableData tableData=(TableData)getItem(position);

        left.setText(tableData.category);
        right.setText(""+tableData.cost);

        return view;
    }
}
