package com.booleanrhapsody.kram.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class GlobalModel {

    static private GlobalModel instance = null;

    public static GlobalModel getInstance() {

        if (null == instance) {
            instance = new GlobalModel();
        }
        return instance;
    }


    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userCategory;


    static {
        FirebaseFirestore.setLoggingEnabled(true);
    }

    public void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
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
