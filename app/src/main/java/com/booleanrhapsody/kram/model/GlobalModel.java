package com.booleanrhapsody.kram.model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.booleanrhapsody.kram.activity.WelcomeActivity;
import com.booleanrhapsody.kram.fragment.NurseDoctorsActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;

public class GlobalModel {

    static public String CAT_PATIENT = "patient";
    static public String CAT_DOCTOR = "doctor";
    static public String CAT_NURSE = "nurse";

    static private GlobalModel instance = null;

    public static GlobalModel getInstance() {

        if (null == instance) {
            instance = new GlobalModel();
            instance.init();
        }
        return instance;
    }


    private FirebaseAuth firebaseAuth;

    public FirebaseFirestore getFirestore() {
        return firestore;
    }

    private PatientChangeListener patientChangeListener;

    private FirebaseFirestore firestore;
    private String userCategory;

    public PatientModel getEditingPatient() {
        return editingPatient;
    }

    public void setEditingPatient(PatientModel editingPatient) {
        this.editingPatient = editingPatient;
    }

    public DoctorModel getEditingDoctor() {
        return editingDoctor;
    }

    public void setEditingDoctor(DoctorModel editingDoctor) {
        this.editingDoctor = editingDoctor;
    }

    private PatientModel editingPatient;
    private DoctorModel editingDoctor;
    private NurseModel editingNurse;

    private String userHospital;

    static {
        FirebaseFirestore.setLoggingEnabled(true);
    }

    public void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);

        userHospital = "100";
    }

    public PatientChangeListener startPatientListener(Query q, PatientChangeListener.OnPatientChangedListener listener) {

        this.stopPatientListener();

        this.patientChangeListener = new PatientChangeListener(q, listener);
        this.patientChangeListener.startListening();

        return this.patientChangeListener;
    }

    public void stopPatientListener() {

        if (this.patientChangeListener != null) {
            this.patientChangeListener.stopListening();
            this.patientChangeListener = null;
        }
    }

    public FirebaseUser getCurrentUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        return currentUser;
    }

    public NurseModel getEditingNurse() {
        return editingNurse;
    }

    public void setEditingNurse(NurseModel editingNurse) {
        this.editingNurse = editingNurse;
    }

    public String getUserHospital() {
        return userHospital;
    }

    public void setUserHospital(String userHospital) {
        this.userHospital = userHospital;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public Task<Void> logoutUser(Activity activity) {

        Task<Void> task = AuthUI.getInstance().signOut(activity);
        task.addOnCompleteListener(new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                // ...
                activity.startActivity(
                        WelcomeActivity.newIntent(activity));
            }
        });

        return task;
    }
}
