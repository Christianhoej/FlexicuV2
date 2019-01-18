package com.example.chris.flexicuv2.loadingscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class Loading_screen extends Fragment {

    TextView tv;
    ImageView ivX;
    private Animation rotateAnim;

    public Loading_screen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.loading_screen_fragment, container, false);

        // Inflate the layout for this fragment

        tv = v.findViewById(R.id.loading_text);
        ivX = v.findViewById(R.id.x_logo);
        /*rotateAnim =AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);//TODO link fragmentet til en "kontekst"
        ivX.startAnimation(rotateAnim);
        Log.d("anim","Kommer her");
        */

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);
        ivX.startAnimation(animation);

        return v;
    }

}
