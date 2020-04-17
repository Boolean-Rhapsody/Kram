/*
*  DoctorsSettingsActivity.java
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
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.activity.*;
import com.booleanrhapsody.kram.databinding.DoctorsSettingsActivityBinding;
import com.booleanrhapsody.kram.model.GlobalModel;

import java.util.*;


public class DoctorsSettingsActivity extends Fragment {
	
	public static DoctorsSettingsActivity newInstance() {
	
		DoctorsSettingsActivity fragment = new DoctorsSettingsActivity();
		Bundle arguments = new Bundle();
		fragment.setArguments(arguments);
		return fragment;
	}
	
	private DoctorsSettingsActivityBinding binding;
	public DoctorsSettingsActivity() {
		super();
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		binding = DataBindingUtil.inflate(inflater, R.layout.doctors_settings_activity, container, false);
		return binding.getRoot();
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	
		inflater.inflate(R.menu.doctors_settings_activity_menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
	
		switch (menuItem.getItemId()) {
			case R.id.group_menu_item: 
				this.onGroupPressed();
				return true;
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
	
	public void onSlideTwoValueChanged() {
	
	}
	
	public void onSlideValueChanged() {
	
	}
	
	public void onGroupPressed() {
	
	}
	
	public void onLeftItemPressed() {

		GlobalModel.getInstance().logoutUser(this.getActivity());
	}
	
	public void init() {
	
		// Configure Slide component
		binding.slideSwitch.setOnClickListener((view) -> {
	this.onSlideTwoValueChanged();
});
		
		// Configure Slide component
		binding.slideTwoSwitch.setOnClickListener((view) -> {
	this.onSlideValueChanged();
});
	}
	
	private void startWelcomeActivity() {

		GlobalModel.getInstance().logoutUser(this.getActivity());
	}
}
