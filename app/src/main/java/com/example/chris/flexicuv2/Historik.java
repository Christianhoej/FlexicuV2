package com.example.chris.flexicuv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chris.flexicuv2.startskærm.lej.RecyclerViewAdapter_Ledig_Arbejdskraft;

public class Historik extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historik_akt);

        recyclerView = findViewById(R.id.historik_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        Historik_recyclerview_adapter mAdapter = new Historik_recyclerview_adapter(this);

        recyclerView.setAdapter(mAdapter);
    }
}
