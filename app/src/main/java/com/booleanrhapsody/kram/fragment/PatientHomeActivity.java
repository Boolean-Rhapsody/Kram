/*
*  PatientHomeActivity.java
*  Kram
*
*  Created by Unknown.
*  Copyright © 2018 Boolean Rhapsody. All rights reserved.
*/

package com.booleanrhapsody.kram.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.activity.*;
import com.booleanrhapsody.kram.databinding.PatientHomeActivityBinding;
import com.booleanrhapsody.kram.model.GlobalModel;
import com.booleanrhapsody.kram.model.PatientChangeListener;
import com.booleanrhapsody.kram.model.PatientModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.*;


public class PatientHomeActivity extends Fragment implements  PatientChangeListener.OnPatientChangedListener {

	private static final String TAG = "PatientHomeActivity";

	private boolean active = true;

	public static PatientHomeActivity newInstance() {
	
		PatientHomeActivity fragment = new PatientHomeActivity();
		Bundle arguments = new Bundle();
		fragment.setArguments(arguments);
		return fragment;
	}
	
	private PatientHomeActivityBinding binding;
	public PatientHomeActivity() {
		super();
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		binding = DataBindingUtil.inflate(inflater, R.layout.patient_home_activity, container, false);
		return binding.getRoot();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	
		inflater.inflate(R.menu.patient_home_activity_menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
	
		switch (menuItem.getItemId()) {
			case R.id.left_item_menu_item: 
				this.onLeftItemPressed();
				return true;
			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	
		super.onViewCreated(view, savedInstanceState);
		init();
	}
	
	public void onLeftItemPressed() {
	
		this.startWelcomeActivity();
	}

	public static final String DATE_FORMAT_1 = "hh:mm a";

	public void init() {

		PatientModel patient = GlobalModel.getInstance().getEditingPatient();

		Log.i(TAG, "Patient Info= " + patient.toString());
		binding.peterTextView.setText(patient.getName());

		binding.textPatientInfoDetails.setText(String.valueOf(patient.getSeverity()));

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
		binding.waitingSince1012TextView.setText("Waiting since " + dateFormat.format(patient.getTimestamp()));

		active = true;
		if (patient.getStatus().equals(PatientModel.STATUS_COMPLETED)) {
			active = false;
		}
	}
	
	private void startWelcomeActivity() {

		GlobalModel.getInstance().logoutUser(this.getActivity());
	}

	@Override
	public void onStart() {
		super.onStart();

		if (active) {

			Query q = PatientModel.getPatientsCollection()
					.whereGreaterThan("status", PatientModel.STATUS_COMPLETED)
					.orderBy("status", Query.Direction.DESCENDING)
					.orderBy("severity", Query.Direction.ASCENDING)
					.orderBy("timestamp", Query.Direction.DESCENDING)
					.limit(50);
			// Start listening for Firestore updates
			GlobalModel.getInstance().startPatientListener(q, this);

		}

	}

	@Override
	public void onStop() {
		super.onStop();

		if (active) {
			GlobalModel.getInstance().stopPatientListener();
		}
	}

	@Override
	public void onPatientChanged() {

		Log.i(TAG, "onPatientChanged");

		String queueSize = String.valueOf(GlobalModel.getInstance().getPatientChangeListener().getPatientsBeforeMe());
		binding.textPatientInfoQueueSize.setText(queueSize);

		String myWaitTime = String.valueOf(GlobalModel.getInstance().getPatientChangeListener().getMyWaitTime());
		binding.textPatientInfoExpectedWait.setText(myWaitTime);

		//String otherDetails = String.valueOf(GlobalModel.getInstance().getPatientChangeListener().getPatientsInProgress());
		//binding.textPatientInfoDetails.setText(otherDetails);

		Snackbar.make(this.getActivity().findViewById(android.R.id.content), "Some patient was updated",
				Snackbar.LENGTH_SHORT).show();
	}
}
