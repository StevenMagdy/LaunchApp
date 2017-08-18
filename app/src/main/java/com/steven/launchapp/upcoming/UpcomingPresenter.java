package com.steven.launchapp.upcoming;

import android.util.Log;

import com.steven.launchapp.models.LaunchesResult;
import com.steven.launchapp.network.LaunchLibraryAPI;
import com.steven.launchapp.utils.RxUtils;

import io.reactivex.disposables.Disposable;

public class UpcomingPresenter implements UpcomingContract.Presenter {

	private static final String TAG = UpcomingPresenter.class.getSimpleName();

	private UpcomingContract.View view;
	private LaunchLibraryAPI launchLibraryAPI;

	private Disposable disposable;

	UpcomingPresenter(LaunchLibraryAPI launchLibraryAPI) {
		this.launchLibraryAPI = launchLibraryAPI;
	}

	@Override
	public void attachView(UpcomingContract.View view) {
		this.view = view;
	}

	@Override
	public void detachView() {
		if (disposable != null) disposable.dispose();
		this.view = null;
	}

	@Override
	public void loadLaunches(boolean forceUpdate, int launchesNumber) {
		view.setProgress(true);
		disposable = launchLibraryAPI.getUpcomingLaunches(launchesNumber)
				.compose(RxUtils.singleIOToMainThreadScheduler())
				.map(LaunchesResult::getLaunches)
				.subscribe(launches -> {
					view.setProgress(false);
					view.showLaunches(launches);
				}, throwable -> {
					Log.e(TAG, "Observable Subscribing Error", throwable);
					view.setProgress(false);
					view.showConnectionError();
				});
	}
}
