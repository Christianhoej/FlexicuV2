package com.example.chris.flexicuv2.startskærm.lej;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.ArrayList;
import java.util.Collections;

public class RecyclerViewAdapter_Ledig_Arbejdskraft extends RecyclerView.Adapter<RecyclerViewAdapter_Ledig_Arbejdskraft.ViewHolder>{

    private Context mContext;
    private Singleton singleton;

    public RecyclerViewAdapter_Ledig_Arbejdskraft(Context mContext) {
        this.mContext = mContext;
        singleton = Singleton.getInstance();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_Ledig_Arbejdskraft.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.lej_ledig_arbejdskraft_listitem,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String loen = "Løn: " + Integer.toString(singleton.getMedarbejdereTilUdlejning().get(i).getLoen());
        viewHolder.name.setText(singleton.getMedarbejdereTilUdlejning().get(i).getNavn());
        viewHolder.salary.setText(loen);
        viewHolder.workfield.setText(singleton.getMedarbejdereTilUdlejning().get(i).getArbejdsomraade());



    }


    @Override
    public int getItemCount() {
        if(singleton.getMedarbejdereTilUdlejning()!=null){
            return singleton.getMedarbejdereTilUdlejning().size();
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView salary;
        private TextView workfield;
        private TextView periode;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.medarbejderNavn);
            salary = itemView.findViewById(R.id.salary);
            periode = itemView.findViewById(R.id.periode);
            workfield = itemView.findViewById(R.id.workfield);
        }
    }
}
