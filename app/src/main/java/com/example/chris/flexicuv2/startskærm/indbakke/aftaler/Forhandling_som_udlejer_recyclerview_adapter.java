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
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.startskærm.indbakke.forhandling.Forhandling_som_lejer;
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

        viewHolder.type.setText(singleton.getMineUdlejForhandlinger().get(i).getMedarbejder().getArbejdsomraade());
        viewHolder.navn.setText(singleton.getMineUdlejForhandlinger().get(i).getMedarbejder().getNavn());
        viewHolder.virksomhed.setText(singleton.getMineUdlejForhandlinger().get(i).getLejer().getVirksomhedsnavn());
        viewHolder.periode.setText(singleton.getMineUdlejForhandlinger().get(i).getStartDato().replace(" ", "") + " - " + singleton.getMineUdlejForhandlinger().get(i).getEndDato().replace(" ", ""));
        viewHolder.pris.setText(singleton.getMineUdlejForhandlinger().get(i).getPris());


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
        if(singleton.getMineUdlejForhandlinger()!=null){
            return singleton.getMineUdlejForhandlinger().size();
        }
        else{
            return 0;
        }

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
            type = (TextView) itemView.findViewById(R.id.forhandlinger_type);
            navn = (TextView) itemView.findViewById(R.id.forhandlinger_navn);
            periode = (TextView) itemView.findViewById(R.id.forhandlinger_periode);
            virksomhed = (TextView) itemView.findViewById(R.id.forhandlinger_virk);
            pris = (TextView) itemView.findViewById(R.id.forhandlinger_pris);
            forhandlinger_aftaler_listitems = (RelativeLayout) itemView.findViewById(R.id.forhandlinger_recyclerview_listitem);
        }
    }
}
