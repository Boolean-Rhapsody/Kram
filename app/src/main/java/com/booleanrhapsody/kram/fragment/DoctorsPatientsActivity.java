/*
*  DoctorsPatientsActivity.java
*  Kram
*
*  Created by Unknown.
*  Copyright © 2018 Boolean Rhapsody. All rights reserved.
*/

package com.booleanrhapsody.kram.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.activity.*;
import com.booleanrhapsody.kram.adapter.DoctorsPatientsActivityMessagesRecyclerViewAdapter;
import com.booleanrhapsody.kram.databinding.DoctorsPatientsActivityBinding;
import com.booleanrhapsody.kram.model.GlobalModel;
import com.booleanrhapsody.kram.model.PatientModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.*;

//implements DoctorsPatientsMessagesRecyclerViewAdapter.OnPatientSelectedListener
public class DoctorsPatientsActivity extends Fragment
		implements DoctorsPatientsActivityMessagesRecyclerViewAdapter.OnPatientSelectedListener{

	private DoctorsPatientsActivityMessagesRecyclerViewAdapter mAdapter;

	public static DoctorsPatientsActivity newInstance() {
	
		DoctorsPatientsActivity fragment = new DoctorsPatientsActivity();
		Bundle arguments = new Bundle();
		fragment.setArguments(arguments);
		return fragment;
	}
	
	private DoctorsPatientsActivityBinding binding;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		binding = DataBindingUtil.inflate(inflater, R.layout.doctors_patients_activity, container, false);
		return binding.getRoot();
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	
		super.onViewCreated(view, savedInstanceState);
		init();
	}
	
	public void init() {

		Query q = PatientModel.getPatientsCollection()
				.whereGreaterThan("status", PatientModel.STATUS_ASSIGNED)
				.orderBy("status", Query.Direction.DESCENDING)
				.orderBy("severity", Query.Direction.ASCENDING)
				.orderBy("timestamp", Query.Direction.DESCENDING)
				.limit(50);

		this.mAdapter = new DoctorsPatientsActivityMessagesRecyclerViewAdapter(PatientModel.getPatientsQuery(), this);
		// Configure Messages component
		binding.messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
		binding.messagesRecyclerView.setAdapter(this.mAdapter);
	}

	@Override
	public void onStart() {
		super.onStart();

		// Start listening for Firestore updates
		if (mAdapter != null) {
			mAdapter.startListening();
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		if (mAdapter != null) {
			mAdapter.stopListening();
		}
	}

	@Override
	public void onPatientSelected(DocumentSnapshot item) {

		PatientModel patient = item.toObject(PatientModel.class);
		patient.setId(item.getId());
		GlobalModel.getInstance().setEditingPatient(patient);

		Intent intent = PatientDetailsActivity.newIntent(this.getContext());
		intent.putExtra("id", item.getId());
		this.getActivity().startActivity(intent);

	}
}
