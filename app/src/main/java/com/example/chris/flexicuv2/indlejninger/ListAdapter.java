package com.example.chris.flexicuv2.indlejninger;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;

public class ListAdapter extends RecyclerView.Adapter{

    /*Denne kode er udarbejdet med inspiration fra følgende video:
     * https://www.youtube.com/watch?v=9I2jUY0mwYQ
     */

    /*
    Skal denne klasse slettes???

     */

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listadapter_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Arbejdsområder.arbejdsområder.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView ting;
        private CheckedTextView mBox;


        public ListViewHolder(View itemView){
            super(itemView);
            ting = (TextView) itemView.findViewById(R.id.itemArbejdsområder);
            mBox = (CheckedTextView) itemView.findViewById(R.id.itemBox);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            ting.setText(Arbejdsområder.arbejdsområder[position]);
        }

        public void onClick(View view){
        }

    }
}
