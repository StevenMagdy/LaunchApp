package com.steven.launchapp.settings;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.steven.launchapp.R;
import com.steven.launchapp.base.BaseActivity;

public class SettingsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		setSupportActionBar(findViewById(R.id.toolbar));
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setTitle(R.string.menu_settings_title);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}
}
