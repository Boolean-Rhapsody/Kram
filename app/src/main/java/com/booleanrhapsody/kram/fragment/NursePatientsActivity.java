/*
*  NursePatientsActivity.java
*  Kram
*
*  Created by Unknown.
*  Copyright © 2018 Boolean Rhapsody. All rights reserved.
*/

package com.booleanrhapsody.kram.fragment;

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
import com.booleanrhapsody.kram.model.PatientModel;

import java.util.*;


public class NursePatientsActivity extends Fragment {
	
	public static NursePatientsActivity newInstance() {
	
		NursePatientsActivity fragment = new NursePatientsActivity();
		Bundle arguments = new Bundle();
		fragment.setArguments(arguments);
		return fragment;
	}
	
	private NursePatientsActivityBinding binding;
	protected NursePatientsActivity() {
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
	
		this.startPatientDetailsActivity();
	}
	
	public void init() {
	
		// Configure Messages component
		binding.messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
		binding.messagesRecyclerView.setAdapter(new NursePatientsActivityMessagesRecyclerViewAdapter(PatientModel.getPatientsQuery()));
	}
	
	private void startPatientDetailsActivity() {
	
		this.getActivity().startActivity(PatientDetailsActivity.newIntent(this.getContext()));
	}
}
