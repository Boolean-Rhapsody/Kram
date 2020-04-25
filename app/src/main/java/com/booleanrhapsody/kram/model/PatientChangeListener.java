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
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

public class PatientChangeListener implements EventListener<QuerySnapshot> {

    public interface OnPatientChangedListener {

        public void onPatientChanged();

    }

    private PatientChangeListener.OnPatientChangedListener listener;

    private static final String TAG = "PatientChangeListener";

    private Query query;
    private ListenerRegistration registration;

    public List<DocumentSnapshot> getDocumentSnapshots() {
        return documentSnapshots;
    }

    private ArrayList<DocumentSnapshot> documentSnapshots = new ArrayList<>();


    public int getPatientsBeforeMe() {
        return patientsBeforeMe;
    }

    public int getPatientsInProgress() {
        return patientsInProgress;
    }

    public int getMyWaitTime() {
        return myWaitTime;
    }

    private int patientsBeforeMe = 0;
    private int patientsInProgress = 0;
    private int myWaitTime = 0;

    public PatientChangeListener(Query q) {
        this.query = q;
    }

    public PatientChangeListener(Query q, PatientChangeListener.OnPatientChangedListener listener) {
        this.query = q;
        this.listener = listener;
    }

    public void recalculateStats() {

        int patientsBeforeMe = 0;
        boolean foundCurrentPatient = false;

        int patientsInProgress = 0;

        int myWaitTime = 0;

        PatientModel currentPatient = GlobalModel.getInstance().getEditingPatient();
        if (currentPatient == null) {
            Log.w(TAG, "No valid current patient found, no stats");
            return;
        }

        for(DocumentSnapshot snapshot: this.documentSnapshots) {

            PatientModel patient = snapshot.toObject(PatientModel.class);
            if (patient.getId() == null) {
                patient.setId(snapshot.getId());
            }

            Log.i("Recalc stats: patient= ", patient.toString());
            Log.i("Comparing:", currentPatient.getId() + ", " + patient.getId());

            long diffInMillies = Math.abs(new Date().getTime() - patient.getTimestamp().getTime());
            if (diffInMillies > 45*60*1000) {
                int newSeverity = patient.getSeverity() - 1;
                if (newSeverity < 1) {
                    newSeverity = 1;
                }

                patient.setSeverity(newSeverity);
            }

            if (patient.getId().equals(currentPatient.getId())) {
                foundCurrentPatient = true;
            }
            if (!foundCurrentPatient && patient.getStatus().equals(PatientModel.STATUS_WAIT)) {
                patientsBeforeMe++;
            }

            if (patient.getStatus() == PatientModel.STATUS_ASSIGNED) {
                patientsInProgress++;
            }
        }
        Log.i(TAG, "Stats summary: before me" +  String.valueOf(patientsBeforeMe) + ", " +  String.valueOf(patientsInProgress));

        myWaitTime = patientsBeforeMe * 10;

        this.patientsBeforeMe = patientsBeforeMe;
        this.patientsInProgress = patientsInProgress;
        this.myWaitTime = myWaitTime;
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

        if (this.listener != null) {
            this.listener.onPatientChanged();
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
