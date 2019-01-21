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
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class DBManager {

    private final String MEDARBEJDER = "medarbejder";
    private final String BRUGER = "bruger";
    private final String VIRKSOMHEDSID = "virksomhedsID";
    private final String AFTALE = "aftale";
    private final String LEDIG = "ledig";
    private final String FORHANDLING = "forhandling";
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
        System.out.println("uid i createBruger: " + mAuth.getCurrentUser().getUid());
        String virkKey = mAuth.getCurrentUser().getUid();
        bruger.setBrugerID(virkKey);
        ref.child(BRUGER).child(virkKey).setValue(bruger);
        createUserSuccess.userCreateSuccess(true);
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

    public void readBruger(String uid){
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
        System.out.println("UID: " + uid);
        ref.orderByChild(VIRKSOMHEDSID).equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Medarbejder> medarbejdere = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    medarbejdere.add(snapshot.getValue(Medarbejder.class));

                }
                singleton.setMedarbejdere(medarbejdere);
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
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        udlej.getMedarbejder().setVirksomhedsID(uid);
        String udlejID = ref.child(LEDIG).push().getKey();
        udlej.setOprindeligUdlejID(udlejID);

        ref.child(LEDIG).child(udlejID).setValue(udlej);
    }

    public void updateUdlej(Aftale udlej){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child(MEDARBEJDER).child(udlej.getOprindeligUdlejID()).setValue(udlej);
    }

    public void createForhandling(Aftale forhandling){
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        forhandling.getMedarbejder().setVirksomhedsID(uid);

        String aftaleID = ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).push().getKey();
        forhandling.setAftaleID(aftaleID);
        String forhandlingID = ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(aftaleID).push().getKey();
        forhandling.setForhandlingID(forhandlingID);

        ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(aftaleID).child(forhandlingID).setValue(forhandling);
        ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(aftaleID).child(forhandlingID).child("timestamp").setValue(ServerValue.TIMESTAMP);
    }

    public void addForhandling(Aftale forhandling){
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        forhandling.getMedarbejder().setVirksomhedsID(uid);

        String forhandlingID = ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(forhandling.getAftaleID()).push().getKey();

        forhandling.setForhandlingID(forhandlingID);

        ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(forhandling.getAftaleID()).child(forhandlingID).setValue(forhandling);
        ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(forhandling.getAftaleID()).child(forhandlingID).child("timestamp").setValue(ServerValue.TIMESTAMP);
    }


    public void readForhandling(){
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(FORHANDLING);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Aftale> udlejForhandling = new ArrayList<>();
                ArrayList<Aftale> lejForhandling = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    for(DataSnapshot snapshot1 :snapshot.getChildren()){
                        for(DataSnapshot snapshot2 : snapshot1.getChildren()){
                            System.out.println(snapshot2.getValue());

                            Aftale forhandling = snapshot2.getValue(Aftale.class);

                            if(forhandling.getUdlejer().getBrugerID().equals(mAuth.getCurrentUser().getUid())) {
                                udlejForhandling.add(forhandling);
                            }
                            else if(forhandling.getLejer().getBrugerID().equals(mAuth.getCurrentUser().getUid())){
                                lejForhandling.add(forhandling);
                            }
                        }
                    }
                }
                singleton.setMineUdlejForhandlinger(udlejForhandling);
                singleton.setMineLejForhandlinger(lejForhandling);
                signInSuccess.userSignInSuccess(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void readAlleUdlej(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(LEDIG);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uid = mAuth.getCurrentUser().getUid();
                ArrayList<Aftale> mineUdlejninger = new ArrayList<>();
                ArrayList<Aftale> andresUdlejninger = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Aftale udlej = snapshot.getValue(Aftale.class);

                    if(!udlej.getUdlejer().getBrugerID().equals(uid)){
                        mineUdlejninger.add(udlej);
                    }
                    else {
                        andresUdlejninger.add(udlej);
                    }
                }
                singleton.setMineLedigeMedarbejdere(andresUdlejninger);
                singleton.setMedarbejdereTilUdlejning(mineUdlejninger);
                readForhandling();
                //signInSuccess.userSignInSuccess(true);
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
                            createBruger(singleton.midlertidigBruger);
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

                            readBruger(mAuth.getCurrentUser().getUid());


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


    public class YourModelClass {
        //private fields
        private Map<String, String> timestamp;

        public YourModelClass() {}

        //public setters and getters for the fields

        public void setTimestamp(Map<String, String> timeStamp) {this.timestamp= timestamp;}
        public Map<String, String> getTimestamp() {return timestamp;}
    }
}