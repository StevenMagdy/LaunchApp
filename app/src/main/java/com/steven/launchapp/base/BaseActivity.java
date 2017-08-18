package com.steven.launchapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BaseActivity extends AppCompatActivity {

	protected final String TAG = getClass().getSimpleName();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Activity Created");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "Activity Paused");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "Activity Resumed");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "Activity Destroyed");
	}
}
