package com.example.chris.flexicuv2.Medarbejdere_pakke;

/*
 *Udarbejdet med inspiration fra denne video:
 * https://www.youtube.com/watch?v=Vyqz_-sJGFk
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.fragments.RecyclerViewAdapter_AlleMedarbejdere;

import java.util.ArrayList;

public class medarbejder_recyclerView_adapter extends RecyclerView.Adapter<medarbejder_recyclerView_adapter.ViewHolder>{

    private static final String TAG = "recyclerView_adapter";

    private ArrayList<String> mMedarbjderBilleder = new ArrayList<>();
    private ArrayList<String> mMedarbejderNavn = new ArrayList<>();
    private ArrayList<String> mMedarbejderArbejdsområde = new ArrayList<>();
    private Context mContext;


    //Billeder skal implementeres på en måde
    public medarbejder_recyclerView_adapter(Context mContext, /*ArrayList<String> mMedarbjderBilleder,*/ ArrayList<String> mMedarbejderNavn, ArrayList<String> mMedarbejderArbejdsområde) {
        //this.mMedarbjderBilleder = mMedarbjderBilleder;
        this.mMedarbejderNavn = mMedarbejderNavn;
        this.mMedarbejderArbejdsområde = mMedarbejderArbejdsområde;
        this.mContext = mContext;
    }

    /**
     * ViewHolder metode. Ansvarlig for at inflate View'et
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.medarbejdere_recyclerview_listitems, viewGroup, false);
        //Objekt ViewHolder klassen nedenfor, der tager vores view som er oprettet ud fra designet af recyclerViewet
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        /*Kan implementere Glide for en bedre oplevelse

        Glide.with(mContext)
                .asBitmap()
                .load(mMedarbjderBilleder.get(i)) */

        viewHolder.medarbejder_navn.setText(mMedarbejderNavn.get(i));
        viewHolder.medarbejder_arbejdsområde.setText(mMedarbejderArbejdsområde.get(i));

        viewHolder.medarbejder_recyclerview_listitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on" + mMedarbejderNavn.get(i));

                Toast.makeText(mContext, mMedarbejderNavn.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMedarbejderNavn.size();
    }


    /**
     * Indre klasse
     * Opretter ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        //ImageView medarbejder_billede;
        TextView medarbejder_navn;
        TextView medarbejder_arbejdsområde;
        RelativeLayout medarbejder_recyclerview_listitems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //medarbejder_billede = itemView.findViewById(R.id.medarbejder_billede_recycler);
            medarbejder_navn = itemView.findViewById(R.id.medarbejder_navn_recycler);
            medarbejder_arbejdsområde = itemView.findViewById(R.id.arbejdsområde_recycler);
            medarbejder_recyclerview_listitems = itemView.findViewById(R.id.medarbejder_recyclerview_listitems);



        }
    }

}
