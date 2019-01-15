package com.example.chris.flexicuv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.chris.flexicuv2.StartSkærm.Startskaerm;
import com.example.chris.flexicuv2.login.LoginScreen_akt;
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
    private final String VIRKSOMHEDSID = "virksomhedsID";
    private Bruger bruger;
    private Singleton singleton;
    private Medarbejder med1 = new Medarbejder();
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private static Integer success;
    private static Context mContext;
    private static String email, password;


    public DBManager() {
        singleton = Singleton.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void createBruger(Bruger bruger) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        String virkKey = mAuth.getCurrentUser().getUid();
        //String virkKey = ref.child(BRUGER).push().getKey();
        bruger.setBrugerID(virkKey);
        ref.child(BRUGER).child(virkKey).setValue(bruger);
    }

    public void createMedarbejder(Medarbejder medarbejder) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        String uid = mAuth.getCurrentUser().getUid();

        String medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        System.out.println("medarbejderkey: " + medKey);
        System.out.println("uid: " + uid);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.getBruger().addMedarbejdere(medarbejder);
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
        String uid = mAuth.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(BRUGER).child(uid);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               // for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    bruger = dataSnapshot.getValue(Bruger.class);
                    System.out.println("Bruger er sat!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                //}
                singleton.setBruger(bruger);
                readMedarbejdere();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

    public void readMedarbejdere(){
        String uid = mAuth.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(MEDARBEJDER).child(VIRKSOMHEDSID);


        ref.equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Medarbejder medarbejder = new Medarbejder();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    medarbejder = snapshot.getValue(Medarbejder.class);
                }
                singleton.getBruger().addMedarbejdere(medarbejder);
                mContext.startActivity(new Intent(mContext, Startskaerm.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }


    public void createUserAuth(final Context context, final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        success = 1;
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(context, "Bruger oprettet",
                                    Toast.LENGTH_SHORT).show();
                            success = 1;
                            } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Bruger kunne ikke oprettes",
                                    Toast.LENGTH_SHORT).show();
                            success = 0;
                            }
                    }

                });
    }

    public void signInAuth(final Context mContext, final String email, final String password){
        this.mContext = mContext;
        this.email = email;
        this.password = password;
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            readBruger();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}