package com.example.chris.flexicuv2.startskærm.indbakke.aftaler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.concurrent.TimeoutException;


public class Ledige_recyclerview_adapter extends RecyclerView.Adapter<Ledige_recyclerview_adapter.ViewHolder>{

    private Context mContext;
    Singleton singleton;

    //TODO konstruktøren skal self have flere input når net er klar.
    public Ledige_recyclerview_adapter(Context mContext) {
        this.mContext = mContext;
        singleton = Singleton.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ledige_recyclerview_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull Ledige_recyclerview_adapter.ViewHolder viewHolder, int i) {
        //TODO setText

        //viewHolder.navn.setText(singleton.getMedarbejder().get(i).getNavn());
        viewHolder.navn.setText("Navn: " + singleton.getMineLedigeMedarbejdere().get(i).getMedarbejder().getNavn());
        viewHolder.periode.setText("Periode: " + singleton.getMineLedigeMedarbejdere().get(i).getStartDato() + " - " + singleton.getMineLedigeMedarbejdere().get(i).getEndDato());
        viewHolder.pris.setText("Timepris: " + singleton.getMineLedigeMedarbejdere().get(i).getPris() + "kr.");
        viewHolder.værktøj.setText("Medbriges eget værktøj? " + egetVærktøj(i));
        viewHolder.arbejdsområder.setText("Arbejdsområder: " + singleton.getMineLedigeMedarbejdere().get(i).getMedarbejder().getArbejdsomraade());

        viewHolder.ledige_aftaler_listitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO her skal fragmentet med hele aftalen vises, muligvis med mulighed for at kunne redigere.
            }
        });
    }

    public String egetVærktøj(int i){
        String egetVærktøj;
        if(singleton.getMineLedigeMedarbejdere().get(i).isEgetVærktøj()==false){
            egetVærktøj="Nej";
        }
        else
            egetVærktøj="Ja";

        return egetVærktøj;
    }

    @Override
    public int getItemCount() {
        //return 0;
        return singleton.getMineLedigeMedarbejdere().size();
    }


    /**
     * Indre klasse
     * Opretter ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView navn;
        TextView periode;
        TextView pris;
        TextView værktøj;
        TextView arbejdsområder;
        RelativeLayout ledige_aftaler_listitems;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            navn = (TextView) itemView.findViewById(R.id.ledige_navn);
            periode = (TextView) itemView.findViewById(R.id.ledige_periode);
            pris = (TextView) itemView.findViewById(R.id.ledige_pris);
            værktøj = itemView.findViewById(R.id.ledige_værktøj);
            arbejdsområder = itemView.findViewById(R.id.ledige_arbejdsområder);
            ledige_aftaler_listitems = (RelativeLayout) itemView.findViewById(R.id.ledige_recyclerview_listitem);
        }
    }
}
