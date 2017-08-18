package com.steven.launchapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	protected final String TAG = getClass().getSimpleName();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Fragment Created");
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "Fragment View Created");
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "Fragment Paused");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "Fragment Resumed");
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		Log.d(TAG, "Fragment isVisible: " + String.valueOf(isVisibleToUser));
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d(TAG, "Fragment View Destroyed");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "Fragment Destroyed");
	}
}
