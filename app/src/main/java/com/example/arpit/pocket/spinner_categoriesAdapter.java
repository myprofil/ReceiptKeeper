package com.example.arpit.pocket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class spinner_categoriesAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> lists;
    LayoutInflater inflater;
    String curRowVal;

    public spinner_categoriesAdapter(@NonNull Context context, int resource, ArrayList<String> lists) {
        super(context, resource, lists);
        this.context = context;
        this.lists = lists;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    public View getCustomView(int position, View view, ViewGroup parent){
        View r = inflater.inflate(R.layout.spinner_item, parent, false);
        curRowVal = (String) lists.get(position);
        TextView spinner_item = (TextView) r.findViewById(R.id.spinner_item);
        spinner_item.setText(curRowVal);
        return r;
    }
}
