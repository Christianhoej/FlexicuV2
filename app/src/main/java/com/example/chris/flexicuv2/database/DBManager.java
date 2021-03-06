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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @Author Gunn
 */
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
        String forhKey = ref.child(AFTALE).child(forhandling.getAftaleID()).child(FORHANDLING).push().getKey();
        forhandling.setForhandlingID(forhKey);
        ref.child(AFTALE).child(forhandling.getAftaleID()).child(FORHANDLING).child(forhKey).setValue(forhandling);
    }

    public void updateForhandling(Forhandling forhandling){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child(AFTALE).child(forhandling.getAftaleID()).child(FORHANDLING).child(forhandling.getForhandlingID()).setValue(forhandling);
    }

    public void updateIndgåetAftale(Aftale aftale, Forhandling forhandling){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child(AFTALE).child(forhandling.getAftaleID()).child(FORHANDLING).child(forhandling.getForhandlingID()).setValue(forhandling);
        for(Forhandling forhandling1 : aftale.getForhandlinger()){
            if(!forhandling.getForhandlingID().equals(forhandling1.getForhandlingID())){
                forhandling1.setAktiv(false);
                forhandling1.setAftaleIndgået(false);
                ref.child(AFTALE).child(forhandling.getAftaleID()).child(FORHANDLING).child(forhandling1.getForhandlingID()).setValue(forhandling1);
                singleton.getAlleMineAftalerMedForhandling().remove(forhandling1);

            }
        }
    }

    public void updateAfsluttetAftale(Aftale aftale, Forhandling forhandling){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child(AFTALE).child(forhandling.getAftaleID()).child(FORHANDLING).child(forhandling.getForhandlingID()).setValue(forhandling);
        for(Forhandling forhandling1 : aftale.getForhandlinger()){
            if(!forhandling.getForhandlingID().equals(forhandling1.getForhandlingID())){
                forhandling1.setAktiv(false);
                forhandling1.setAftaleIndgået(false);
                ref.child(AFTALE).child(forhandling.getAftaleID()).child(AKTIV).setValue(false);
                ref.child(AFTALE).child(forhandling.getAftaleID()).child(FORHANDLING).child(forhandling1.getForhandlingID()).setValue(forhandling1);
                singleton.getAlleMineAftalerMedForhandling().remove(forhandling1);

            }
        }
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
                ArrayList<Aftale> lejIndgåedeMenIkkeAfsluttede = new ArrayList<>();
                ArrayList<Aftale> udlejIndgåedeMenIkkeAfsluttede = new ArrayList<>();
                ArrayList<Aftale> alleMineAftalerMedForhandling = new ArrayList<>();

                long tidIdag = System.currentTimeMillis();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Aftale udlej = snapshot.getValue(Aftale.class);

                    //Tilføjer alle forhandlinger til aftalen
                    for(DataSnapshot snapshotForhandling : snapshot.child(FORHANDLING).getChildren()) {
                        Forhandling forhandling = snapshotForhandling.getValue(Forhandling.class);
                        udlej.addForhandlinger(forhandling);
                    }

                    // Hvis aftalen ikke er aktiv
                    if(!udlej.isAktiv()){
                        afsluttedeAftaler.add(udlej);
                        continue;
                    }

                    //Hvis aftalen er indgået



                    //Hvis aftalen er aktiv
                    System.out.println("UDLEJ ER HVAD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1"+udlej.isAktiv());
                    if (udlej.isAktiv()) {
                        System.out.println("kommet ind");
                        boolean indgået = false;
                        for(Forhandling forhandling : udlej.getForhandlinger()) {
                            if (forhandling.isAftaleIndgået()){
                                indgået = true;
                                try {
                                    String dateString = forhandling.getLejerSlutDato().replace(" ", "");
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    Date date = sdf.parse(dateString);
                                    long startDate = date.getTime();
                                    //Hvis aftalen er indgået men ikke aktiv
                                    if (tidIdag > startDate) {
                                        udlej.setAktiv(false);
                                        updateAfsluttetAftale(udlej, forhandling);
                                        afsluttedeAftaler.add(udlej);
                                        break;
                                    } else {
                                        if (forhandling.getLejer().getBrugerID().equals(uid)) {
                                            lejIndgåedeMenIkkeAfsluttede.add(udlej);
                                            break;
                                        } else {
                                            udlejIndgåedeMenIkkeAfsluttede.add(udlej);
                                            break;
                                        }
                                    }

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if(indgået){
                            continue;
                        }
                        // Hvis jeg er udlejer
                        if (udlej.getUdlejer().getBrugerID().equals(uid)) {
                            System.out.println("UdlejerID: " + udlej.getUdlejer().getBrugerID() + ", uid: " + uid);
                            mineUdlejninger.add(udlej);
                            //Hvis der er nogle forhandlinger
                            if (udlej.getForhandlinger() != null) {
                                udlejAftaleMedForhandling.add(udlej);
                            }
                        } else {
                            andresUdlejninger.add(udlej);
                            // Hvis der er nogle udlejninger
                            if (udlej.getForhandlinger() != null) {
                                for (Forhandling forhandling : udlej.getForhandlinger()) {
                                    //Hvis jeg er lejer
                                    if (forhandling.getLejer().getBrugerID().equals(uid)) {
                                        lejAftaleMedForhandling.add(udlej);
                                        break;
                                    }
                                }
                            }
                        }
                        alleMineAftalerMedForhandling.add(udlej);

                   }

                }
                singleton.setMineLejIndgåedeAftaler(lejIndgåedeMenIkkeAfsluttede);
                singleton.setMineUdlejIndgåedeAftaler(udlejIndgåedeMenIkkeAfsluttede);
                System.out.println("UdlejIndgået" + singleton.getMineUdlejIndgåedeAftaler().size());
                System.out.println("LejIndgået" + singleton.getMineLejIndgåedeAftaler().size());
                singleton.setAlleMineAftalerMedForhandling(alleMineAftalerMedForhandling);
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



    public interface CreateUserSuccess {
        void userCreateSuccess(boolean success);
        void failureMesssage(String message);
    }

    public interface SignInSuccess{
        void userSignInSuccess(boolean success);
        void failureMesssage(String message);
    }
}