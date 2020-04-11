/*
*  DoctorsPatientsActivityMessagesRecyclerViewAdapter.java
*  Kram
*
*  Created by Unknown.
*  Copyright © 2018 Boolean Rhapsody. All rights reserved.
*/

package com.booleanrhapsody.kram.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.databinding.MessageThirteenViewHolderBinding;
import com.booleanrhapsody.kram.model.PatientModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;


public class DoctorsPatientsActivityMessagesRecyclerViewAdapter extends
		FirestoreAdapter<DoctorsPatientsActivityMessagesRecyclerViewAdapter.MessageViewHolder>  {

	public interface OnPatientSelectedListener {

		void onPatientSelected(DocumentSnapshot restaurant);

	}

	private DoctorsPatientsActivityMessagesRecyclerViewAdapter.OnPatientSelectedListener mListener;

	public DoctorsPatientsActivityMessagesRecyclerViewAdapter(Query query) {
		super(query);
	}

	public DoctorsPatientsActivityMessagesRecyclerViewAdapter(Query query, DoctorsPatientsActivityMessagesRecyclerViewAdapter.OnPatientSelectedListener listener) {
		super(query);
		mListener = listener;
	}

	@NonNull
	@Override
	public DoctorsPatientsActivityMessagesRecyclerViewAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		return new DoctorsPatientsActivityMessagesRecyclerViewAdapter.MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_thirteen_view_holder, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull DoctorsPatientsActivityMessagesRecyclerViewAdapter.MessageViewHolder messageViewHolder, int position) {
		messageViewHolder.bind(getSnapshot(position), mListener);
	}


	public static class MessageViewHolder extends RecyclerView.ViewHolder {
		private MessageThirteenViewHolderBinding binding;
		public MessageViewHolder(View itemView) {
			super(itemView);
			binding = MessageThirteenViewHolderBinding.bind(itemView);
			init();
		}

		public void init() {

		}

		public void bind(final DocumentSnapshot snapshot,
						 final DoctorsPatientsActivityMessagesRecyclerViewAdapter.OnPatientSelectedListener listener) {

			PatientModel patient = snapshot.toObject(PatientModel.class);
			Resources resources = itemView.getResources();


			binding.nameTextView.setText(patient.getName());
			binding.theNeedsOfTheFewTextView.setText(String.valueOf(patient.getSeverity()));

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null) {
						listener.onPatientSelected(snapshot);
					}
				}
			});
		}
	}

}
