/*
*  WelcomeActivity.java
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
import android.view.View;
import android.widget.Toast;

import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.databinding.WelcomeActivityBinding;
import com.booleanrhapsody.kram.model.GlobalModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import io.supernova.uitoolkit.drawable.LinearGradientDrawable;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

	int RC_SIGN_IN = 100;

	private FirebaseAuth mAuth;

	public static Intent newIntent(Context context) {
	
		// Fill the created intent with the data you want to be passed to this Activity when it's opened.
		return new Intent(context, WelcomeActivity.class);
	}
	
	private WelcomeActivityBinding binding;

	@Override
	public void onStart() {
		super.onStart();
		// Check if user is signed in (non-null) and update UI accordingly.
		FirebaseUser currentUser = mAuth.getCurrentUser();
		FirebaseFirestore db = FirebaseFirestore.getInstance();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);

		// Initialize Firebase Auth
		mAuth = FirebaseAuth.getInstance();

		binding = DataBindingUtil.setContentView(this, R.layout.welcome_activity);
		this.init();

	}
	
	private void init() {
	
		// Configure Welcome component
		binding.welcomeConstraintLayout.setBackground(new LinearGradientDrawable.Builder(this, new PointF(0.31f, 1.1f), new PointF(0.69f, -0.1f)).addStop(0f, Color.argb(255, 247, 132, 98)).addStop(1f, Color.argb(255, 138, 27, 139)).build());
		
		// Configure NurseLoginButton component
		binding.nurseloginbuttonButton.setOnClickListener((view) -> {
	this.onNurseLoginButtonPressed();
});
		
		// Configure DoctorLoginButton component
		binding.doctorloginbuttonButton.setOnClickListener((view) -> {
	this.onDoctorLoginButtonPressed();
});
		
		// Configure PatientLoginButton component
		binding.patientloginbuttonButton.setOnClickListener((view) -> {
	this.onPatientLoginButtonPressed();
});
	}
	
	public void onNurseLoginButtonPressed() {

		GlobalModel.getInstance().setUserCategory(GlobalModel.CAT_NURSE);
		this.startFirebaseLoginActivity();
	}
	
	public void onDoctorLoginButtonPressed() {

		GlobalModel.getInstance().setUserCategory(GlobalModel.CAT_DOCTOR);
		this.startFirebaseLoginActivity();
	}
	
	public void onPatientLoginButtonPressed() {

		GlobalModel.getInstance().setUserCategory(GlobalModel.CAT_PATIENT);
		this.startFirebaseAnonymousLoginActivity();
	}
	
	private void startPatientLoginActivity() {
	
		this.startActivity(LoginActivity.newIntent(this));
	}

	private void startSignupActivity() {
	
		this.startActivity(SignupActivity.newIntent(this));
	}

	private void startFirebaseAnonymousLoginActivity() {

		//this.startLoginActivity("Patient");

		mAuth.signInAnonymously()
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							//Log.d(TAG, "signInAnonymously:success");
							FirebaseUser user = mAuth.getCurrentUser();
							WelcomeActivity.this.startPatientLoginActivity();

						} else {
							// If sign in fails, display a message to the user.
							//Log.w(TAG, "signInAnonymously:failure", task.getException());
							Toast.makeText(WelcomeActivity.this, "Anon Authentication failed.",
									Toast.LENGTH_SHORT).show();
						}

						// ...
					}
				});
	}

	private void startFirebaseLoginActivity() {

		// Choose authentication providers
		List<AuthUI.IdpConfig> providers = Arrays.asList(
				new AuthUI.IdpConfig.EmailBuilder().build());

		// Create and launch sign-in intent
		startActivityForResult(
				AuthUI.getInstance()
						.createSignInIntentBuilder()
						.setAvailableProviders(providers)
						.build(),
				RC_SIGN_IN);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RC_SIGN_IN) {
			IdpResponse response = IdpResponse.fromResultIntent(data);

			if (resultCode == RESULT_OK) {

				// Successfully signed in
				//FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
				this.startSignupActivity();

				// ...
			} else {
				// Sign in failed. If response is null the user canceled the
				// sign-in flow using the back button. Otherwise check
				// response.getError().getErrorCode() and handle the error.
				// ...
				//this.startActivity(LoginHomeActivity.newIntent(this));
				Toast.makeText(WelcomeActivity.this, "Authentication failed.",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
