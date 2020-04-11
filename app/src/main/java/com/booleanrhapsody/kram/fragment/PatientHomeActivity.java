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
import com.google.firebase.firestore.Query;

import java.util.*;


public class PatientHomeActivity extends Fragment implements  PatientChangeListener.OnPatientChangedListener {

	private static final String TAG = "PatientHomeActivity";

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
	
	public void init() {
	
	}
	
	private void startWelcomeActivity() {

		GlobalModel.getInstance().logoutUser(this.getActivity());
	}

	@Override
	public void onStart() {
		super.onStart();

		Query q = PatientModel.getPatientsCollection()
				.orderBy("timestamp", Query.Direction.DESCENDING)
				.limit(50);
		// Start listening for Firestore updates
		GlobalModel.getInstance().startPatientListener(q, this);
	}

	@Override
	public void onStop() {
		super.onStop();

		GlobalModel.getInstance().stopPatientListener();
	}

	@Override
	public void onPatientChanged() {

		Log.i(TAG, "onPatientChanged");
		Snackbar.make(this.getActivity().findViewById(android.R.id.content), "Some patient was updated",
				Snackbar.LENGTH_SHORT).show();
	}
}
