package com.example.chris.flexicuv2.Medarbejdere_pakke;

/*
 *Udarbejdet med inspiration fra denne video:
 * https://www.youtube.com/watch?v=Vyqz_-sJGFk
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.fragments.RecyclerViewAdapter_AlleMedarbejdere;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.ArrayList;
import java.util.List;

public class medarbejder_recyclerView_adapter extends RecyclerView.Adapter<medarbejder_recyclerView_adapter.ViewHolder> {

    private static final String TAG = "recyclerView_adapter";

    private ArrayList<String> mMedarbjderBilleder = new ArrayList<>();
    private ArrayList<String> mMedarbejderNavn = new ArrayList<>();
    private ArrayList<String> mMedarbejderArbejdsområde = new ArrayList<>();
    private Context mContext;
    private Singleton singleton;


    //Billeder skal implementeres på en måde
    public medarbejder_recyclerView_adapter(Context mContext/*ArrayList<String> mMedarbjderBilleder,*//* ArrayList<String> mMedarbejderNavn, ArrayList<String> mMedarbejderArbejdsområde*/) {
        this.mMedarbjderBilleder = mMedarbjderBilleder;
        this.mMedarbejderNavn = mMedarbejderNavn;
        this.mMedarbejderArbejdsområde = mMedarbejderArbejdsområde;
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
       //viewHolder.medarbejder_navn.setText(singleton.getMedarbejdere().get(i).getNavn());
       //viewHolder.medarbejder_arbejdsområde.setText(singleton.getMedarbejdere().get(i).getArbejdsomraade());

        viewHolder.medarbejder_recyclerview_listitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on" + singleton.getMedarbejdere().get(i).getNavn());

                /*Medarbejdere_skaerm medarbejdere_skaerm = new Medarbejdere_skaerm();
                medarbejdere_skaerm.onButtonShowPopupWindowClick(v);*/
                onButtonShowPopupWindowClick(v);
                //Toast.makeText(mContext, mMedarbejderNavn.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
     * @param view
     */
    public void onButtonShowPopupWindowClick(View view) {
        //tilPopUp = (View) findViewById(R.id.tilPopUp);
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.vis_medarbejder_popup_fragment, null);

        //get width
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;

        // create the popup window
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, (width-20), height, focusable);


        //popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        //final PopupWindow popupWindowt = new PopupWindow();
        popupWindow.setContentView(popupView);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        /*// dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });*/
    }



    @Override
    public int getItemCount() {
        return mMedarbejderNavn.size();
        //return singleton.getMedarbejdere().size();
    }

    /**
     * Metode der skal anvendes til at "fylde" viewet når der oprettes nye medarbejdere
     * @param navn
     * @param område
     */
    public void medarbejderTilføjet(String navn, String område) {

       /* if(getItemCount()<singleton.getMedarbejdere().size()){
            notifyDataSetChanged();
        }*/

        mMedarbejderNavn.add(navn);
        mMedarbejderArbejdsområde.add(område);
        //if (mMedarbejderNavn != null && mMedarbejderNavn.size() > 0) {
        ArrayList<String> temp1 = new ArrayList<>();
        for(String s : mMedarbejderNavn){
            temp1.add(s);
        }
        ArrayList<String> temp2 = new ArrayList<>();
        for(String s : mMedarbejderArbejdsområde){
            temp2.add(s);
        }
        mMedarbejderArbejdsområde.clear();
        mMedarbejderNavn.clear();
        mMedarbejderArbejdsområde = temp2;
        mMedarbejderNavn = temp1;

            notifyDataSetChanged();
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
