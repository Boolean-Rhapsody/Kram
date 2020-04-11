/*
*  PatientDetailsActivity.java
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
import com.booleanrhapsody.kram.databinding.PatientDetailsActivityBinding;
import com.booleanrhapsody.kram.model.GlobalModel;
import com.booleanrhapsody.kram.model.PatientModel;

import io.supernova.uitoolkit.drawable.LinearGradientDrawable;


public class PatientDetailsActivity extends AppCompatActivity {
	
	public static Intent newIntent(Context context) {
	
		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, PatientDetailsActivity.class);
	}
	
	private PatientDetailsActivityBinding binding;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.patient_details_activity);
		this.init();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		getMenuInflater().inflate(R.menu.patient_details_activity_menu, menu);
		return true;
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
	
	private void init() {
	
		// Configure Navigation Bar #2 component
		binding.toolbar.setBackground(new LinearGradientDrawable.Builder(this, new PointF(-0.01f, 0.51f), new PointF(1.01f, 0.49f)).addStop(0f, Color.argb(255, 247, 132, 98)).addStop(1f, Color.argb(255, 138, 27, 139)).build());
		binding.patientDoctorName.setText("Not assigned");

		this.setupToolbar();

		String id = getIntent().getStringExtra("id");
		PatientModel p = GlobalModel.getInstance().getEditingPatient();

		if (p!= null) {
			binding.patientNameEdit.setText(p.getName());
			binding.patientSeverityEdit.setText(String.valueOf(p.getSeverity()));
			binding.patientDoctorName.setText(p.getDoctor());
		}

	}
	
	public void setupToolbar() {
	
		setSupportActionBar(binding.toolbar);
		
		// Additional Toolbar setup code can go here.
	}
	
	public void onLeftItemPressed() {

		String id = getIntent().getStringExtra("id");
		if (id == null) {

			PatientModel p = new PatientModel();
			p.setName(binding.patientNameEdit.getText().toString());
			p.setSeverity(Integer.valueOf(binding.patientSeverityEdit.getText().toString()));
			PatientModel.add(p);
		}
		else {
			PatientModel p = GlobalModel.getInstance().getEditingPatient();
			p.setName(binding.patientNameEdit.getText().toString());
			p.setSeverity(Integer.valueOf(binding.patientSeverityEdit.getText().toString()));
			PatientModel.save(p);
		}
		this.finish();
	}

}
