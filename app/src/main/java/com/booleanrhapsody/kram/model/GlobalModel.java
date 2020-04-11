package com.booleanrhapsody.kram.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class GlobalModel {

    static private GlobalModel instance = null;

    public static GlobalModel getInstance() {

        if (null == instance) {
            instance = new GlobalModel();
        }
        return instance;
    }


    private FirebaseAuth firebaseAuth;

    public FirebaseFirestore getFirestore() {
        return firestore;
    }

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
    }

    public FirebaseUser getCurrentUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        return currentUser;
    }


    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

}
