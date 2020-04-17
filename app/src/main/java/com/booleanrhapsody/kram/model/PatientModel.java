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

    static public String STATUS_WAIT = "2-waiting";
    static public String STATUS_ASSIGNED = "1-assigned";
    static public String STATUS_COMPLETED = "0-completed";

    private static final String TAG = "PatientModel";

    private static final CollectionReference patientsCollection =
            FirebaseFirestore.getInstance().collection("patients");

    private static final Query patientsQuery =
            patientsCollection.whereGreaterThanOrEqualTo("status", PatientModel.STATUS_COMPLETED)
                    .orderBy("status", Query.Direction.DESCENDING)
                    .orderBy("timestamp", Query.Direction.ASCENDING)
                    .limit(50);

    private static List<PatientModel> patients;

    public static  Task<QuerySnapshot> fetch() {
        return patientsCollection.get();
    }

    public static Task<Void> save(PatientModel patient) {

        return patientsCollection.document(patient.getId()).set(patient);
    }

    public static Task<DocumentReference> add(PatientModel patient) {
        return patientsCollection.add(patient);
    }

    private String name;
    private Integer severity;
    private String status;

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
        this.status = STATUS_WAIT;
    }

    public static CollectionReference getPatientsCollection() {
        return patientsCollection;
    }

    public static Query getPatientsQuery() {
        return patientsQuery;
    }

    public String getHospital() {
        return hospital;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    private String hospital;

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
