package com.example.chris.flexicuv2.startskærm.hjem;
/**
 * @Author Gunn
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class RecyclerViewAdapter_Udlej extends RecyclerView.Adapter<RecyclerViewAdapter_Udlej.ViewHolder>  {

    private ArrayList<Medarbejder> mMedarbejder;
    private Context mContext;
    private Singleton singleton;
    private int index = 0;

    public RecyclerViewAdapter_Udlej(Context mContext, ArrayList<Medarbejder> mMedarbejder) {
       // this.mMedarbejder = mMedarbejder;
        //Collections.sort(mMedarbejder);
        this.mContext = mContext;
        singleton = Singleton.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.hjem_udlej_listitem,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        DecimalFormat numberFormat = new DecimalFormat("#.00");
        if(index<singleton.getMineUdlejIndgåedeAftaler().size()) {


            if (i < singleton.getMineUdlejIndgåedeAftaler().get(index).getForhandlinger().size()) {
                if (singleton.getMineUdlejIndgåedeAftaler().get(index).getForhandlinger().get(i).isAftaleIndgået()) {
                    int arbejdsdage = udregnArbejdsdage(singleton.getMineUdlejIndgåedeAftaler().get(index).getForhandlinger().get(i).getLejerStartDato().toString(), singleton.getMineUdlejIndgåedeAftaler().get(index).getForhandlinger().get(i).getLejerSlutDato().toString());
                    int timeloen = Integer.parseInt(singleton.getMineUdlejIndgåedeAftaler().get(index).getForhandlinger().get(i).getLejPris());
                    System.out.println(arbejdsdage + ", " + timeloen);
                    double loen = udregnPriser(timeloen, arbejdsdage, 7.4);
                    viewHolder.arbejdeområde.setText(singleton.getMineUdlejIndgåedeAftaler().get(index).getForhandlinger().get(i).getMedarbejder().getArbejdsomraade());
                    viewHolder.navn.setText(singleton.getMineUdlejIndgåedeAftaler().get(index).getForhandlinger().get(i).getMedarbejder().getNavn());
                    //    viewHolder.virksomhed.setText(singleton.getMineLejIndgåedeAftaler().get(index).getUdlejer().getVirksomhedsnavn());
                    viewHolder.periode.setText(singleton.getMineUdlejIndgåedeAftaler().get(index).getForhandlinger().get(i).getUdlejerStartDato().replace(" ", "") + " - " + singleton.getMineUdlejIndgåedeAftaler().get(index).getForhandlinger().get(i).getUdlejerSlutDato().replace(" ", ""));
                    viewHolder.loen.setText(numberFormat.format(loen)+" DKK");

                } else {
                    index++;
                }
            }
        }
        viewHolder.hjem_udlej_listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "onClick: clicked on" + singleton.getMedarbejdere().get(i).getNavn());

                onButtonShowPopupWindowClick(v, i);
                //Toast.makeText(mContext, mMedarbejderNavn.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public double udregnPriser(int timeløn, int antalArbejdsdage, double gennemsnitstimer) {
        double subtotal = timeløn*gennemsnitstimer*antalArbejdsdage;
        double flexicuGebyr = (subtotal*2.5)/100;
        double total = subtotal+flexicuGebyr;
        return total;
    }

    /**
     * Metoden anvendes til at finde totale antal arbejdsdage i perioden
     * @param startdato
     * @param slutdato
     */

    public int udregnArbejdsdage(String startdato, String slutdato){
        startdato = startdato.replace(" ", "");
        slutdato = slutdato.replace(" ","");

        int arbDage = Arbejdsdage_Kalender.findArbejdsdage(startdato, slutdato);
        if(arbDage<0) {
            arbDage = 0;
        }
        return arbDage;

    }


    @Override
    public int getItemCount() {
        return singleton.getMineUdlejIndgåedeAftaler().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView navn;
        private TextView periode;
        private TextView loen;
        private TextView arbejdeområde;
        RelativeLayout hjem_udlej_listItem;

        public ViewHolder(View itemView){
            super(itemView);
            navn = itemView.findViewById(R.id.medarbejderNavn);
            periode = itemView.findViewById(R.id.periode);
            loen = itemView.findViewById(R.id.salary);
            arbejdeområde = itemView.findViewById(R.id.workfield);
            hjem_udlej_listItem = itemView.findViewById(R.id.hjem_udlej_listItem);
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
