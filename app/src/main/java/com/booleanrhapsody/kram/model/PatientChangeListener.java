package com.booleanrhapsody.kram.model;

import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class PatientChangeListener implements EventListener<QuerySnapshot> {

    private static final String TAG = "PatientChangeListener";

    private Query query;
    private ListenerRegistration registration;

    private ArrayList<DocumentSnapshot> documentSnapshots = new ArrayList<>();

    public PatientChangeListener(Query q) {
        this.query = q;
    }

    public void recalculateStats() {

        for(DocumentSnapshot snapshot: this.documentSnapshots) {

            PatientModel patient = snapshot.toObject(PatientModel.class);
            
        }
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

        // Handle errors
        if (e != null) {
            Log.w(TAG, "onEvent:error", e);
            return;
        }

        // Dispatch the event
        for (DocumentChange change : queryDocumentSnapshots.getDocumentChanges()) {
            // Snapshot of the changed document
            DocumentSnapshot snapshot = change.getDocument();

            switch (change.getType()) {
                case ADDED:
                    onDocumentAdded(change);
                    break;
                case MODIFIED:
                    onDocumentModified(change);
                    break;
                case REMOVED:
                    onDocumentRemoved(change);
                    break;
            }
        }
    }

    public void startListening() {

        if (registration == null && query != null) {
            this.registration = query.addSnapshotListener(this);
        }
    }

    public void stopListening() {

        if (registration != null) {
            registration.remove();
            registration = null;
        }

        documentSnapshots.clear();
    }

    protected DocumentSnapshot getSnapshot(int index) {
        return documentSnapshots.get(index);
    }

    protected void onDocumentAdded(DocumentChange change) {
        documentSnapshots.add(change.getNewIndex(), change.getDocument());
        this.recalculateStats();
    }

    protected void onDocumentModified(DocumentChange change) {
        if (change.getOldIndex() == change.getNewIndex()) {
            // Item changed but remained in same position
            documentSnapshots.set(change.getOldIndex(), change.getDocument());
        } else {
            // Item changed and changed position
            documentSnapshots.remove(change.getOldIndex());
            documentSnapshots.add(change.getNewIndex(), change.getDocument());
        }
        this.recalculateStats();
    }

    protected void onDocumentRemoved(DocumentChange change) {
        documentSnapshots.remove(change.getOldIndex());
        this.recalculateStats();
    }
}
