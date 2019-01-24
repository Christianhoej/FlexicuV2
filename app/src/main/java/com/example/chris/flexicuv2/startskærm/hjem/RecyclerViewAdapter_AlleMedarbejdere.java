package com.example.chris.flexicuv2.startskærm.hjem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
import com.example.chris.flexicuv2.medarbejdere.Rediger_medarbejder_fragment_1;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class RecyclerViewAdapter_AlleMedarbejdere extends RecyclerView.Adapter<RecyclerViewAdapter_AlleMedarbejdere.ViewHolder>{

    private ArrayList<Medarbejder> mMedarbejder;
    private Context mContext;
    Singleton singleton;
    private Rediger_medarbejder_fragment_1 rediger_medarbejder_fragment_1;

    public RecyclerViewAdapter_AlleMedarbejdere(Context mContext, ArrayList<Medarbejder> mMedarbejder) {
        this.mMedarbejder = mMedarbejder;
        Collections.sort(mMedarbejder);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_AlleMedarbejdere.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.startskaerm_alle_medarbejdere_listitem,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_AlleMedarbejdere.ViewHolder viewHolder, final int i) {
       // String loen = "Løn: " + Integer.toString(mMedarbejder.get(i).getLoen());
        viewHolder.name.setText(mMedarbejder.get(i).getNavn());
        //viewHolder.salary.setText(loen);
        viewHolder.workfield.setText(mMedarbejder.get(i).getArbejdsomraade());

        viewHolder.alle_medarbejdere_listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d(TAG, "onClick: clicked on" + singleton.getMedarbejdere().get(i).getNavn());

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
        //private TextView salary;
        private TextView workfield;
        RelativeLayout alle_medarbejdere_listItem;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.medarbejderNavn);
            //salary = itemView.findViewById(R.id.salary);
            workfield = itemView.findViewById(R.id.workfield);
            alle_medarbejdere_listItem = itemView.findViewById(R.id.alle_medarbejdere_start_listItem);
        }
    }

    public void onButtonShowPopupWindowClick(View view, final int i) {
        //tilPopUp = (View) findViewById(R.id.tilPopUp);
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.hjem_popup_alle, null);

        TextView navn = popupView.findViewById(R.id.medarbejder_navn);
        navn.setText("Navn: " + singleton.getMedarbejdere().get(i).getNavn());

        TextView arbejdsområde = popupView.findViewById(R.id.medarbejder_arbejdsområde);
        arbejdsområde.setText("Arbejdsområde: " + singleton.getMedarbejdere().get(i).getArbejdsomraade());

        TextView køn = popupView.findViewById(R.id.medarbejder_køn);
        køn.setText("Køn: " + singleton.getMedarbejdere().get(i).getKøn());

        TextView email = popupView.findViewById(R.id.medarbejder_email);
        email.setText("Email: " + singleton.getMedarbejdere().get(i).getEmail());

        TextView alder = popupView.findViewById(R.id.medarbejder_alder);
        alder.setText("Alder: " + (Calendar.getInstance().get(Calendar.YEAR)-singleton.getMedarbejdere().get(i).getFødselsår()) + " år");

        TextView adresse = popupView.findViewById(R.id.medarbejder_adresse);
        adresse.setText("Adresse: " + singleton.getMedarbejdere().get(i).getVejnavn() + " " + singleton.getMedarbejdere().get(i).getHusnummer() + ", " + singleton.getMedarbejdere().get(i).getPostnr());


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
 /*       final Button rediger = popupView.findViewById(R.id.rediger);
        rediger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rediger_medarbejder_fragment_1 = new Rediger_medarbejder_fragment_1();
                openSkaerm(Medarbejdere_skaerm_akt.class);

                singleton.midlertidigMedarbejder = singleton.getMedarbejdere().get(i);
                popupWindow.dismiss();
            }
        });
*/
    }
    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.medarbejdere_frame, fragment);
        fragmentTransaction.commit();
    }

    public void openSkaerm(Class a){
        Intent intent = new Intent(((AppCompatActivity)mContext),a);
        mContext.startActivity(intent);
        //setFragment(rediger_medarbejder_fragment_1);
    }


}
