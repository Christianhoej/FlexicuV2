package com.example.chris.flexicuv2.startskærm.indbakke.aftaler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Aftale;
import com.example.chris.flexicuv2.model.Forhandling;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.startskærm.indbakke.forhandling.Forhandling_indhold;


public class Forhandling_recyclerview extends RecyclerView.Adapter<Forhandling_recyclerview.ViewHolder>{

    private Context mContext;
    private Forhandling_indhold forhandling_indhold;
    private Singleton singleton;

    //TODO konstruktøren skal self have flere input når net er klar.
    public Forhandling_recyclerview(Context mContext) {
        this.mContext = mContext;
        singleton = Singleton.getInstance();
    }

    private int index = 0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.forhandlinger_recyclerview_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        forhandling_indhold = new Forhandling_indhold();

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull Forhandling_recyclerview.ViewHolder viewHolder, final int i) {
        //TODO setText

        //TODO setText for forhandling som lejer

        //TODO De to adaptere kan måske slås sammen, det kan undersøges når forhandlinger er sat helt op
        if(index<singleton.getAlleMineAftalerMedForhandling().size()) {

            if (i < singleton.getAlleMineAftalerMedForhandling().get(index).getForhandlinger().size()) {
                viewHolder.arbejdsområder.setText(singleton.getAlleMineAftalerMedForhandling().get(index).getMedarbejder().getArbejdsomraade());
                viewHolder.navn.setText(singleton.getAlleMineAftalerMedForhandling().get(index).getForhandlinger().get(i).getMedarbejder().getNavn());
                viewHolder.virksomhed.setText(singleton.getAlleMineAftalerMedForhandling().get(index).getUdlejer().getVirksomhedsnavn());
                viewHolder.periode.setText(singleton.getAlleMineAftalerMedForhandling().get(index).getForhandlinger().get(i).getUdlejerStartDato().replace(" ", "") + " - " + singleton.getAlleMineAftalerMedForhandling().get(index).getForhandlinger().get(i).getUdlejerSlutDato().replace(" ", ""));
                viewHolder.pris.setText(singleton.getAlleMineAftalerMedForhandling().get(index).getForhandlinger().get(i).getUdlejPris());

                if (singleton.getAlleMineAftalerMedForhandling().get(index).getUdlejer().getBrugerID().equals(singleton.getBruger().getBrugerID())) {
                    viewHolder.type.setText("Jeg er udlejer");
                } else {
                    viewHolder.type.setText("Jeg er lejer");
                }
            } else {
                index++;
            }
        }
        viewHolder.forhandlinger_aftaler_listitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(forhandling_indhold, i, index);
            }
        });
    }

    public void setFragment(Fragment fragment, int i, int index) {
        singleton.midlertidigAftale = singleton.getAlleMineAftalerMedForhandling().get(index);
        singleton.midlertidigForhandling = singleton.getAlleMineAftalerMedForhandling().get(index).getForhandlinger().get(i);
        //startskærmFrameTilDiverse.removeAllViews();
        FragmentTransaction fragmentTransaction = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskærm_frame_til_diverse, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    @Override
    public int getItemCount() {
        int antal=0;
        for(Aftale aftale : singleton.getAlleMineAftalerMedForhandling()){
            for(Forhandling forhandling : aftale.getForhandlinger()){
                antal++;
            }
        }
        return antal;
        //TODO retun forhandlingerSomLejer.size();
        //return singleton.getAftaler().size();
    }


    /**
     * Indre klasse
     * Opretter ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView arbejdsområder;
        TextView navn;
        TextView periode;
        TextView virksomhed;
        TextView pris;
        TextView type;
        RelativeLayout forhandlinger_aftaler_listitems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Type skal nok væk
            arbejdsområder = (TextView) itemView.findViewById(R.id.forhandlinger_arbejdsområder);
            navn = (TextView) itemView.findViewById(R.id.forhandlinger_navn);
            periode = (TextView) itemView.findViewById(R.id.forhandlinger_periode);
            virksomhed = (TextView) itemView.findViewById(R.id.forhandlinger_virk);
            pris = (TextView) itemView.findViewById(R.id.forhandlinger_pris);
            type = itemView.findViewById(R.id.forhandlinger_type);
            forhandlinger_aftaler_listitems = (RelativeLayout) itemView.findViewById(R.id.forhandlinger_recyclerview_listitem);
        }
    }
}
