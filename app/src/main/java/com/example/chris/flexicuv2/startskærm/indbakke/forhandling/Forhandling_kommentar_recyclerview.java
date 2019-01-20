package com.example.chris.flexicuv2.startskærm.indbakke.forhandling;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Aftale;
import com.example.chris.flexicuv2.model.Singleton;

import java.util.List;

public class Forhandling_kommentar_recyclerview extends RecyclerView.Adapter {

    private Context mContext;
    private List<Aftale> aftaleList;
    private Singleton singleton;
    private final int MODTAGER = 1;
    private final int SENDER = 2;

    public Forhandling_kommentar_recyclerview(Context mContext, List<Aftale> aftaleList){
        this.mContext = mContext;
        this.aftaleList = aftaleList;
        singleton = Singleton.getInstance();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        if(i == SENDER){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.forhandling_meddelelser_popup_send_listitem, viewGroup, false);
            return new SendtKommentarHolder(view);
        }
        else if(i == MODTAGER){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.forhandling_meddelelser_popup_modtaget_listitem, viewGroup, false);
            return new ModtagetKommentarHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        switch (viewHolder.getItemViewType()) {
            case SENDER:
                ((SendtKommentarHolder)viewHolder).bind(aftaleList.get(i));
                break;
            case MODTAGER:
                ((ModtagetKommentarHolder)viewHolder).bind(aftaleList.get(i));
                break;
        }
    }

    @Override
    public int getItemCount() {
        System.out.println("Aftalelist size: " + aftaleList.size());
        if(aftaleList!=null){
            return aftaleList.size();
        }
        else{
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position){
        if(singleton.getBruger().getBrugerID().equals(aftaleList.get(position).getSidstSendtAftale().getBrugerID())){
            return SENDER;
        }
        else{
            return MODTAGER;
        }
    }

    private class ModtagetKommentarHolder extends RecyclerView.ViewHolder{
        TextView modtagetKommentarTV, navnTV;

        public ModtagetKommentarHolder(@NonNull View itemView) {
            super(itemView);
            modtagetKommentarTV = itemView.findViewById(R.id.kommentar_modtaget);
            navnTV = itemView.findViewById(R.id.kommentarSender);
        }

        void bind(Aftale aftale){
            modtagetKommentarTV.setText(aftale.getKommentar());
            //navnTV.setText(aftale.get().getNavn());
        }


    }

    private class SendtKommentarHolder extends RecyclerView.ViewHolder{
        TextView sendtKommentarTV;

        public SendtKommentarHolder(@NonNull View itemView) {
            super(itemView);
            sendtKommentarTV = itemView.findViewById(R.id.kommentar_sendt);
        }

        void bind(Aftale aftale){
            System.out.println("aftale i binder: " + aftale.getKommentar());
            sendtKommentarTV.setText(aftale.getKommentar());
        }
    }
}
