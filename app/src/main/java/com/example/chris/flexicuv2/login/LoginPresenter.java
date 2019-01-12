package com.example.chris.flexicuv2.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chris.flexicuv2.DBManager;
import com.example.chris.flexicuv2.StartSkærm.Startskaerm;
import com.example.chris.flexicuv2.model.Bruger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * @author Janus
 * Presenter klassen skal håndtere logik
 * opdatere loginviewet
 * og agere "mellemmand" mellem modeller/data og viewet
 */
public class LoginPresenter{
    private UpdateLoginScreen pres;
    private final String EMAIL_NOT_VALI_DMSG = "Den indtastede email adresse er ikke en email adresse";
    private final String PASSWORD_EMPTY = "Der skal indtastest et password";
    private final String EMAIL_EMPTY = "Der skal indtastest en email";
    private DBManager dbManager;
    private static final String TAG = "EmailPassword";

    public LoginPresenter(UpdateLoginScreen pres){
        this.pres = pres;
        dbManager = new DBManager();
    }
    public String setText(){
        return "Gunn rules";
    }
    public boolean checkLoginCredentials(String email, String password, final Context context){
        //TODO lav "et tjek" af login for at se om loginoplysinger er ok

        // If the email is not empty
        if(!TextUtils.isEmpty(email)){
            // If the email is not the format of an email
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                pres.setErrorMsgEmail(EMAIL_NOT_VALI_DMSG);
                return false;
            }
            else{
                // if password is empty
                if(TextUtils.isEmpty(password)){
                    pres.setErrorMsgPassword(PASSWORD_EMPTY);
                    return false;
                }

            }

        }
        else{
            pres.setErrorMsgEmail(EMAIL_EMPTY);
            return false;
        }
/*
       mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        success = true;
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            success = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            success = false;
                        }

                        // ...
                    }
                });
*/
/*        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            success = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            success = false;
                        }
                    }
                });*/

      /*  boolean success1 = dbManager.signInAuth(context,email,password);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(success1);
        return success1;
*/
        return true;
        //find bruger med match, hvis det findes
        //find tilsvarende password
        // if password == fundne password
        //return true;
        //else
        //return false;
    }



    /**
     * Interfacet implementeres for at give
     * LoginPresenter mulighed for at opdatere viewet, da metoderne skal implementeres lokalt
     */
    public interface UpdateLoginScreen{
        String getEmail();
        String getPassword();

        public void setErrorMsgPassword(String error);

        public void setErrorMsgEmail(String error);
    }
}
