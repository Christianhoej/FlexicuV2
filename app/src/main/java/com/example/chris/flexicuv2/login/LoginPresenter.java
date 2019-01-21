package com.example.chris.flexicuv2.login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.chris.flexicuv2.database.DBManager;
import com.example.chris.flexicuv2.loadingscreen.Loading_screen;

/**
 * @author Janus
 * Presenter klassen skal håndtere logik
 * opdatere loginviewet
 * og agere "mellemmand" mellem modeller/data og viewet
 */
public class LoginPresenter implements DBManager.SignInSuccess{
    private UpdateLoginScreen pres;
    private final String EMAIL_NOT_VALI_DMSG = "Den indtastede email adresse er ikke en email adresse";
    private final String PASSWORD_EMPTY = "Der skal indtastest et password";
    private final String EMAIL_EMPTY = "Der skal indtastest en email";
    private DBManager dbManager;
    private static final String TAG = "EmailPassword";
    private Context mContext;
    private String email, password;
    startAnimation startAnimation;

    public LoginPresenter(UpdateLoginScreen pres, Context mContext, startAnimation start){
        this.startAnimation = start;
        this.pres = pres;
        dbManager = new DBManager();
        this.mContext = mContext;
        dbManager.setSignInSuccess(this);
    }
    public String setText(){
        return "Gunn rules";
    }

    public void startAnim(){
        startAnimation.starAnimationMetode(true);
    }

    public boolean checkLoginCredentials(String email, String password, final Context context){
        //TODO lav "et tjek" af login for at se om loginoplysinger er ok
        this.email = email;
        this.password = password;
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
                else{

                    //pres.setLoadingScreen("Henter oplysninger...");
                    dbManager.signInAuth(context, email, password);

                    /*AsyncCheckLogIn async = new AsyncCheckLogIn();
                    async.execute();*/
                    return true;
                }

            }

        }
        else{
            pres.setErrorMsgEmail(EMAIL_EMPTY);
            return false;
        }

    }

    @Override
    public void userSignInSuccess(boolean success) {
        pres.loginSuccess(success);

    }

    public interface startAnimation{
        void starAnimationMetode(Boolean begynd);
    }

    @Override
    public void failureMesssage(String message) {
        pres.loginErrorMessage(message);
    }


    /**
     * Interfacet implementeres for at give
     * LoginPresenter mulighed for at opdatere viewet, da metoderne skal implementeres lokalt
     */
    public interface UpdateLoginScreen {
        String getEmail();

        String getPassword();

        void setErrorMsgPassword(String error);

        void setErrorMsgEmail(String error);
        void loginSuccess(boolean success);
        void loginErrorMessage(String string);

        //void setLoadingScreen(String message);
    }
}
