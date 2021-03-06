package com.steven.launchapp.launch;

import android.util.Log;

import com.steven.launchapp.models.Launch;
import com.steven.launchapp.network.LaunchLibraryAPI;
import com.steven.launchapp.utils.RxUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
				.subscribe(this::showDetails, throwable -> {
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
		if (view == null) return;
		view.setProgress(false);
		if (launch.getRocket() != null) view.setRocketID(launch.getRocket().getId());
		if (launch.getMissions() != null && launch.getMissions().size() > 0) {
			view.setMissionID(launch.getMissions().get(0).getId());
		}
		if (launch.getVidURLs() != null && launch.getVidURLs().size() > 0) {
			view.showLiveUrl(launch.getVidURLs().get(0));
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
