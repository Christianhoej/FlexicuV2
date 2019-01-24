package com.example.chris.flexicuv2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.hjælpeklasser.Arbejdsdage_Kalender;
import com.example.chris.flexicuv2.model.Aftale;
import com.example.chris.flexicuv2.model.Forhandling;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.startskærm.lej.Lej_medarbejder_fragment;

import java.text.DecimalFormat;

public class Historik_recyclerview_adapter extends RecyclerView.Adapter<Historik_recyclerview_adapter.ViewHolder> {

    private Context mContext;
    private Singleton singleton;
    private int index=0;

    public Historik_recyclerview_adapter(Context mContext) {
        this.mContext = mContext;
        singleton = Singleton.getInstance();
    }

    @NonNull
    @Override
    public Historik_recyclerview_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.historik_listitem, viewGroup, false);
        Historik_recyclerview_adapter.ViewHolder holder = new Historik_recyclerview_adapter.ViewHolder(view);
        return new ViewHolder(view);
    }


    private int index=0;
    DecimalFormat numberFormat = new DecimalFormat("#.00");

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {


        if(index<singleton.getMineAfsluttedeAftaler().size()) {


            if (i < singleton.getMineAfsluttedeAftaler().get(index).getForhandlinger().size()) {
                if (singleton.getMineAfsluttedeAftaler().get(index).getForhandlinger().get(i).isAftaleIndgået()) {
                    System.out.println("HEEY");
                    int arbejdsdage = udregnArbejdsdage(singleton.getMineAfsluttedeAftaler().get(index).getForhandlinger().get(i).getLejerStartDato().toString(), singleton.getMineAfsluttedeAftaler().get(index).getForhandlinger().get(i).getLejerSlutDato().toString());
                    int timeloen = Integer.parseInt(singleton.getMineAfsluttedeAftaler().get(index).getForhandlinger().get(i).getLejPris());
                    System.out.println(arbejdsdage + ", " + timeloen);
                    double loen = udregnPriser(timeloen, arbejdsdage, 7.4);
                    viewHolder.workfield.setText(singleton.getMineAfsluttedeAftaler().get(index).getForhandlinger().get(i).getMedarbejder().getArbejdsomraade());
                    viewHolder.name.setText(singleton.getMineAfsluttedeAftaler().get(index).getForhandlinger().get(i).getMedarbejder().getNavn());
                    viewHolder.virksomhed.setText(singleton.getMineAfsluttedeAftaler().get(index).getUdlejer().getVirksomhedsnavn());
                    viewHolder.periode.setText(singleton.getMineAfsluttedeAftaler().get(index).getForhandlinger().get(i).getUdlejerStartDato().replace(" ", "") + " - " + singleton.getMineAfsluttedeAftaler().get(index).getForhandlinger().get(i).getUdlejerSlutDato().replace(" ", ""));
                    viewHolder.totalpris.setText(numberFormat.format(Double.toString(loen)));

                    if (singleton.getMineAfsluttedeAftaler().get(index).getUdlejer().getBrugerID().equals(singleton.getBruger().getBrugerID())) {
                        viewHolder.type.setText("Jeg er udlejer");
                    } else {
                        viewHolder.type.setText("Jeg er lejer");
                    }
                } else {
                    index++;
                }
            }
        }


       /*
        int arbejdsdage = udregnArbejdsdage(singleton.getMineAfsluttedeAftaler().get(i).getStartDato().toString(), singleton.getMineAfsluttedeAftaler().get(i).getSlutDato().toString());
        int timeloen = Integer.parseInt(singleton.getMineAfsluttedeAftaler().get(i).getTimePris());
        double loen = udregnPriser(timeloen,arbejdsdage, 7.4) ;
        viewHolder.name.setText(singleton.getMineAfsluttedeAftaler().get(i).getMedarbejder().getNavn());
        viewHolder.totalpris.setText(Double.toString(loen));
        viewHolder.workfield.setText(singleton.getMineAfsluttedeAftaler().get(i).getMedarbejder().getArbejdsomraade());
        viewHolder.periode.setText(singleton.getMineAfsluttedeAftaler().get(i).getStartDato().replace(" ", "" + " - " + singleton.getMineAfsluttedeAftaler().get(i).getSlutDato().replace(" ", "")));
        viewHolder.virksomhed.setText(singleton.getMineAfsluttedeAftaler().get(i).getUdlejer().getVirksomhedsnavn());


        if (singleton.getMineAfsluttedeAftaler().get(i).getUdlejer().getBrugerID().equals(singleton.getBruger().getBrugerID())) {
            viewHolder.type.setText("Jeg er udlejer");
        } else {
            viewHolder.type.setText("Jeg er lejer");
        }
*/

        viewHolder.historik_listitem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO åben popup;
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
        if(arbDage<0)
            arbDage = 0;
        return arbDage;

    }

    @Override
    public int getItemCount() {
        int antal=0;
        for(Aftale aftale : singleton.getMineAfsluttedeAftaler()){
            for(Forhandling forhandling : aftale.getForhandlinger()){
                antal++;
            }
        }
        return singleton.getMineAfsluttedeAftaler().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView totalpris;
        private TextView workfield;
        private TextView periode;
        private TextView virksomhed;
        private TextView type;
        private RelativeLayout historik_listitem;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.historik_navn);
            totalpris = itemView.findViewById(R.id.historik_totalpris);
            periode = itemView.findViewById(R.id.historik_periode);
            workfield = itemView.findViewById(R.id.historik_arbejdsområder);
            virksomhed = itemView.findViewById(R.id.historik_virk);
            type = itemView.findViewById(R.id.historik_type);
            historik_listitem = itemView.findViewById(R.id.historik_listitem);
        }
    }
}

