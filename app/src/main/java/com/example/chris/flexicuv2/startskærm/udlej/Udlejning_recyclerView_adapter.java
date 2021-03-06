package com.example.chris.flexicuv2.startskærm.udlej;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Singleton;

/**
 * @Author Gunn
 */

public class Udlejning_recyclerView_adapter extends RecyclerView.Adapter<Udlejning_recyclerView_adapter.ViewHolder>{

    private Context mContext;
    private Singleton singleton;
    private static final String TAG = "recyclerView_adapter";
    private Udlejning_indhold udlejning_af_medarbejder;
    private FrameLayout udlejning_fragment_frame;
    private FrameLayout startskærmFrameTilDiverse;
    private Spinner medarbejder_spinner;

    public Udlejning_recyclerView_adapter(Context mContext) {
        this.mContext = mContext;
        singleton = Singleton.getInstance();
    }

    /**
     * ViewHolder metode. Ansvarlig for at inflate View'et
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public Udlejning_recyclerView_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.medarbejdere_recyclerview_listitems, viewGroup, false);
        //Objekt ViewHolder klassen nedenfor, der tager vores view som er oprettet ud fra designet af recyclerViewet
        Udlejning_recyclerView_adapter.ViewHolder holder = new Udlejning_recyclerView_adapter.ViewHolder(view);
        udlejning_fragment_frame = viewGroup.findViewById(R.id.udlej_fragment_frame);
        startskærmFrameTilDiverse = viewGroup.findViewById(R.id.startskærm_frame_til_diverse);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Udlejning_recyclerView_adapter.ViewHolder viewHolder, final int i) {

        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.medarbejder_navn.setText(singleton.getMedarbejdere().get(i).getNavn());
        viewHolder.medarbejder_arbejdsområde.setText(singleton.getMedarbejdere().get(i).getArbejdsomraade());

        viewHolder.medarbejder_recyclerview_listitems.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + singleton.getMedarbejdere().get(i).getNavn());

                valgAfMedarbejder(v, i);
            }
        });

    }


    public void valgAfMedarbejder(View view, int i) {
        singleton.midlertidigMedarbejder = singleton.getMedarbejdere().get(i);
        udlejning_af_medarbejder = new Udlejning_indhold();
        medarbejder_spinner = view.findViewById(R.id.udlejning_medarbejder_spinner);
        //TODO navnet skal føres med videre.

        setFragment(udlejning_af_medarbejder);

    }


    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskærm_frame_til_diverse, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    @Override
    public int getItemCount() {
        return singleton.getMedarbejdere().size();
    }

    /**
     * Metode der skal anvendes til at "fylde" viewet når der oprettes nye medarbejdere
     * @param navn
     * @param område
     */
    public void medarbejderTilføjet(String navn, String område) {

        if(getItemCount()<singleton.getMedarbejdere().size()){
            notifyDataSetChanged();
        }
    }


    /**
     * Indre klasse
     * Opretter ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView medarbejder_navn;
        TextView medarbejder_arbejdsområde;
        RelativeLayout medarbejder_recyclerview_listitems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            medarbejder_navn = itemView.findViewById(R.id.medarbejder_navn_recycler);
            medarbejder_arbejdsområde = itemView.findViewById(R.id.arbejdsområde_recycler);
            medarbejder_recyclerview_listitems = itemView.findViewById(R.id.medarbejder_recyclerview_listitems);
        }
    }


}
