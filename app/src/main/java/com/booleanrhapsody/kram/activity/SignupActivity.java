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
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.databinding.SignupActivityBinding;
import com.booleanrhapsody.kram.model.DoctorModel;
import com.booleanrhapsody.kram.model.GlobalModel;
import com.booleanrhapsody.kram.model.NurseModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import io.supernova.uitoolkit.drawable.LinearGradientDrawable;


public class SignupActivity extends AppCompatActivity {

	private static final String TAG = "SignupActivity";

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

		//binding.toolbar.setVisibility(View.INVISIBLE);

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

		String userCategory = GlobalModel.getInstance().getUserCategory();
		this.switchToCategory(userCategory);

		binding.hospitalNameEditText.setText(GlobalModel.getInstance().getUserHospital());

		this.setupToolbar();
	}
	
	public void setupToolbar() {
	
		setSupportActionBar(binding.toolbar);
		
		// Additional Toolbar setup code can go here.
		this.getSupportActionBar().setTitle("Signup");
	}
	
	public void onSignUpPressed() {

		this.startTabGroupOneActivity();
	}

	private void switchToCategory(String category) {

		if (category == GlobalModel.CAT_DOCTOR) {
			GlobalModel.getInstance().setUserCategory(GlobalModel.CAT_DOCTOR);
			binding.switchSwitch.setChecked(true);
			binding.switchTwoSwitch.setChecked(false);
			binding.specialityEditText.setVisibility(View.VISIBLE);
		}
		else {
			GlobalModel.getInstance().setUserCategory(GlobalModel.CAT_NURSE);
			binding.switchSwitch.setChecked(false);
			binding.switchTwoSwitch.setChecked(true);
			binding.specialityEditText.setVisibility(View.INVISIBLE);
		}
	}

	public void onSwitchValueChanged() {

		this.switchToCategory(GlobalModel.CAT_DOCTOR);

		Toast.makeText(SignupActivity.this, "Changed doc = yes.",
				Toast.LENGTH_SHORT).show();
	}
	
	public void onSwitchTwoValueChanged() {

		this.switchToCategory(GlobalModel.CAT_NURSE);

		Toast.makeText(SignupActivity.this, "Changed nurse = yes.",
				Toast.LENGTH_SHORT).show();


	}
	
	public void onGroupPressed() {
	
	}

	private void startNurseHomeActivity() {

		this.startActivity(TabGroupOneActivity.newIntent(this));
	}

	private void startDoctorHomeActivity() {

		this.startActivity(TabGroupThreeActivity.newIntent(this));
	}

	private void saveNurse() {

		String hospital = binding.hospitalNameEditText.getText().toString();

		FirebaseUser currentUser= GlobalModel.getInstance().getCurrentUser();
		NurseModel.getNursesCollection().document(currentUser.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DocumentSnapshot> task) {
				if (task.isSuccessful()) {
					DocumentSnapshot document = task.getResult();
					NurseModel doc;
					if (document.exists()) {
						Log.d(TAG, "DocumentSnapshot data: " + document.getData());
						doc = document.toObject(NurseModel.class);
					} else {
						Log.d(TAG, "No such document");
						doc = new NurseModel();
						doc.setName(currentUser.getDisplayName());
					}

					doc.setId(currentUser.getEmail());
					doc.setHospital(hospital);
					NurseModel.save(doc);

					GlobalModel.getInstance().setEditingNurse(doc);

				} else {
					Log.d(TAG, "get failed with ", task.getException());
				}
			}
		});
	}

	private void saveDoctor() {

		String specialty = binding.specialityEditText.getText().toString();
		String hospital = binding.hospitalNameEditText.getText().toString();

		FirebaseUser currentUser= GlobalModel.getInstance().getCurrentUser();
		DoctorModel.getDoctorCollection().document(currentUser.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DocumentSnapshot> task) {
				if (task.isSuccessful()) {
					DocumentSnapshot document = task.getResult();
					DoctorModel doc;
					if (document.exists()) {
						Log.d(TAG, "DocumentSnapshot data: " + document.getData());
						doc = document.toObject(DoctorModel.class);
					} else {
						Log.d(TAG, "No such document");
						doc = new DoctorModel();
						doc.setName(currentUser.getDisplayName());
					}

					doc.setId(currentUser.getEmail());
					doc.setSpecialty(specialty);
					doc.setHospital(hospital);
					DoctorModel.save(doc);

					GlobalModel.getInstance().setEditingDoctor(doc);

				} else {
					Log.d(TAG, "get failed with ", task.getException());
				}
			}
		});
	}

	private void startTabGroupOneActivity() {

		String userCategory = GlobalModel.getInstance().getUserCategory();

		String hospital = binding.hospitalNameEditText.getText().toString();
		GlobalModel.getInstance().setUserHospital(hospital);

		if (userCategory == GlobalModel.CAT_NURSE) {
			this.saveNurse();
			this.startNurseHomeActivity();
		}
		else if (userCategory == GlobalModel.CAT_DOCTOR) {
			this.saveDoctor();
			this.startDoctorHomeActivity();
		}
	}
}
