package com.example.chris.flexicuv2.startskærm.indbakke.aftaler;
/**
 * @Author Janus
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.startskærm.udlej.Ledige_rediger;
import com.example.chris.flexicuv2.startskærm.udlej.Udlejning_Janus;


public class Ledige_recyclerview_adapter extends RecyclerView.Adapter<Ledige_recyclerview_adapter.ViewHolder>{

    private Context mContext;
    private Singleton singleton;
    private Ledige_rediger ledige_rediger_fragment;
    private Spinner medarbejder_spinner;

    //TODO konstruktøren skal self have flere input når net er klar.
    public Ledige_recyclerview_adapter(Context mContext) {
        this.mContext = mContext;
        singleton = Singleton.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ledige_recyclerview_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        ledige_rediger_fragment = new Ledige_rediger();
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final Ledige_recyclerview_adapter.ViewHolder viewHolder, final int i) {
        //TODO setText

        //viewHolder.navn.setText(singleton.getMedarbejder().get(i).getNavn());
        viewHolder.navn.setText(singleton.getMineMedarbejderUdbud().get(i).getMedarbejder().getNavn());
        viewHolder.periode.setText(singleton.getMineMedarbejderUdbud().get(i).getStartDato().replace(" ", "") + " - " + singleton.getMineMedarbejderUdbud().get(i).getSlutDato().replace(" ", ""));
        viewHolder.pris.setText(singleton.getMineMedarbejderUdbud().get(i).getTimePris() + "kr.");
        viewHolder.værktøj.setText(egetVærktøj(i));
        viewHolder.arbejdsområder.setText(singleton.getMineMedarbejderUdbud().get(i).getMedarbejder().getArbejdsomraade());

        viewHolder.ledige_aftaler_listitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleton.midlertidigAftale = singleton.getMineMedarbejderUdbud().get(i);
                singleton.midlertidigMedarbejder = singleton.getMineMedarbejderUdbud().get(i).getMedarbejder();
                ledige_rediger_fragment = new Ledige_rediger();
                medarbejder_spinner = v.findViewById(R.id.ledig_medarbejder_spinner);
                setFragment(ledige_rediger_fragment);
            }
        });
    }


    public String egetVærktøj(int i){
        String egetVærktøj;
        if(singleton.getMineMedarbejderUdbud().get(i).isEgetVærktøj()==false){
            egetVærktøj="Nej";
        }
        else
            egetVærktøj="Ja";

        return egetVærktøj;
    }

    @Override
    public int getItemCount() {
        return singleton.getMineMedarbejderUdbud().size();
    }

    public void setFragment(Fragment fragment) {
        //startskærmFrameTilDiverse.removeAllViews();
        FragmentTransaction fragmentTransaction = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.startskærm_frame_til_diverse, fragment);
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    /**
     * Indre klasse
     * Opretter ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView navn;
        TextView periode;
        TextView pris;
        TextView værktøj;
        TextView arbejdsområder;
        RelativeLayout ledige_aftaler_listitems;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            navn = (TextView) itemView.findViewById(R.id.ledige_navn);
            periode = (TextView) itemView.findViewById(R.id.ledige_periode);
            pris = (TextView) itemView.findViewById(R.id.ledige_pris);
            værktøj = itemView.findViewById(R.id.ledige_værktøj);
            arbejdsområder = itemView.findViewById(R.id.ledige_arbejdsområder);
            ledige_aftaler_listitems = (RelativeLayout) itemView.findViewById(R.id.ledige_recyclerview_listitem);
        }
    }
}
