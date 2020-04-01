/*
*  PatientProfileActivity.java
*  Kram
*
*  Created by [Author].
*  Copyright © 2018 [Company]. All rights reserved.
*/

package com.company_name.kram.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.company_name.kram.R;


public class PatientProfileActivity extends AppCompatActivity {
	
	public static Intent newIntent(Context context) {
	
		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, PatientProfileActivity.class);
	}
	
	private Toolbar toolbar;
	private Button doctorButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.patient_profile_activity);
		this.init();
	}
	
	private void init() {
	
		// Configure Navigation Bar #1 component
		toolbar = this.findViewById(R.id.toolbar);
		
		// Configure Doctor component
		doctorButton = this.findViewById(R.id.doctor_button);
		doctorButton.setOnClickListener((view) -> {
	this.onDoctorPressed();
});
		
		this.setupToolbar();
	}
	
	public void setupToolbar() {
	
		setSupportActionBar(toolbar);
		
		// Additional Toolbar setup code can go here.
	}
	
	public void onDoctorPressed() {
	
	}
}
