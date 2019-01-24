
package com.example.chris.flexicuv2.login;


import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.startskærm.Startskaerm_akt;
import com.example.chris.flexicuv2.loadingscreen.Loading_screen;
import com.example.chris.flexicuv2.R;
import com.example.chris.flexicuv2.opret_bruger.Opret_bruger_fragment_1;
import com.example.chris.flexicuv2.opret_bruger.Opret_bruger_akt;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import com.google.firebase.analytics.FirebaseAnalytics;

public class LoginScreen_akt extends AppCompatActivity implements View.OnClickListener, LoginPresenter.UpdateLoginScreen {

    private Button logIn;
    private Button newUser;
    private EditText username;
    private EditText password;

    private Opret_bruger_fragment_1 new_user_fragment_1;
    private Loading_screen loading_screen;
    private FrameLayout loginFrame;
    private FrameLayout tilFragmenter;

    private DBManager dbManager;

    private FrameLayout tilAnimation;
    private FrameLayout flex_roter;

    private LoginPresenter presenter;
    TextView tv;
    ImageView ivX;
    private Animation rotateAnim;
    private ImageView logo;
    private Fragment animfrag;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if (!EMULATOR) {
            Fabric.with(this, new Crashlytics());
        }

/*        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Gunn");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
*/

        presenter = new LoginPresenter(this, this, loading_screen); //Evt. lav metode der kan sætte presenter på.
        dbManager = new DBManager();
        //logo = findViewById(R.id.flexLogo);
        loading_screen = new Loading_screen();
        setContentView(R.layout.login_screen_akt);
        tilAnimation = (FrameLayout) findViewById(R.id.tilAnimation);

        //animfrag = findViewById(R.id.fragment1);

        tv = findViewById(R.id.loading_text2);
        ivX = findViewById(R.id.x_logo);
        rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotation);

        logIn = findViewById(R.id.logInBtn);
        logIn.setOnClickListener(this);

        newUser = findViewById(R.id.newUser);
        newUser.setOnClickListener(this);

        username = findViewById(R.id.emailInput);
        username.setHint("Indtast email");
        //username.setText("test@123.dk");
        username.setText("123@123.dk");
        password = findViewById(R.id.passwordInput);

        //password.setText(presenter.setText());//TODO Her er eksempel på at bruge logik gennem presenter -> Det skal anvendes til at opdatere views
        password.setHint("Indtast adgangskode");
        password.setText("123123");

        loginFrame = findViewById(R.id.login_frame);
        new_user_fragment_1= new Opret_bruger_fragment_1();
        tilFragmenter = (FrameLayout) findViewById(R.id.tilFragmenter_frame);

        //setFragment(loading_screen);
    }



    @Override
    public void onClick(View v) {
      if(v.getId() == logIn.getId()){
          boolean erOK = presenter.checkLoginCredentials(username.getText().toString().trim(), password.getText().toString(),this);
          if(erOK) {
              //TODO evt. sæt loading screen her, sammen med de andre metoder for at hente fra DB
              //ivX = findViewById(R.id.x_logo);
              //loginFrame.setAlpha();
              logIn.setEnabled(false);
              newUser.setEnabled(false);
              setAnimation(loading_screen);

              //logo = findViewById(R.id.x_logo);
              //logo.setVisibility(View.INVISIBLE);

              //Context context;
              //v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation));
              //ivX.startAnimation(rotateAnim);
              //presenter.startAnim();
              //tv.setText("Loading...");

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
        Intent intent = new Intent(this, Opret_bruger_akt.class);
        startActivity(intent);
    }

    public void openStartskaerm(){
        Intent intent = new Intent(this, Startskaerm_akt.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginSuccess(boolean success) {
        if(success){
            openStartskaerm();
        }
    }

    @Override
    public void loginErrorMessage(String message) {
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
        removeAnimation(loading_screen);
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
/*
    @Override
    public void setLoadingScreen(String message) {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.loading_screen_fragment, null);

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

*/
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

    public void setAnimation(Fragment fragment) {
        //loginFrame.setVisibility(View.INVISIBLE);
        //loginFrame.removeAllViews();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.tilAnimation, fragment);

        //fragmentTransaction.addToBackStack(null);
        //this.getSupportFragmentManager().popBackStack();
        fragmentTransaction.addToBackStack("fragment");
        fragmentTransaction.commit();
    }

    public void removeAnimation(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment).commit();
        //fragmentTransaction.addToBackStack(null);
        //this.getSupportFragmentManager().popBackStack();
 //       fragmentTransaction.addToBackStack("fragment");
   //     fragmentTransaction.commit();
    }


}
