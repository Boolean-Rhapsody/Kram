package com.booleanrhapsody.kram.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class DoctorModel  {

    private static final String TAG = "DoctorModel";

    private static final CollectionReference doctorCollection =
            FirebaseFirestore.getInstance().collection("doctors");

    private static final Query doctorsQuery =
            doctorCollection.orderBy("timestamp", Query.Direction.DESCENDING).limit(50);


    public static  Task<QuerySnapshot> fetch() {
        return doctorCollection.get();
    }

    public static Task<Void> save(DoctorModel nurse) {

        return doctorCollection.document(nurse.getId()).set(nurse);
    }

    public static Task<DocumentReference> add(DoctorModel nurse) {
        return doctorCollection.add(nurse);
    }

    private String name;
    private String specialty;
    private String id;
    private Date timeStamp;
    private boolean onCall;

    public boolean isOnCall() {
        return onCall;
    }

    public void setOnCall(boolean onCall) {
        this.onCall = onCall;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    private String hospital;

    public DoctorModel() {
        // Needed for Firebase
    }

    public DoctorModel(String name, String message, String uid) {
        this.name = name;
        specialty = message;
        this.id = uid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorModel doctor = (DoctorModel) o;

        return timeStamp.equals(doctor.timeStamp)
                && id.equals(doctor.id)
                && (name == null ? doctor.name == null : name.equals(doctor.name))
                && (specialty == null ? doctor.specialty == null : specialty.equals(doctor.specialty));
    }

    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result = 31 * result + (specialty == null ? 0 : specialty.hashCode());
        result = 31 * result + id.hashCode();
        result = 31 * result + timeStamp.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Name='" + name + '\'' +
                ", Specialty='" + specialty + '\'' +
                ", Uid='" + id + '\'' +
                ", Timestamp=" + timeStamp +
                '}';
    }
}
