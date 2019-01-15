package com.example.chris.flexicuv2.loadingscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chris.flexicuv2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Loading_Screen extends Fragment {

    TextView tv;
    ImageView ivX;
    private Animation rotateAnim;

    public Loading_Screen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loading__screen, container, false);
        tv = v.findViewById(R.id.loading_text);
        ivX = v.findViewById(R.id.x_logo);
        rotateAnim =AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);//TODO link fragmentet til en "kontekst"
        ivX.startAnimation(rotateAnim);
        Log.d("anim","Kommer her");
        return v;
    }

}
