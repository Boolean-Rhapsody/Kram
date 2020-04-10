/*
*  DoctorsPatientsActivityMessagesRecyclerViewAdapter.java
*  Kram
*
*  Created by Unknown.
*  Copyright © 2018 Boolean Rhapsody. All rights reserved.
*/

package com.booleanrhapsody.kram.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.booleanrhapsody.kram.R;
import com.booleanrhapsody.kram.databinding.MessageEighteenViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageFifteenViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageFourteenViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageSeventeenViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageSixteenViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageThirteenViewHolderBinding;
import java.util.*;


public class DoctorsPatientsActivityMessagesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	public static final int MESSAGE_THIRTEEN_VIEW_HOLDER_VIEW_TYPE = 1;
	public static final int MESSAGE_FOURTEEN_VIEW_HOLDER_VIEW_TYPE = 2;
	public static final int MESSAGE_FIFTEEN_VIEW_HOLDER_VIEW_TYPE = 3;
	public static final int MESSAGE_SIXTEEN_VIEW_HOLDER_VIEW_TYPE = 4;
	public static final int MESSAGE_SEVENTEEN_VIEW_HOLDER_VIEW_TYPE = 5;
	public static final int MESSAGE_EIGHTEEN_VIEW_HOLDER_VIEW_TYPE = 6;
	
	public static final List<Integer> MOCK_DATA = Arrays.asList(MESSAGE_THIRTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_FOURTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_FIFTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_SIXTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_SEVENTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_EIGHTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_THIRTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_FOURTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_FIFTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_SIXTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_SEVENTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_EIGHTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_THIRTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_FOURTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_FIFTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_SIXTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_SEVENTEEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_EIGHTEEN_VIEW_HOLDER_VIEW_TYPE);
	
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	
		switch (viewType) {
			case MESSAGE_THIRTEEN_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageThirteenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_thirteen_view_holder, parent, false));
			case MESSAGE_FOURTEEN_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageFourteenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_fourteen_view_holder, parent, false));
			case MESSAGE_FIFTEEN_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageFifteenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_fifteen_view_holder, parent, false));
			case MESSAGE_SIXTEEN_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageSixteenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_sixteen_view_holder, parent, false));
			case MESSAGE_SEVENTEEN_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageSeventeenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_seventeen_view_holder, parent, false));
			case MESSAGE_EIGHTEEN_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageEighteenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_eighteen_view_holder, parent, false));
		}
		
		throw new RuntimeException("Unsupported view type");
	}
	
	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
	
		// Here you can bind RecyclerView item data.
	}
	
	@Override
	public int getItemViewType(int position) {
	
		return MOCK_DATA.get(position);
	}
	
	@Override
	public int getItemCount() {
	
		return MOCK_DATA.size();
	}
	
	
	public static class MessageThirteenViewHolder extends RecyclerView.ViewHolder {
		private MessageThirteenViewHolderBinding binding;
		public MessageThirteenViewHolder(View itemView) {
			super(itemView);
			binding = MessageThirteenViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageFourteenViewHolder extends RecyclerView.ViewHolder {
		private MessageFourteenViewHolderBinding binding;
		public MessageFourteenViewHolder(View itemView) {
			super(itemView);
			binding = MessageFourteenViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageFifteenViewHolder extends RecyclerView.ViewHolder {
		private MessageFifteenViewHolderBinding binding;
		public MessageFifteenViewHolder(View itemView) {
			super(itemView);
			binding = MessageFifteenViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageSixteenViewHolder extends RecyclerView.ViewHolder {
		private MessageSixteenViewHolderBinding binding;
		public MessageSixteenViewHolder(View itemView) {
			super(itemView);
			binding = MessageSixteenViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageSeventeenViewHolder extends RecyclerView.ViewHolder {
		private MessageSeventeenViewHolderBinding binding;
		public MessageSeventeenViewHolder(View itemView) {
			super(itemView);
			binding = MessageSeventeenViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageEighteenViewHolder extends RecyclerView.ViewHolder {
		private MessageEighteenViewHolderBinding binding;
		public MessageEighteenViewHolder(View itemView) {
			super(itemView);
			binding = MessageEighteenViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
}
