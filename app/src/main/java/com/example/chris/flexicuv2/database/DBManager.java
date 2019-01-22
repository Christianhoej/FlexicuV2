package com.example.chris.flexicuv2.database;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.chris.flexicuv2.model.Aftale;
import com.example.chris.flexicuv2.model.Forhandling;
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

import java.util.ArrayList;
import java.util.Map;

public class DBManager {

    private final String MEDARBEJDER = "medarbejder";
    private final String BRUGER = "bruger";
    private final String VIRKSOMHEDSID = "virksomhedsID";
    private final String AFTALE = "aftale";
    private final String FORHANDLING = "forhandling";
    private final String AKTIV = "aktiv";
    private Singleton singleton;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private CreateUserSuccess createUserSuccess;
    private SignInSuccess signInSuccess;
    private Map<String, Object> map;


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
                readAlleForhandling();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void createUdbud(Aftale medarbejderUdbud){
        //TestAfAftalerDB testAfAftalerDB = new TestAfAftalerDB();
        //Aftale udlej = testAfAftalerDB.getUdlej();
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        //medarbejderUdbud.getMedarbejder().setVirksomhedsID(uid);
        String medarbejderUdbudID = ref.child(AFTALE).push().getKey();
        medarbejderUdbud.setAftaleID(medarbejderUdbudID);

        ref.child(AFTALE).child(medarbejderUdbudID).setValue(medarbejderUdbud);
    }

    public void updateUdbud(Aftale aftale){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child(AFTALE).child(aftale.getAftaleID()).child("kommentar").setValue(aftale.getKommentar());
        ref.child(AFTALE).child(aftale.getAftaleID()).child("medarbejder").setValue(aftale.getMedarbejder());
        ref.child(AFTALE).child(aftale.getAftaleID()).child("slutDato").setValue(aftale.getSlutDato());
        ref.child(AFTALE).child(aftale.getAftaleID()).child("startDato").setValue(aftale.getStartDato());
        ref.child(AFTALE).child(aftale.getAftaleID()).child("timePris").setValue(aftale.getTimePris());
        ref.child(AFTALE).child(aftale.getAftaleID()).child("egetVærktøj").setValue(aftale.isEgetVærktøj());

    }

    public void addForhandling(Forhandling forhandling){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        String forhKey = ref.child(AFTALE).child(forhandling.getAftaleID()).push().getKey();
        forhandling.setForhandlingID(forhKey);
        ref.child(AFTALE).child(forhandling.getAftaleID()).child(forhKey).setValue(forhandling);
    }


    public void readAlleForhandling(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(AFTALE);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uid = mAuth.getCurrentUser().getUid();
                ArrayList<Aftale> mineUdlejninger = new ArrayList<>();
                ArrayList<Aftale> andresUdlejninger = new ArrayList<>();
                ArrayList<Aftale> lejAftaleMedForhandling = new ArrayList<>();
                ArrayList<Aftale> udlejAftaleMedForhandling = new ArrayList<>();
                ArrayList<Aftale> afsluttedeAftaler = new ArrayList<>();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Aftale udlej = snapshot.getValue(Aftale.class);

                    //Hvis aftalen er aktiv
                    if (udlej.isAktiv() == true) {

                        //Hvis udlejerID er lig med nuværende brugers ID
                        if (udlej.getUdlejer().getBrugerID().equals(uid)) {
                            mineUdlejninger.add(udlej);
                            //Hvis der er nogle forhandlinger
                            if (udlej.getForhandlinger() != null) {
                                udlejAftaleMedForhandling.add(udlej);
                            }
                        } else {
                            andresUdlejninger.add(udlej);
                            if (udlej.getForhandlinger() != null) {
                                for (Forhandling forhandling : udlej.getForhandlinger()) {
                                    if (forhandling.getLejer().getBrugerID().equals(uid)) {
                                        udlejAftaleMedForhandling.add(udlej);
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        for(Forhandling forhandling : udlej.getForhandlinger()){
                            if(!forhandling.isAftaleIndgået()){
                                udlej.removeForhandlinger(forhandling);
                            }
                        }
                        afsluttedeAftaler.add(udlej);
                    }
                }
                singleton.setMineLejAftalerMedForhandling(lejAftaleMedForhandling);
                singleton.setMineUdlejAftalerMedForhandling(udlejAftaleMedForhandling);
                singleton.setMineAfsluttedeAftaler(afsluttedeAftaler);
                singleton.setMineMedarbejderUdbud(mineUdlejninger);
                singleton.setAndresMedarbejderUdbud(andresUdlejninger);
               // readForhandling();
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
 /*  public void createForhandling(Forhandling forhandling){
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        forhandling.getMedarbejder().setVirksomhedsID(uid);

        String aftaleID = ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).push().getKey();
        forhandling.setAftaleID(aftaleID);
        String forhandlingID = ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(aftaleID).push().getKey();
        forhandling.setForhandlingID(forhandlingID);

        ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(aftaleID).child(forhandlingID).setValue(forhandling);
        //ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(aftaleID).child(forhandlingID).child("timestamp").setValue(ServerValue.TIMESTAMP);
    }

    public void addForhandling(Forhandling forhandling){
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        forhandling.getMedarbejder().setVirksomhedsID(uid);

        String forhandlingID = ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(forhandling.getAftaleID()).push().getKey();

        forhandling.setForhandlingID(forhandlingID);

        ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(forhandling.getAftaleID()).child(forhandlingID).setValue(forhandling);
        ref.child(FORHANDLING).child(forhandling.getOprindeligUdlejID()).child(forhandling.getAftaleID()).child(forhandlingID).child("timestamp").setValue(ServerValue.TIMESTAMP);
    }*/

 /*  public void readForhandling(){
        String uid = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(FORHANDLING);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Forhandling> udlejForhandling = new ArrayList<>();
                ArrayList<Forhandling> lejForhandling = new ArrayList<>();
                ArrayList<ArrayList<Forhandling>> forhandlingerIUdlejAftale = new ArrayList<>();
                ArrayList<ArrayList<Forhandling>> forhandlingerILejAftale = new ArrayList<>();
                //Gemmenløber alle ledige
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    //Gennemløber alle aftaler omkring den ledige
                    for(DataSnapshot snapshot1 :snapshot.getChildren()){
                        //Gennemløber alle forhandlinger i aftale
                        for(DataSnapshot snapshot2 : snapshot1.getChildren()){
                            System.out.println(snapshot2.getValue());

                            Forhandling forhandling = snapshot2.getValue(Forhandling.class);

                            if(forhandling.getUdlejer().getBrugerID().equals(mAuth.getCurrentUser().getUid())) {
                                udlejForhandling.add(forhandling);
                            }
                            else if(forhandling.getLejer().getBrugerID().equals(mAuth.getCurrentUser().getUid())){
                                lejForhandling.add(forhandling);
                            }
                        }
                        forhandlingerILejAftale.add(lejForhandling);
                        forhandlingerIUdlejAftale.add(udlejForhandling);
                    }
                }
                singleton.setMineMedarbejderUdbud(udlejForhandling);
                singleton.setMineLejForhandlinger(lejForhandling);
                signInSuccess.userSignInSuccess(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
*/


    public interface CreateUserSuccess {
        void userCreateSuccess(boolean success);
        void failureMesssage(String message);
    }

    public interface SignInSuccess{
        void userSignInSuccess(boolean success);
        void failureMesssage(String message);
    }


   /* public Map<String, Object> setMap(ArrayList<Forhandling> forhandling){
        this.map = new HashMap<String, Object>();
        for(Forhandling f : forhandling){
            map.put(f.get)
        }


    }*/

}