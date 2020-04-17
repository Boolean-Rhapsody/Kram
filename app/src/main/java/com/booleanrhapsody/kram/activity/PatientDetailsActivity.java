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
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.databinding.PatientDetailsActivityBinding;
import com.booleanrhapsody.kram.model.GlobalModel;
import com.booleanrhapsody.kram.model.PatientModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Date;

import io.supernova.uitoolkit.drawable.LinearGradientDrawable;


public class PatientDetailsActivity extends AppCompatActivity {

	private static final String TAG = "PatientDetailsActivity";

	public static Intent newIntent(Context context) {
	
		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, PatientDetailsActivity.class);
	}
	
	private PatientDetailsActivityBinding binding;

	private PatientModel patientModel;

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

			binding.textViewPatientId.setText(id);
		}

		String userCategory = GlobalModel.getInstance().getUserCategory();
		if (userCategory == GlobalModel.CAT_DOCTOR) {
			binding.buttonPatientAssign.setVisibility(View.VISIBLE);
			binding.buttonPatientMarkDone.setVisibility(View.VISIBLE);
		}
		else if (userCategory == GlobalModel.CAT_NURSE) {
			binding.buttonPatientAssign.setVisibility(View.INVISIBLE);
			binding.buttonPatientMarkDone.setVisibility(View.INVISIBLE);
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
			p.setHospital(GlobalModel.getInstance().getUserHospital());
			p.setStatus(PatientModel.STATUS_WAIT);
			PatientModel.add(p).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
				@Override
				public void onComplete(@NonNull Task<DocumentReference> task) {
					if (task.isSuccessful()) {
						DocumentReference document = task.getResult();

						p.setId(document.getId());
						PatientModel.save(p);
						PatientDetailsActivity.this.finish();

					} else {
						Log.d(TAG, "get failed with ", task.getException());
						Snackbar.make(findViewById(android.R.id.content), "Failed to save patient",
								Snackbar.LENGTH_SHORT).show();
					}
				}
			});
		}
		else {
			PatientModel p = GlobalModel.getInstance().getEditingPatient();
			p.setName(binding.patientNameEdit.getText().toString());
			p.setSeverity(Integer.valueOf(binding.patientSeverityEdit.getText().toString()));
			PatientModel.save(p);
			this.finish();
		}

	}

	public void onClickAssignPatient (View v) {

		Snackbar.make(findViewById(android.R.id.content), "Assigned patient",
				Snackbar.LENGTH_SHORT).show();

		PatientModel p = GlobalModel.getInstance().getEditingPatient();
		p.setDoctor(GlobalModel.getInstance().getCurrentUser().getEmail());
		p.setStartTime(new Date());
		p.setStatus(PatientModel.STATUS_ASSIGNED);
		p.setHospital(GlobalModel.getInstance().getUserHospital());
		PatientModel.save(p);

		this.finish();
	}

	public void onClickDonePatient (View v) {

		Snackbar.make(findViewById(android.R.id.content), "Marked done patient",
				Snackbar.LENGTH_SHORT).show();

		PatientModel p = GlobalModel.getInstance().getEditingPatient();
		p.setDoctor(GlobalModel.getInstance().getCurrentUser().getEmail());
		p.setStopTime(new Date());
		p.setStatus(PatientModel.STATUS_COMPLETED);
		p.setHospital(GlobalModel.getInstance().getUserHospital());
		PatientModel.save(p);

		this.finish();
	}

	public void onClickSavePatient (View v) {
		onLeftItemPressed();
	}
}
