/*
*  TabGroupThreeActivity.java
*  Kram
*
*  Created by Unknown.
*  Copyright © 2018 Boolean Rhapsody. All rights reserved.
*/

package com.booleanrhapsody.kram.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.adapter.TabGroupThreeActivityPagerAdapter;
import com.booleanrhapsody.kram.databinding.TabGroupThreeActivityBinding;
import com.booleanrhapsody.kram.fragment.DoctorsPatientsActivity;
import com.booleanrhapsody.kram.fragment.DoctorsSettingsActivity;
import io.supernova.uitoolkit.drawable.LinearGradientDrawable;
import java.util.Arrays;
import java.util.List;


public class TabGroupThreeActivity extends AppCompatActivity {
	
	public static Intent newIntent(Context context) {
	
		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, TabGroupThreeActivity.class);
	}
	
	private TabGroupThreeActivityBinding binding;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.tab_group_three_activity);
		init();
	}
	
	public void init() {
	
		
		// Configure Navigation Bar #2 component
		binding.toolbar.setBackground(new LinearGradientDrawable.Builder(this, new PointF(-0.01f, 0.51f), new PointF(1.01f, 0.49f)).addStop(0f, Color.argb(255, 247, 132, 98)).addStop(1f, Color.argb(255, 138, 27, 139)).build());
		// Configure View Pager Adapter
		List<Fragment> fragments = Arrays.asList(DoctorsPatientsActivity.newInstance(), DoctorsSettingsActivity.newInstance());
		binding.viewPager.setAdapter(new TabGroupThreeActivityPagerAdapter(getSupportFragmentManager(), fragments));
		binding.bottomNavigationBar.setOnNavigationItemSelectedListener((menuItem) -> {
	onTabSelected(menuItem);
	return true;
});
		
		setupToolbar();
	}
	
	private void setupToolbar() {
	
		setSupportActionBar(binding.toolbar);
		
		// Additional Toolbar setup code can go here.
		this.getSupportActionBar().setTitle("Home");
	}
	
	public void onTabSelected(MenuItem menuItem) {
	
		switch (menuItem.getItemId()) {
			case R.id.doctors_patients_activity_menu_item: 
				binding.viewPager.setCurrentItem(0, true);
				break;
			case R.id.doctors_settings_activity_menu_item: 
				binding.viewPager.setCurrentItem(1, true);
				break;
		}
	}
}
