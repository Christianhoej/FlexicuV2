package com.example.chris.flexicuv2.login;

import android.view.View;
import android.widget.EditText;

import com.example.chris.flexicuv2.model.Bruger;

/**
 * @author Janus
 * Presenter klassen skal håndtere logik
 * opdatere loginviewet
 * og agere "mellemmand" mellem modeller/data og viewet
 */
public class LoginPresenter{
    UpdateLoginScreen pres;
    //Bruger bruger;

    public LoginPresenter(UpdateLoginScreen pres){
        this.pres = pres;
    }
    public String setText(){
        return "Gunn rules";
    }
    public void checkLoginCredentials(String email, String password){
        //TODO lav "et tjek" af login for at se om loginoplysinger er ok

        pres.setErrorMsg("Fejl");
        pres.setErrorMsgEmailNotFilled("Fejl");
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

        public void setErrorMsg(String error);

        public void setErrorMsgEmailNotFilled(String error);
    }
}
