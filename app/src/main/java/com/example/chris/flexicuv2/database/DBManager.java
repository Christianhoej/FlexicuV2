package com.example.chris.flexicuv2.database;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.chris.flexicuv2.model.Aftale;
import com.example.chris.flexicuv2.model.Bruger;
import com.example.chris.flexicuv2.model.Medarbejder;
import com.example.chris.flexicuv2.model.Singleton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DBManager {

    private final String MEDARBEJDER = "medarbejder";
    private final String BRUGER = "bruger";
    private final String VIRKSOMHEDSID = "virksomhedsID";
    private final String AFTALE = "aftale";
    private final String LEDIG = "ledig";
    private Singleton singleton;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private CreateUserSuccess createUserSuccess;
    private SignInSuccess signInSuccess;


    public DBManager() {
        singleton = Singleton.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void setCreateUserSuccess(CreateUserSuccess createUserSuccess) {
        this.createUserSuccess = createUserSuccess;
    }

    public void setSignInSuccess(SignInSuccess signInSuccess) {
        this.signInSuccess = signInSuccess;
    }

    public void createBruger(Bruger bruger) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        String virkKey = mAuth.getCurrentUser().getUid();
        bruger.setBrugerID(virkKey);
        ref.child(BRUGER).child(virkKey).setValue(bruger);
    }

    public void createMedarbejder(Medarbejder medarbejder) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        String uid = mAuth.getCurrentUser().getUid();
        medarbejder.setVirksomhedsID(uid);
        String medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.addMedarbejder(medarbejder);

        uid = mAuth.getCurrentUser().getUid();
        medarbejder.setVirksomhedsID(uid);
        medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.addMedarbejder(medarbejder);

        uid = mAuth.getCurrentUser().getUid();
        medarbejder.setVirksomhedsID(uid);
        medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.addMedarbejder(medarbejder);

        uid = mAuth.getCurrentUser().getUid();
        medarbejder.setVirksomhedsID(uid);
        medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.addMedarbejder(medarbejder);

        uid = mAuth.getCurrentUser().getUid();
        medarbejder.setVirksomhedsID(uid);
        medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.addMedarbejder(medarbejder);

        uid = mAuth.getCurrentUser().getUid();
        medarbejder.setVirksomhedsID(uid);
        medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.addMedarbejder(medarbejder);

        uid = mAuth.getCurrentUser().getUid();
        medarbejder.setVirksomhedsID(uid);
        medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.addMedarbejder(medarbejder);

        uid = mAuth.getCurrentUser().getUid();
        medarbejder.setVirksomhedsID(uid);
        medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.addMedarbejder(medarbejder);

        uid = mAuth.getCurrentUser().getUid();
        medarbejder.setVirksomhedsID(uid);
        medKey = ref.child(MEDARBEJDER).push().getKey();
        medarbejder.setMedarbejderID(medKey);
        ref.child(MEDARBEJDER).child(medKey).setValue(medarbejder);

        ref.child(BRUGER).child(uid).child(MEDARBEJDER).child(medKey).setValue(medKey);
        singleton.addMedarbejder(medarbejder);
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
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(BRUGER).child(uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bruger bruger = dataSnapshot.getValue(Bruger.class);
                singleton.setBruger(bruger);
                readMedarbejdere();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readMedarbejdere(){
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(MEDARBEJDER);

        ref.orderByChild(VIRKSOMHEDSID).equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Medarbejder medarbejder = snapshot.getValue(Medarbejder.class);
                    singleton.addMedarbejder(medarbejder);
                }
                readAlleUdlej();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void createUdlej(Aftale udlej){
        //TestAfAftalerDB testAfAftalerDB = new TestAfAftalerDB();
        //Aftale udlej = testAfAftalerDB.getUdlej();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        String udlejID = ref.child(LEDIG).push().getKey();
        udlej.setAftaleID(udlejID);

        ref.child(LEDIG).child(udlejID).setValue(udlej);
    }

    public void updateUdlej(Aftale udlej){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child(MEDARBEJDER).child(udlej.getAftaleID()).setValue(udlej);
    }

    public void readAlleUdlej(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(LEDIG);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uid = mAuth.getCurrentUser().getUid();
                System.out.println("dataSnapshot: " + dataSnapshot.getChildrenCount());
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Aftale udlej = snapshot.getValue(Aftale.class);

                   // if(!udlej.getUdlejer().getBrugerID().equals(uid)){
                        singleton.addMedarbejderTilUdlejning(udlej.getMedarbejder());
                   // }
                }
                signInSuccess.userSignInSuccess(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void createUserAuth(Context context, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context,  new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            singleton.userID = mAuth.getCurrentUser().getUid();
                            System.out.println(singleton.userID);
                            createUserSuccess.userCreateSuccess(true);
                            }
                            else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                           // createUserSuccess.userCreateSuccess(false);
                            }
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                createUserSuccess.failureMesssage(e.getLocalizedMessage());
            }
        });
    }

    public void signInAuth(Context mContext, String email, String password){
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

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException || e instanceof FirebaseAuthInvalidUserException) {
                    signInSuccess.failureMesssage("De indtastede oplysninger er forkerte");
                } else {
                    signInSuccess.failureMesssage(e.getLocalizedMessage());
                }
                signInSuccess.userSignInSuccess(false);
            }
        });
    }



    public interface CreateUserSuccess {
        void userCreateSuccess(boolean success);
        void failureMesssage(String message);
    }

    public interface SignInSuccess{
        void userSignInSuccess(boolean success);
        void failureMesssage(String message);
    }
}