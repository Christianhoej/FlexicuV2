
package com.example.chris.flexicuv2.login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import com.example.chris.flexicuv2.DBManager;
import com.example.chris.flexicuv2.loadingscreen.Loading_Screen;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.opret_bruger.NewUser_akt;
import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.StartSkærm.Startskaerm;
import com.example.chris.flexicuv2.opret_bruger.New_user_fragment_1;
import com.example.chris.flexicuv2.opret_bruger.TilFragmenter;

public class LoginScreen_akt extends AppCompatActivity implements View.OnClickListener, LoginPresenter.UpdateLoginScreen {

    private Button logIn;
    private Button newUser;
    private EditText username;
    private EditText password;

    private New_user_fragment_1 new_user_fragment_1;
    private Loading_Screen loading_screen;
    private FrameLayout loginFrame;
    private FrameLayout tilFragmenter;

    private DBManager dbManager;



    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginPresenter(this, this); //Evt. lav metode der kan sætte presenter på.
        dbManager = new DBManager();

        loading_screen = new Loading_Screen();
        setContentView(R.layout.activity_login_screen);

        logIn = findViewById(R.id.logInBtn);
        logIn.setOnClickListener(this);

        newUser = findViewById(R.id.newUser);
        newUser.setOnClickListener(this);

        username = findViewById(R.id.emailInput);
        username.setHint("Indtast email");
        username.setText("test@123.dk");

        password = findViewById(R.id.passwordInput);

        //password.setText(presenter.setText());//TODO Her er eksempel på at bruge logik gennem presenter -> Det skal anvendes til at opdatere views
        password.setHint("Indtast adgangskode");
        password.setText("123123");

        loginFrame = findViewById(R.id.login_frame);
        new_user_fragment_1= new New_user_fragment_1();
        tilFragmenter = (FrameLayout) findViewById(R.id.tilFragmenter_frame);

    }

    public void openNewUserScreen(){
        Intent intent = new Intent(this, NewUser_akt.class);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
      if(v.getId() == logIn.getId()){
          boolean erOK = presenter.checkLoginCredentials(username.getText().toString().trim(), password.getText().toString(),this);
          if(erOK) {
              //TODO evt. sæt loading screen her, sammen med de andre metoder for at hente fra DB
              //setFragment(loading_screen);
          }
      }
      else{
          openFragmenterAktivitet();
          //setFragment(new_user_fragment_1);

          //openNewUserScreen();
      }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //openloginScreen();
            //getSupportFragmentManager().popBackStackImmediate();
            this.getSupportFragmentManager().popBackStack();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
    public void openFragmenterAktivitet(){
        Intent intent = new Intent(this, TilFragmenter.class);
        startActivity(intent);
    }

    @Override
    public String getEmail() {
        return username.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void setErrorMsgPassword(String error) {
        password.setError(error);
    }

    @Override
    public void setErrorMsgEmail(String error){
        username.setError(error);
    }

    @Override
    public void setLoadingScreen(String message) {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.fragment_loading__screen, null);

        //get width
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;

        // create the popup window
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, (width-20), height, focusable);
        popupWindow.setContentView(popupView);

        popupWindow.showAtLocation(logIn, Gravity.CENTER, 0, 0);
    }


    public void setFragment(android.support.v4.app.Fragment fragment) {
        //loginFrame.setVisibility(View.INVISIBLE);
        //loginFrame.removeAllViews();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tilFragmenter_frame, fragment);

        //fragmentTransaction.addToBackStack(null);
        //this.getSupportFragmentManager().popBackStack();
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }


}
