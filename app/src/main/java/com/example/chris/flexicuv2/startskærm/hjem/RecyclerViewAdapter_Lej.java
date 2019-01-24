package com.example.chris.flexicuv2.startskærm.hjem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.medarbejdere.Rediger_medarbejder_fragment_1;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class RecyclerViewAdapter_Lej extends RecyclerView.Adapter<RecyclerViewAdapter_Lej.ViewHolder>  {

    private ArrayList<Medarbejder> mMedarbejder;
    private Context mContext;
    Singleton singleton;


    public RecyclerViewAdapter_Lej(Context mContext, ArrayList<Medarbejder> mMedarbejder) {
        this.mMedarbejder = mMedarbejder;
        Collections.sort(mMedarbejder);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.hjem_lej_listitem,viewGroup,false);
        singleton = Singleton.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {


       // String loen = "Løn: " + Integer.toString(mMedarbejder.get(i).getLoen());
        viewHolder.name.setText(mMedarbejder.get(i).getNavn());
      //  viewHolder.salary.setText(loen);
        viewHolder.workfield.setText(mMedarbejder.get(i).getArbejdsomraade());

        viewHolder.hjem_lej_listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "onClick: clicked on" + singleton.getMedarbejdere().get(i).getNavn());

                onButtonShowPopupWindowClick(v, i);
                //Toast.makeText(mContext, mMedarbejderNavn.get(i), Toast.LENGTH_SHORT).show();
            }
        });

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
        RelativeLayout hjem_lej_listItem;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.medarbejderNavn);
            period = itemView.findViewById(R.id.periode);
            salary = itemView.findViewById(R.id.salary);
            workfield = itemView.findViewById(R.id.workfield);
            hjem_lej_listItem = itemView.findViewById(R.id.hjem_lej_listItem);
        }
    }

    /**
     * https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
     * @param view
     */
    public void onButtonShowPopupWindowClick(View view, final int i) {

        //TODO Når lejede aftaler er implementeret skal det her opdateres;
        //tilPopUp = (View) findViewById(R.id.tilPopUp);
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.hjem_popup_aftaler, null);

        TextView navn = popupView.findViewById(R.id.aftaler_navn);
        navn.setText("Navn: " + singleton.getMedarbejdere().get(i).getNavn());

        TextView arbejdsområde = popupView.findViewById(R.id.aftaler_arbejdsområder);
        arbejdsområde.setText("Arbejdsområder: " + singleton.getMedarbejdere().get(i).getArbejdsomraade());

        TextView periode = popupView.findViewById(R.id.aftaler_periode);
        periode.setText("Periode: TILFØJ" );

        TextView dage = popupView.findViewById(R.id.aftaler_arbejdsdage);
        dage.setText("Dage: TILFØJ" );

        TextView virksomhed = popupView.findViewById(R.id.aftaler_virksomhed);
        virksomhed.setText("Virksomhed: TILFØJ");

        TextView værktøj = popupView.findViewById(R.id.aftaler_værktøj);
        værktøj.setText("Virksomhed: TILFØJ");

        TextView timepris = popupView.findViewById(R.id.aftaler_timepris);
        timepris.setText("Timepris: TILFØJ");

        TextView total = popupView.findViewById(R.id.aftaler_totalpris);
        total.setText("Totalpris: TILFØJ");


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
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setElevation(20);
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        //  popupWindow.setOutsideTouchable(true);
            /*// dismiss the popup window when touched
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popupWindow.dismiss();
                    return true;
                }
            });*/

    }

}