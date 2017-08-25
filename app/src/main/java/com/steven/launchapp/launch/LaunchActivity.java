package com.steven.launchapp.launch;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.steven.launchapp.R;
import com.steven.launchapp.base.BaseActivity;

import static com.steven.launchapp.utils.Utils.LAUNCH_ID_KEY;
import static com.steven.launchapp.utils.Utils.LAUNCH_NAME_KEY;
import static com.steven.launchapp.utils.Utils.MISSION_ID_KEY;
import static com.steven.launchapp.utils.Utils.ROCKET_ID_KEY;

public class LaunchActivity extends BaseActivity {

	private int launchID;
	private int missionID;
	private int rocketID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		setSupportActionBar(findViewById(R.id.toolbar));
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) setupActionBar(actionBar);
		ViewPager viewPager = findViewById(R.id.viewPager);
		LaunchPagerAdapter launchPagerAdapter =
				new LaunchPagerAdapter(getSupportFragmentManager(), this);
		viewPager.setAdapter(launchPagerAdapter);
		TabLayout tabLayout = findViewById(R.id.tabLayout);
		tabLayout.setupWithViewPager(viewPager);
		if (getIntent().hasExtra(LAUNCH_ID_KEY)) {
			launchID = getIntent().getIntExtra(LAUNCH_ID_KEY, 0);
		}
		if (savedInstanceState != null) {
			missionID = savedInstanceState.getInt(MISSION_ID_KEY);
			rocketID = savedInstanceState.getInt(ROCKET_ID_KEY);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt(MISSION_ID_KEY, missionID);
		outState.putInt(ROCKET_ID_KEY, rocketID);

		super.onSaveInstanceState(outState);
	}

	public int getLaunchID() {
		return launchID;
	}

	public int getMissionID() {
		return missionID;
	}

	public void setMissionId(int id) {
		missionID = id;
	}

	public int getRocketID() {
		return rocketID;
	}

	public void setRocketID(int id) {
		rocketID = id;
	}

	private void setupActionBar(ActionBar actionBar) {
		if (getIntent().hasExtra(LAUNCH_NAME_KEY)) {
			actionBar.setTitle(getIntent().getStringExtra(LAUNCH_NAME_KEY));
		}
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
