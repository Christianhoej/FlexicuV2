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
import com.example.chris.flexicuv2.startskærm.indbakke.forhandling.Forhandling_som_udlejer;

import java.util.ArrayList;


public class Forhandling_som_udlejer_recyclerview_adapter extends RecyclerView.Adapter<Forhandling_som_udlejer_recyclerview_adapter.ViewHolder>{

    private Context mContext;
    private Forhandling_som_udlejer forhandlingSomUdlejerFragment;
    private Singleton singleton;

    //TODO konstruktøren skal self have flere input når net er klar.
    public Forhandling_som_udlejer_recyclerview_adapter(Context mContext) {
        this.mContext = mContext;
        singleton = Singleton.getInstance();
    }

    private ArrayList typeARR;
    private ArrayList navnARR;
    private ArrayList virkARR;
    private int index=0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.forhandlinger_recyclerview_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        forhandlingSomUdlejerFragment = new Forhandling_som_udlejer();

        typeARR = new ArrayList();
        navnARR = new ArrayList();
        virkARR = new ArrayList();


        typeARR.add("UDLEJER");
        typeARR.add("Smed");
        navnARR.add("Gunn");
        navnARR.add("Janus");
        virkARR.add("Hanoigrh");
        virkARR.add("HANSEN COCO");



        return holder;
    }




    @Override
    public void onBindViewHolder(@NonNull Forhandling_som_udlejer_recyclerview_adapter.ViewHolder viewHolder, int i) {
        //TODO setText

        //TODO setText for forhandling som udlejer
        if(i <singleton.getMineUdlejAftalerMedForhandling().get(index).getForhandlinger().size()) {
            viewHolder.type.setText(singleton.getMineUdlejAftalerMedForhandling().get(index).getMedarbejder().getArbejdsomraade());
            viewHolder.navn.setText(singleton.getMineUdlejAftalerMedForhandling().get(index).getMedarbejder().getNavn());
            viewHolder.virksomhed.setText(singleton.getMineUdlejAftalerMedForhandling().get(index).getForhandlinger().get(i).getLejer().getVirksomhedsnavn());
            viewHolder.periode.setText(singleton.getMineUdlejAftalerMedForhandling().get(index).getForhandlinger().get(i).getUdlejerStartDato().replace(" ", "") + " - " + singleton.getMineUdlejAftalerMedForhandling().get(index).getForhandlinger().get(i).getUdlejerSlutDato().replace(" ", ""));
            viewHolder.pris.setText(singleton.getMineUdlejAftalerMedForhandling().get(index).getForhandlinger().get(i).getUdlejPris());
        } else {
            index++;
        }



        viewHolder.forhandlinger_aftaler_listitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO her skal fragmentet med hele aftalen vises.
                setFragment(forhandlingSomUdlejerFragment);
            }
        });
    }

    public void setFragment(Fragment fragment) {
        //startskærmFrameTilDiverse.removeAllViews();
        FragmentTransaction fragmentTransaction = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskærm_frame_til_diverse, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    @Override
    public int getItemCount() {
        int antal=0;
        for(Aftale aftale : singleton.getMineUdlejAftalerMedForhandling()){
            for(Forhandling forhandling : aftale.getForhandlinger()){
                antal++;
            }
        }
        return antal;
        //TODO retun forhandlingerSomUdlejer.size();
        //return singleton.getAftaler().size();
    }


    /**
     * Indre klasse
     * Opretter ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView type;
        TextView navn;
        TextView periode;
        TextView virksomhed;
        TextView pris;
        RelativeLayout forhandlinger_aftaler_listitems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Type skal nok væk
            type = (TextView) itemView.findViewById(R.id.forhandlinger_arbejdsområder);
            navn = (TextView) itemView.findViewById(R.id.forhandlinger_navn);
            periode = (TextView) itemView.findViewById(R.id.forhandlinger_periode);
            virksomhed = (TextView) itemView.findViewById(R.id.forhandlinger_virk);
            pris = (TextView) itemView.findViewById(R.id.forhandlinger_pris);
            forhandlinger_aftaler_listitems = (RelativeLayout) itemView.findViewById(R.id.forhandlinger_recyclerview_listitem);
        }
    }
}
