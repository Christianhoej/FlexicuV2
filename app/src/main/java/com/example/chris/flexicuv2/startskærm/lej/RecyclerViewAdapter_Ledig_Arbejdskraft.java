package com.example.chris.flexicuv2.startskærm.lej;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.model.Singleton;

public class RecyclerViewAdapter_Ledig_Arbejdskraft extends RecyclerView.Adapter<RecyclerViewAdapter_Ledig_Arbejdskraft.ViewHolder>{

    private Context mContext;
    private Singleton singleton;
    private Lej_medarbejder_fragment lej_medarbejder_fragment;
    private FrameLayout startskærmFrameTilDiverse;

    public RecyclerViewAdapter_Ledig_Arbejdskraft(Context mContext) {
        this.mContext = mContext;
        singleton = Singleton.getInstance();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_Ledig_Arbejdskraft.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.lej_ledig_arbejdskraft_listitem,viewGroup,false);
        RecyclerViewAdapter_Ledig_Arbejdskraft.ViewHolder holder = new RecyclerViewAdapter_Ledig_Arbejdskraft.ViewHolder(view);
        startskærmFrameTilDiverse = viewGroup.findViewById(R.id.startskærm_frame_til_diverse);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String loen = "Løn: " + Integer.toString(singleton.getAndresMedarbejderUdbud().get(i).getMedarbejder().getLoen());
        viewHolder.name.setText(singleton.getAndresMedarbejderUdbud().get(i).getMedarbejder().getNavn());
        viewHolder.salary.setText(loen);
        viewHolder.workfield.setText(singleton.getAndresMedarbejderUdbud().get(i).getMedarbejder().getArbejdsomraade());
        viewHolder.periode.setText(singleton.getAndresMedarbejderUdbud().get(i).getStartDato() + " til " + singleton.getAndresMedarbejderUdbud().get(i).getSlutDato());

        viewHolder.lej_recyclerview_listitems.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                valgAfLedigArbejdskraft(v, i);
            }
        });


    }

    public void valgAfLedigArbejdskraft(View view, int i) {
        singleton.midlertidigAftale = singleton.getAndresMedarbejderUdbud().get(i);
        lej_medarbejder_fragment = new Lej_medarbejder_fragment();
        setFragment(lej_medarbejder_fragment);

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
        if(singleton.getAndresMedarbejderUdbud()!=null){
            return singleton.getAndresMedarbejderUdbud().size();
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView salary;
        private TextView workfield;
        private TextView periode;
        private RelativeLayout lej_recyclerview_listitems;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.medarbejderNavn);
            salary = itemView.findViewById(R.id.salary);
            periode = itemView.findViewById(R.id.periode);
            workfield = itemView.findViewById(R.id.workfield);
            lej_recyclerview_listitems = itemView.findViewById(R.id.lej_ledig_arbejdskraft_listitem);
        }
    }
}
