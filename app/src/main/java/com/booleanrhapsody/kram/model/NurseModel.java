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
public class NurseModel  {

    private static final String TAG = "NurseModel";

    private static final CollectionReference nursesCollection =
            FirebaseFirestore.getInstance().collection("nurses");

    private static final Query nursesQuery =
            nursesCollection.orderBy("timestamp", Query.Direction.DESCENDING).limit(50);

    public static  Task<QuerySnapshot> fetch() {
        return nursesCollection.get();
    }

    public static Task<Void> save(NurseModel nurse) {

        return nursesCollection.document(nurse.getId()).set(nurse);
    }

    public static Task<DocumentReference> add(NurseModel nurse) {
        return nursesCollection.add(nurse);
    }

    private String name;
    private String id;
    private Date timeStamp;

    public NurseModel() {
        // Needed for Firebase
    }

    public NurseModel(String name, String uid) {
        this.name = name;
        this.id = uid;
    }

    public static CollectionReference getNursesCollection() {
        return nursesCollection;
    }

    public static Query getNursesQuery() {
        return nursesQuery;
    }

    public String getHospital() {
        return hospital;
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

        NurseModel doctor = (NurseModel) o;

        return timeStamp.equals(doctor.timeStamp)
                && id.equals(doctor.id)
                && (name == null ? doctor.name == null : name.equals(doctor.name));
    }

    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + timeStamp.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "Name='" + name + '\'' +
                ", Uid='" + id + '\'' +
                ", Timestamp=" + timeStamp +
                '}';
    }
}
