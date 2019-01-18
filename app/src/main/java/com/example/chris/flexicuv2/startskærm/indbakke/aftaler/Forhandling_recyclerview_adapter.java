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
import com.example.chris.flexicuv2.startskærm.indbakke.forhandling.Forhandling;


public class Forhandling_recyclerview_adapter extends RecyclerView.Adapter<Forhandling_recyclerview_adapter.ViewHolder>{

    private Context mContext;
    private Forhandling forhandlingFragment;

    //TODO konstruktøren skal self have flere input når net er klar.
    public Forhandling_recyclerview_adapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.aftaler_recyclerview_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        forhandlingFragment = new Forhandling();
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull Forhandling_recyclerview_adapter.ViewHolder viewHolder, int i) {
        //TODO setText

        //viewHolder.navn.setText(singleton.getMedarbejder().get(i).getNavn());

        viewHolder.indbakke_aftaler_listitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO her skal fragmentet med hele aftalen vises.
                setFragment(forhandlingFragment);
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
        return 0;
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
        RelativeLayout indbakke_aftaler_listitems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.indbakke_type);
            navn = (TextView) itemView.findViewById(R.id.indbakke_navn);
            periode = (TextView) itemView.findViewById(R.id.periode);
            virksomhed = (TextView) itemView.findViewById(R.id.indbakke_virk);
            pris = (TextView) itemView.findViewById(R.id.indbakke_pris);
            indbakke_aftaler_listitems = (RelativeLayout) itemView.findViewById(R.id.indbakke_recyclerview_listitem);
        }
    }
}
