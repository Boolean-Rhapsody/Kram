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
public class PatientModel  {

    private static final String TAG = "PatientModel";

    private static final CollectionReference patientsCollection =
            FirebaseFirestore.getInstance().collection("patients");

    private static final Query patientsQuery =
            patientsCollection.orderBy("timestamp", Query.Direction.DESCENDING).limit(50);

    private static List<PatientModel> patients;

    public static void fetch() {
        patientsCollection
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

    public static void save(PatientModel patient) {

        if (patient.getId() != null) {
            patientsCollection.document(patient.getId()).set(patient);
        }
    }

    public static void add(PatientModel patient) {
        patientsCollection.
        add(patient)
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
    private Integer severity;

    private String id;
    private Date timeStamp;

    private Date startTime;
    private Date stopTime;
    private String doctor;

    public PatientModel() {
        // Needed for Firebase
    }

    public PatientModel(String name, Integer message, String uid) {
        this.name = name;
        severity = message;
        this.id = uid;
    }

    public static CollectionReference getPatientsCollection() {
        return patientsCollection;
    }

    public static Query getPatientsQuery() {
        return patientsQuery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer message) {
        severity = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ServerTimestamp
    public Date getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(Date timestamp) {
        timeStamp = timestamp;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientModel doctor = (PatientModel) o;

        return timeStamp.equals(doctor.timeStamp)
                && id.equals(doctor.id)
                && (name == null ? doctor.name == null : name.equals(doctor.name))
                && (severity == null ? doctor.severity == null : severity.equals(doctor.severity));
    }

    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result = 31 * result + (severity == null ? 0 : severity.hashCode());
        result = 31 * result + id.hashCode();
        result = 31 * result + timeStamp.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "Name='" + name + '\'' +
                ", Severity='" + severity + '\'' +
                ", Uid='" + id + '\'' +
                ", Timestamp=" + timeStamp +
                '}';
    }
}
