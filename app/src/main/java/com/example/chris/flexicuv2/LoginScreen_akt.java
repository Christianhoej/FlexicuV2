package com.example.chris.flexicuv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chris.flexicuv2.model.Test;

public class LoginScreen_akt extends AppCompatActivity implements View.OnClickListener {
    private Button logIn;
    private Button newUser;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        logIn = findViewById(R.id.logInBtn);
        logIn.setOnClickListener(this);

        newUser = findViewById(R.id.newUser);
        newUser.setOnClickListener(this);

        username = findViewById(R.id.emailInput);
        username.setHint("Indtast email");

        password = findViewById(R.id.passwordInput);
        password.setHint("Indtast adgangskode");
        Test test = new Test();
    }

    public void openStartScreen(){
        Intent intent = new Intent(this, Startskaerm_akt.class);
        startActivity(intent);
    }

    public void openNewUserScreen(){
        Intent intent = new Intent(this, NewUser_akt.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
      /*  boolean rightInput = true;
        // If the login button is pressed
        if(v.getId() == logIn.getId()){
            // If the email edittext is not empty
            if(!isEmpty(username)){
                // If the email is not the format of an email
                if(!Patterns.EMAIL_ADDRESS.matcher(username.getText().toString()).matches()){
                    username.setError("Den indtastede email adresse eksisterer ikke");
                    rightInput = false;
                }
                else{
                    // if password is empty
                    if(isEmpty(password)) {
                        password.setError("Du skal indtaste et password");
                        rightInput = false;
                    }
                }
            }
            else{
                username.setError("Du skal indtaste en email adresse");
                rightInput = false;
            }

            // if all conditions above is met, open startscreen
            if(rightInput) {
                openStartScreen();
            }
        }
        // if the new user button is pressed
        else if(v.getId() == newUser.getId()){
            openNewUserScreen();
        }*/

      if(v.getId() == logIn.getId()){
          openStartScreen();
      }
      else{
          openNewUserScreen();
      }

    }



    public boolean isEmpty(EditText text){
        return TextUtils.isEmpty(text.getText().toString());
    }
}
