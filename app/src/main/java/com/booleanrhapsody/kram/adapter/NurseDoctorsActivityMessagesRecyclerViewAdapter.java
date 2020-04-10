/*
*  NurseDoctorsActivityMessagesRecyclerViewAdapter.java
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
import com.booleanrhapsody.kram.databinding.MessageEightViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageElevenViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageNineViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageSevenViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageTenViewHolderBinding;
import com.booleanrhapsody.kram.databinding.MessageTwelveViewHolderBinding;
import java.util.*;


public class NurseDoctorsActivityMessagesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	public static final int MESSAGE_SEVEN_VIEW_HOLDER_VIEW_TYPE = 1;
	public static final int MESSAGE_EIGHT_VIEW_HOLDER_VIEW_TYPE = 2;
	public static final int MESSAGE_NINE_VIEW_HOLDER_VIEW_TYPE = 3;
	public static final int MESSAGE_TEN_VIEW_HOLDER_VIEW_TYPE = 4;
	public static final int MESSAGE_ELEVEN_VIEW_HOLDER_VIEW_TYPE = 5;
	public static final int MESSAGE_TWELVE_VIEW_HOLDER_VIEW_TYPE = 6;
	
	public static final List<Integer> MOCK_DATA = Arrays.asList(MESSAGE_SEVEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_EIGHT_VIEW_HOLDER_VIEW_TYPE, MESSAGE_NINE_VIEW_HOLDER_VIEW_TYPE, MESSAGE_TEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_ELEVEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_TWELVE_VIEW_HOLDER_VIEW_TYPE, MESSAGE_SEVEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_EIGHT_VIEW_HOLDER_VIEW_TYPE, MESSAGE_NINE_VIEW_HOLDER_VIEW_TYPE, MESSAGE_TEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_ELEVEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_TWELVE_VIEW_HOLDER_VIEW_TYPE, MESSAGE_SEVEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_EIGHT_VIEW_HOLDER_VIEW_TYPE, MESSAGE_NINE_VIEW_HOLDER_VIEW_TYPE, MESSAGE_TEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_ELEVEN_VIEW_HOLDER_VIEW_TYPE, MESSAGE_TWELVE_VIEW_HOLDER_VIEW_TYPE);
	
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	
		switch (viewType) {
			case MESSAGE_SEVEN_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageSevenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_seven_view_holder, parent, false));
			case MESSAGE_EIGHT_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageEightViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_eight_view_holder, parent, false));
			case MESSAGE_NINE_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageNineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_nine_view_holder, parent, false));
			case MESSAGE_TEN_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageTenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_ten_view_holder, parent, false));
			case MESSAGE_ELEVEN_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageElevenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_eleven_view_holder, parent, false));
			case MESSAGE_TWELVE_VIEW_HOLDER_VIEW_TYPE: 
				return new MessageTwelveViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_twelve_view_holder, parent, false));
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
	
	
	public static class MessageSevenViewHolder extends RecyclerView.ViewHolder {
		private MessageSevenViewHolderBinding binding;
		public MessageSevenViewHolder(View itemView) {
			super(itemView);
			binding = MessageSevenViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageEightViewHolder extends RecyclerView.ViewHolder {
		private MessageEightViewHolderBinding binding;
		public MessageEightViewHolder(View itemView) {
			super(itemView);
			binding = MessageEightViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageNineViewHolder extends RecyclerView.ViewHolder {
		private MessageNineViewHolderBinding binding;
		public MessageNineViewHolder(View itemView) {
			super(itemView);
			binding = MessageNineViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageTenViewHolder extends RecyclerView.ViewHolder {
		private MessageTenViewHolderBinding binding;
		public MessageTenViewHolder(View itemView) {
			super(itemView);
			binding = MessageTenViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageElevenViewHolder extends RecyclerView.ViewHolder {
		private MessageElevenViewHolderBinding binding;
		public MessageElevenViewHolder(View itemView) {
			super(itemView);
			binding = MessageElevenViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
	
	
	public static class MessageTwelveViewHolder extends RecyclerView.ViewHolder {
		private MessageTwelveViewHolderBinding binding;
		public MessageTwelveViewHolder(View itemView) {
			super(itemView);
			binding = MessageTwelveViewHolderBinding.bind(itemView);
			init();
		}
		
		public void init() {
		
		}
	}
}
