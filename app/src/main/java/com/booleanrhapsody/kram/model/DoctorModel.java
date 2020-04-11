package com.booleanrhapsody.kram.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.List;

@IgnoreExtraProperties
public class DoctorModel  {

    private static final String TAG = "DoctorModel";

    private static final CollectionReference doctorCollection =
            FirebaseFirestore.getInstance().collection("doctors");

    private static final Query doctorsQuery =
            doctorCollection.orderBy("timestamp", Query.Direction.DESCENDING).limit(50);


    private static List<DoctorModel> doctors;

    public static void fetch() {
        doctorCollection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public static void add(DoctorModel doctor) {
        doctorCollection.
                add(doctor)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private String name;
    private String specialty;
    private String uid;
    private Date timeStamp;

    public DoctorModel() {
        // Needed for Firebase
    }

    public DoctorModel(String name, String message, String uid) {
        this.name = name;
        specialty = message;
        this.uid = uid;
    }

    public static CollectionReference getDoctorCollection() {
        return doctorCollection;
    }

    public static Query getDoctorsQuery() {
        return doctorsQuery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String message) {
        specialty = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @ServerTimestamp
    public Date getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(Date timestamp) {
        timeStamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorModel doctor = (DoctorModel) o;

        return timeStamp.equals(doctor.timeStamp)
                && uid.equals(doctor.uid)
                && (name == null ? doctor.name == null : name.equals(doctor.name))
                && (specialty == null ? doctor.specialty == null : specialty.equals(doctor.specialty));
    }

    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result = 31 * result + (specialty == null ? 0 : specialty.hashCode());
        result = 31 * result + uid.hashCode();
        result = 31 * result + timeStamp.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Name='" + name + '\'' +
                ", Specialty='" + specialty + '\'' +
                ", Uid='" + uid + '\'' +
                ", Timestamp=" + timeStamp +
                '}';
    }
}
