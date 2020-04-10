/*
*  SignupActivity.java
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.databinding.SignupActivityBinding;
import io.supernova.uitoolkit.drawable.LinearGradientDrawable;


public class SignupActivity extends AppCompatActivity {
	
	public static Intent newIntent(Context context) {
	
		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, SignupActivity.class);
	}
	
	private SignupActivityBinding binding;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.signup_activity);
		this.init();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		getMenuInflater().inflate(R.menu.signup_activity_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
	
		switch (menuItem.getItemId()) {
			case R.id.group_menu_item: 
				this.onGroupPressed();
				return true;
			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}
	
	private void init() {
	
		// Configure Navigation Bar #2 component
		binding.toolbar.setBackground(new LinearGradientDrawable.Builder(this, new PointF(-0.01f, 0.51f), new PointF(1.01f, 0.49f)).addStop(0f, Color.argb(255, 247, 132, 98)).addStop(1f, Color.argb(255, 138, 27, 139)).build());
		
		// Configure Signup component
		binding.signupConstraintLayout.setBackground(new LinearGradientDrawable.Builder(this, new PointF(0.31f, 1.1f), new PointF(0.69f, -0.1f)).addStop(0f, Color.argb(255, 247, 132, 98)).addStop(1f, Color.argb(255, 138, 27, 139)).build());
		
		// Configure Sign Up component
		binding.signUpButton.setOnClickListener((view) -> {
	this.onSignUpPressed();
});
		
		// Configure switch component
		binding.switchSwitch.setOnClickListener((view) -> {
	this.onSwitchValueChanged();
});
		
		// Configure switch component
		binding.switchTwoSwitch.setOnClickListener((view) -> {
	this.onSwitchTwoValueChanged();
});
		
		this.setupToolbar();
	}
	
	public void setupToolbar() {
	
		setSupportActionBar(binding.toolbar);
		
		// Additional Toolbar setup code can go here.
	}
	
	public void onSignUpPressed() {
	
		//this.startNursePatientsActivity();
	}
	
	public void onSwitchValueChanged() {
	
	}
	
	public void onSwitchTwoValueChanged() {
	
	}
	
	public void onGroupPressed() {
	
	}
	
	private void startTabGroupOneActivity() {
	
		//this.startActivity(NursePatientsActivity.newIntent(this));
	}
}
