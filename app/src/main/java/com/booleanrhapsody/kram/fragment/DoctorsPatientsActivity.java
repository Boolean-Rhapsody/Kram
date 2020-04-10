/*
*  DoctorsPatientsActivity.java
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.activity.*;
import com.booleanrhapsody.kram.adapter.DoctorsPatientsActivityMessagesRecyclerViewAdapter;
import com.booleanrhapsody.kram.databinding.DoctorsPatientsActivityBinding;
import java.util.*;


public class DoctorsPatientsActivity extends Fragment {
	
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
	
		// Configure Messages component
		binding.messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
		binding.messagesRecyclerView.setAdapter(new DoctorsPatientsActivityMessagesRecyclerViewAdapter());
	}
}
