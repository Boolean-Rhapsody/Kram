/*
*  TabGroupOneActivityPagerAdapter.java
*  Kram
*
*  Created by Unknown.
*  Copyright © 2018 Boolean Rhapsody. All rights reserved.
*/

package com.booleanrhapsody.kram.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.*;


public class TabGroupOneActivityPagerAdapter extends FragmentPagerAdapter {
	public final List<Fragment> fragments;
	
	public TabGroupOneActivityPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
		super(fragmentManager);
		this.fragments = fragments;
	}
	
	@Override
	public Fragment getItem(int position) {
	
		return fragments.get(position);
	}
	
	@Override
	public int getCount() {
	
		return fragments.size();
	}
}
