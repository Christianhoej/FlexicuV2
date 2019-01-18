package com.example.chris.flexicuv2.startskærm.hjem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Medarbejder;

import java.util.ArrayList;
import java.util.Collections;

public class RecyclerViewAdapter_Udlej extends RecyclerView.Adapter<RecyclerViewAdapter_Udlej.ViewHolder>  {

    private ArrayList<Medarbejder> mMedarbejder;
    private Context mContext;

    public RecyclerViewAdapter_Udlej(Context mContext, ArrayList<Medarbejder> mMedarbejder) {
        this.mMedarbejder = mMedarbejder;
        Collections.sort(mMedarbejder);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.udlej_listitem,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        String loen = "Løn: " + Integer.toString(mMedarbejder.get(i).getLoen());
        viewHolder.name.setText(mMedarbejder.get(i).getNavn());
        viewHolder.salary.setText(loen);
        viewHolder.workfield.setText(mMedarbejder.get(i).getArbejdsomraade());
    }



    @Override
    public int getItemCount() {
        return mMedarbejder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView period;
        private TextView salary;
        private TextView workfield;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.medarbejderNavn);
            period = itemView.findViewById(R.id.periode);
            salary = itemView.findViewById(R.id.salary);
            workfield = itemView.findViewById(R.id.workfield);
        }
    }
}
