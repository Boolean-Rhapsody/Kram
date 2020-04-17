/*
*  NursePatientsActivity.java
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
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.activity.*;
import com.booleanrhapsody.kram.adapter.NursePatientsActivityMessagesRecyclerViewAdapter;
import com.booleanrhapsody.kram.databinding.NursePatientsActivityBinding;
import com.booleanrhapsody.kram.model.GlobalModel;
import com.booleanrhapsody.kram.model.PatientModel;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.*;


public class NursePatientsActivity extends Fragment
		implements NursePatientsActivityMessagesRecyclerViewAdapter.OnPatientSelectedListener {

	private NursePatientsActivityMessagesRecyclerViewAdapter mAdapter;

	public static NursePatientsActivity newInstance() {
	
		NursePatientsActivity fragment = new NursePatientsActivity();
		Bundle arguments = new Bundle();
		fragment.setArguments(arguments);
		return fragment;
	}
	
	private NursePatientsActivityBinding binding;
	public NursePatientsActivity() {
		super();
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		binding = DataBindingUtil.inflate(inflater, R.layout.nurse_patients_activity, container, false);
		return binding.getRoot();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	
		inflater.inflate(R.menu.nurse_patients_activity_menu, menu);
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

		GlobalModel.getInstance().setEditingPatient(null);
		this.startPatientDetailsActivity();
	}
	
	public void init() {

		this.mAdapter = new NursePatientsActivityMessagesRecyclerViewAdapter(PatientModel.getPatientsQuery(), this);

		// Configure Messages component
		binding.messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
		binding.messagesRecyclerView.setAdapter(this.mAdapter);
	}
	
	private void startPatientDetailsActivity() {
	
		this.getActivity().startActivity(PatientDetailsActivity.newIntent(this.getContext()));
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
