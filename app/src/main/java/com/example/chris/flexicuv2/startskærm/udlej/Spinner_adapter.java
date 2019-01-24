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

/**
 *
 * @Author Christian
 *
 */
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
        return true;
    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        return view;
    }

    public Medarbejder getItem(int position){
        return list.get(position);
    }
}
