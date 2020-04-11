/*
*  NursePatientsActivityMessagesRecyclerViewAdapter.java
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
import com.booleanrhapsody.kram.databinding.MessageViewHolderBinding;
import com.booleanrhapsody.kram.model.PatientModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.Arrays;
import java.util.List;

//public abstract class FirestoreAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>
//public class RestaurantAdapter extends FirestoreAdapter<RestaurantAdapter.ViewHolder> {
//FirestoreAdapter<NursePatientsActivityMessagesRecyclerViewAdapter.MessageViewHolder>
public class NursePatientsActivityMessagesRecyclerViewAdapter extends FirestoreAdapter<NursePatientsActivityMessagesRecyclerViewAdapter.MessageViewHolder> {

	public interface OnPatientSelectedListener {

		void onPatientSelected(DocumentSnapshot restaurant);

	}

	private OnPatientSelectedListener mListener;

	public NursePatientsActivityMessagesRecyclerViewAdapter(Query query) {
		super(query);
	}

	public NursePatientsActivityMessagesRecyclerViewAdapter(Query query, OnPatientSelectedListener listener) {
		super(query);
		mListener = listener;
	}

	@NonNull
	@Override
	public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		return new MessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_view_holder, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int position) {
		messageViewHolder.bind(getSnapshot(position), mListener);
	}
	
	
	public static class MessageViewHolder extends RecyclerView.ViewHolder {
		private MessageViewHolderBinding binding;
		public MessageViewHolder(View itemView) {
			super(itemView);
			binding = MessageViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}

		public void bind(final DocumentSnapshot snapshot,
						 final OnPatientSelectedListener listener) {

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
