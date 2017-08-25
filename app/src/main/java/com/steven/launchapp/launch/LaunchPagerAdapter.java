package com.steven.launchapp.launch;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.steven.launchapp.R;
import com.steven.launchapp.mission.MissionFragment;
import com.steven.launchapp.rocket.RocketFragment;

class LaunchPagerAdapter extends FragmentPagerAdapter {

	private static final String TAG = LaunchPagerAdapter.class.getSimpleName();

	private Context context;

	LaunchPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
			case 0:
				return LaunchDetailsFragment.getInstance(null);
			case 1:
				return RocketFragment.getInstance(null);
			case 2:
				return MissionFragment.getInstance(null);
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return context.getString(R.string.details_title);
			case 1:
				return context.getString(R.string.rocket_title);
			case 2:
				return context.getString(R.string.mission_title);
			default:
				return null;
		}
	}
}
