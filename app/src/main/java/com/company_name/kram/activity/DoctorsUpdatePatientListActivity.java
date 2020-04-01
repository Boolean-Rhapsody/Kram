/*
*  DoctorsUpdatePatientListActivity.java
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
import com.company_name.kram.R;


public class DoctorsUpdatePatientListActivity extends AppCompatActivity {
	
	public static Intent newIntent(Context context) {
	
		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, DoctorsUpdatePatientListActivity.class);
	}
	
	private Toolbar toolbar;
	private Button submitButton;
	private Button submitTwoButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.doctors_update_patient_list_activity);
		this.init();
	}
	
	private void init() {
	
		// Configure Navigation Bar #1 component
		toolbar = this.findViewById(R.id.toolbar);
		
		// Configure Submit component
		submitButton = this.findViewById(R.id.submit_button);
		submitButton.setOnClickListener((view) -> {
	this.onSubmitTwoPressed();
});
		
		// Configure Submit component
		submitTwoButton = this.findViewById(R.id.submit_two_button);
		submitTwoButton.setOnClickListener((view) -> {
	this.onSubmitPressed();
});
		
		this.setupToolbar();
	}
	
	public void setupToolbar() {
	
		setSupportActionBar(toolbar);
		
		// Additional Toolbar setup code can go here.
	}
	
	public void onSubmitTwoPressed() {
	
	}
	
	public void onSubmitPressed() {
	
	}
}
