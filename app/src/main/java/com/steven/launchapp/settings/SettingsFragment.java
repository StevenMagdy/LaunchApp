package com.steven.launchapp.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.steven.launchapp.R;

public class SettingsFragment extends PreferenceFragment {

	private static final String TAG = SettingsFragment.class.getName();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
