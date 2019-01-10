package com.example.chris.flexicuv2;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.chris.flexicuv2.model.Bruger;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;
import com.example.chris.flexicuv2.opret_bruger.NewUser_akt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DBManager extends NewUser_akt implements View.OnClickListener{

    private final String MEDARBEJDER = "medarbejder";
    private final String BRUGER = "bruger";
    private Bruger bruger;
    private Singleton singleton;
    private Medarbejder med1 = new Medarbejder();
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private static boolean success;


    public DBManager() {
        singleton = Singleton.getInstance();
        mAuth = FirebaseAuth.getInstance();
        bruger = new Bruger();
        bruger.setVirksomhedCVR("11223344æljlkj");
        bruger.setVirksomhedsnavn("Flexicu");
        bruger.setAdresse("Anker Engelundsvej 1");
        bruger.setPostnr("2800");
        singleton.setBruger(bruger);
        med1.setNavn("Oliver");
        med1.setLoen(100);
        med1.setArbejdsomraade("Smed");

        singleton.addMedarbejder(med1);
        createBruger(bruger);
        createMedarbejder(med1);

    }

    public void createBruger(Bruger bruger) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        String virkKey = ref.child(BRUGER).push().getKey();
        bruger.setBrugerID(virkKey);
        ref.child(BRUGER).child(virkKey).setValue(bruger);
    }

    public void createMedarbejder(Medarbejder medarbejder) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        String medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(singleton.getBruger().getBrugerID()).child(MEDARBEJDER).setValue(medKey);
    }

    public void updateBruger(Bruger bruger) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.child(BRUGER).child(bruger.getBrugerID()).setValue(bruger);
    }


    public void updateMedarbejder(Medarbejder medarbejder) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child(MEDARBEJDER).child(medarbejder.getMedarbejderID()).setValue(medarbejder);
    }

    public void readBruger(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(BRUGER);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public boolean createUserAuth(final Context context, String email, String password) {
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
        return success;

    }

    public boolean signInAuth(final Context context, String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();

                            success = true;
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            success = false;
                        }
                    }
                });
        return success;

    }


}