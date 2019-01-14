
package com.example.chris.flexicuv2.login;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;


import com.example.chris.flexicuv2.DBManager;
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
    private FrameLayout loginFrame;
    private FrameLayout tilFragmenter;

    private DBManager dbManager;



    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginPresenter(this, this); //Evt. lav metode der kan sætte presenter på.
        dbManager = new DBManager();


        setContentView(R.layout.activity_login_screen);

        logIn = findViewById(R.id.logInBtn);
        logIn.setOnClickListener(this);

        newUser = findViewById(R.id.newUser);
        newUser.setOnClickListener(this);

        username = findViewById(R.id.emailInput);
        username.setHint("Indtast email");
        username.setText("gunn@test.dk");

        password = findViewById(R.id.passwordInput);

        //password.setText(presenter.setText());//TODO Her er eksempel på at bruge logik gennem presenter -> Det skal anvendes til at opdatere views
        password.setHint("Indtast adgangskode");
        password.setText("123qwe");

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
          presenter.checkLoginCredentials(username.getText().toString().trim(), password.getText().toString(),this);

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
