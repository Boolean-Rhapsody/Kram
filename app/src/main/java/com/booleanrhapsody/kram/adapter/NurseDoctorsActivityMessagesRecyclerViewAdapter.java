/*
*  NurseDoctorsActivityMessagesRecyclerViewAdapter.java
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
import com.booleanrhapsody.kram.databinding.MessageSevenViewHolderBinding;
import com.booleanrhapsody.kram.model.DoctorModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;


public class NurseDoctorsActivityMessagesRecyclerViewAdapter extends
		FirestoreAdapter<NurseDoctorsActivityMessagesRecyclerViewAdapter.MessageSevenViewHolder>  {


	public interface OnDoctorSelectedListener {

		void onDoctorSelected(DocumentSnapshot restaurant);

	}

	private NurseDoctorsActivityMessagesRecyclerViewAdapter.OnDoctorSelectedListener mListener;

	public NurseDoctorsActivityMessagesRecyclerViewAdapter(Query query) {
		super(query);
	}

	public NurseDoctorsActivityMessagesRecyclerViewAdapter(Query query, NurseDoctorsActivityMessagesRecyclerViewAdapter.OnDoctorSelectedListener listener) {
		super(query);
		mListener = listener;
	}

	@NonNull
	@Override
	public MessageSevenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		return new MessageSevenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_seven_view_holder, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull MessageSevenViewHolder messageViewHolder, int position) {
		messageViewHolder.bind(getSnapshot(position), mListener);
	}


	public static class MessageSevenViewHolder extends RecyclerView.ViewHolder {
		private MessageSevenViewHolderBinding binding;
		public MessageSevenViewHolder(View itemView) {
			super(itemView);
			binding = MessageSevenViewHolderBinding.bind(itemView);
			init();
		}

		public void init() {

		}

		public void bind(final DocumentSnapshot snapshot,
						 final NurseDoctorsActivityMessagesRecyclerViewAdapter.OnDoctorSelectedListener listener) {

			DoctorModel patient = snapshot.toObject(DoctorModel.class);
			Resources resources = itemView.getResources();


			binding.nameTextView.setText(patient.getName());
			binding.theNeedsOfTheFewTextView.setText(String.valueOf(patient.getSpecialty()));

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (listener != null) {
						listener.onDoctorSelected(snapshot);
					}
				}
			});
		}
	}
}
