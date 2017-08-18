package com.steven.launchapp.launch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.steven.launchapp.mission.MissionFragment;
import com.steven.launchapp.rocket.RocketFragment;

class LaunchPagerAdapter extends FragmentPagerAdapter {

	private static final String TAG = LaunchPagerAdapter.class.getSimpleName();

	LaunchPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
			case 0:
				return LaunchDetailsFragment.getInstance();
			case 1:
				return RocketFragment.getInstance();
			case 2:
				return MissionFragment.getInstance();
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
				return "Details";
			case 1:
				return "Rocket";
			case 2:
				return "Mission";
			default:
				return null;
		}
	}
}
