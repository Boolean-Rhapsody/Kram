/*
*  NurseDoctorsActivity.java
*  Kram
*
*  Created by Unknown.
*  Copyright © 2018 Boolean Rhapsody. All rights reserved.
*/

package com.booleanrhapsody.kram.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.booleanrhapsody.kram.adapter.NurseDoctorsActivityMessagesRecyclerViewAdapter;
import com.booleanrhapsody.kram.databinding.NurseDoctorsActivityBinding;
import com.booleanrhapsody.kram.model.DoctorModel;
import com.booleanrhapsody.kram.model.GlobalModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.*;


public class NurseDoctorsActivity extends Fragment {

	private NurseDoctorsActivityMessagesRecyclerViewAdapter mAdapter;

	public static NurseDoctorsActivity newInstance() {
	
		NurseDoctorsActivity fragment = new NurseDoctorsActivity();
		Bundle arguments = new Bundle();
		fragment.setArguments(arguments);
		return fragment;
	}
	
	private NurseDoctorsActivityBinding binding;
	public NurseDoctorsActivity() {
		super();
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		binding = DataBindingUtil.inflate(inflater, R.layout.nurse_doctors_activity, container, false);
		return binding.getRoot();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	
		inflater.inflate(R.menu.nurse_doctors_activity_menu, menu);
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

		this.mAdapter = new NurseDoctorsActivityMessagesRecyclerViewAdapter(DoctorModel.getDoctorsQuery());
		// Configure Messages component
		binding.messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
		binding.messagesRecyclerView.setAdapter(this.mAdapter);
	}
	
	private void startWelcomeActivity() {

		GlobalModel.getInstance().logoutUser(this.getActivity());
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
}
