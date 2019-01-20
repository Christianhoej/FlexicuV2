package com.example.chris.flexicuv2.startskærm.udlej;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chris.flexicuv2.model.Medarbejder;

import java.util.ArrayList;
import java.util.List;

public class Spinner_adapter extends ArrayAdapter {
    List<Medarbejder> list ;
    private Context mContext;

    public Spinner_adapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.list = objects;

    }

    @Override
    public boolean isEnabled(int position) {
        if (position == 0) {
            // Disable the first item from Spinner
            // First item will be use for hint
            return false;
        } else {
            return true;
        }
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if (position == 0) {
            // Set the hint text color gray
            tv.setTextColor(Color.GRAY);
        } else {
            tv.setTextColor(Color.BLACK);
        }
        return view;
    }

    /*@Override
    public Medarbejder getItem(int position) {
        if(position>0)
            return (Medarbejder)super.getItem(position);
        else
            return null;
    }*/
    public Medarbejder getItem(int position){
        return list.get(position);
    }



}
