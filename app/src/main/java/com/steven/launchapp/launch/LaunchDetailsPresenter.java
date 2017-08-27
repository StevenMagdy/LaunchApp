package com.steven.launchapp.launch;

import android.util.Log;

import com.steven.launchapp.models.Launch;
import com.steven.launchapp.models.Mission;
import com.steven.launchapp.models.Rocket;
import com.steven.launchapp.network.LaunchLibraryAPI;
import com.steven.launchapp.utils.RxUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.disposables.Disposable;

class LaunchDetailsPresenter implements LaunchContract.Presenter {

	private static final String TAG = LaunchDetailsPresenter.class.getSimpleName();

	private LaunchLibraryAPI launchLibraryAPI;
	private LaunchContract.View view;
	private Disposable disposable;

	LaunchDetailsPresenter(LaunchLibraryAPI launchLibraryAPI) {
		this.launchLibraryAPI = launchLibraryAPI;
	}

	@Override
	public void loadDetails(boolean forceUpdate, int launchID) {
		view.setProgress(true);
		disposable = launchLibraryAPI.getLaunchDetails(launchID)
				.compose(RxUtils.singleIOToMainThreadScheduler())
				.map(launchesResult -> launchesResult.getLaunches().get(0))
				.subscribe(launch -> {
					view.setProgress(false);
					showDetails(launch);
				}, throwable -> {
					Log.e(TAG, "Observable Subscribing Error", throwable);
					view.setProgress(false);
					view.showConnectionError();
				});
	}

	@Override
	public void attachView(LaunchContract.View view) {
		this.view = view;
	}

	@Override
	public void detachView() {
		if (disposable != null) disposable.dispose();
		view = null;
	}

	private void showDetails(Launch launch) {
		Rocket rocket = launch.getRocket();
		if (rocket != null) view.setRocketID(rocket.getId());
		List<Mission> missionList = launch.getMissions();
		if (missionList != null && missionList.size() > 0) {
			view.setMissionID(missionList.get(0).getId());
		}
		view.showName(launch.getName());

		view.showStatus(launch.getStatusCode());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss Z");
		try {
			view.showNet(simpleDateFormat.parse(launch.getNet()));
			view.showWindowStart(simpleDateFormat.parse(launch.getWindowStart()));
			view.showWindowEnd(simpleDateFormat.parse(launch.getWindowEnd()));
		} catch (ParseException e) {
			Log.e(TAG, "Date Parsing Error", e);
		}
	}
}
