/*
*  LoginActivity.java
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
import com.booleanrhapsody.kram.databinding.LoginActivityBinding;
import com.booleanrhapsody.kram.model.GlobalModel;
import com.booleanrhapsody.kram.model.PatientModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import io.supernova.uitoolkit.drawable.LinearGradientDrawable;


public class LoginActivity extends AppCompatActivity {

	private static final String TAG = "LoginActivity";

	public static Intent newIntent(Context context) {
	
		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, LoginActivity.class);
	}
	
	private LoginActivityBinding binding;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
		this.init();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		getMenuInflater().inflate(R.menu.login_activity_menu, menu);
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
		
		// Configure Login component
		binding.loginConstraintLayout.setBackground(new LinearGradientDrawable.Builder(this, new PointF(0.31f, 1.1f), new PointF(0.69f, -0.1f)).addStop(0f, Color.argb(255, 247, 132, 98)).addStop(1f, Color.argb(255, 138, 27, 139)).build());
		
		// Configure Login component
		binding.loginButton.setOnClickListener((view) -> {
	this.onLoginPressed();
});
		
		this.setupToolbar();
	}
	
	public void setupToolbar() {
	
		setSupportActionBar(binding.toolbar);
		
		// Additional Toolbar setup code can go here.
	}
	
	public void onLoginPressed() {
	
		this.startPatientHomeActivity();
	}

	private void startPatientHomeActivity() {

		String id = binding.yourPatientIdEditText.getText().toString();
		PatientModel.getPatientsCollection().document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
			@Override
			public void onComplete(@NonNull Task<DocumentSnapshot> task) {
				if (task.isSuccessful()) {
					DocumentSnapshot document = task.getResult();
					if (document.exists()) {
						Log.d(TAG, "DocumentSnapshot data: " + document.getData());

						PatientModel patient = document.toObject(PatientModel.class);
						patient.setId(document.getId());
						GlobalModel.getInstance().setEditingPatient(patient);

						LoginActivity.this.startActivity(TabGroupTwoActivity.newIntent(LoginActivity.this));

					} else {
						Log.d(TAG, "No such document");
						Snackbar.make(findViewById(android.R.id.content), "No such patient ID",
								Snackbar.LENGTH_SHORT).show();
					}
				} else {
					Log.d(TAG, "get failed with ", task.getException());
					Snackbar.make(findViewById(android.R.id.content), "Failed to validate patient ID",
							Snackbar.LENGTH_SHORT).show();
				}
			}
		});

	}

	public void onGroupPressed() {
	
	}
	
	private void startTabGroupTwoActivity() {
	
		//this.startActivity(PatientHomeActivity.newIntent(this));
	}
}
