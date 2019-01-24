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
import com.example.chris.flexicuv2.login.LoginPresenter;

/**
 * A simple {@link Fragment} subclass.
 * @Author Christian
 */
public class Loading_screen extends Fragment implements LoginPresenter.startAnimation {

    TextView tv2;
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

        tv2 = v.findViewById(R.id.loading_text);
        ivX = v.findViewById(R.id.x_logo);
        /*rotateAnim =AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);//TODO link fragmentet til en "kontekst"
        ivX.startAnimation(rotateAnim);
        Log.d("anim","Kommer her");
        */
        rotateAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.rotation);
        ivX.startAnimation(rotateAnim);
        return v;
    }

    @Override
    public void starAnimationMetode(Boolean begynd) {

        ivX.startAnimation(rotateAnim);
    }
}
