package com.steven.launchapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.steven.launchapp.network.DaggerNetworkComponent;
import com.steven.launchapp.network.NetworkComponent;
import com.steven.launchapp.network.NetworkModule;

public class MyApp extends Application {

	private static final String TAG = MyApp.class.getSimpleName();
	private NetworkComponent networkComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		if (LeakCanary.isInAnalyzerProcess(this)) {
			return;
		}
		LeakCanary.install(this);
		networkComponent = DaggerNetworkComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.networkModule(new NetworkModule())
				.build();
	}

	public NetworkComponent getNetworkComponent() {
		return networkComponent;
	}
}
