package com.steven.launchapp.mission;

import android.util.Log;

import com.steven.launchapp.models.Mission;
import com.steven.launchapp.network.LaunchLibraryAPI;

import io.reactivex.disposables.Disposable;

import static com.steven.launchapp.utils.RxUtils.singleIOToMainThreadScheduler;

class MissionPresenter implements MissionContract.Presenter {

	private static final String TAG = MissionPresenter.class.getSimpleName();

	private Disposable disposable;
	private LaunchLibraryAPI launchLibraryAPI;
	private MissionContract.View view;

	MissionPresenter(LaunchLibraryAPI launchLibraryAPI) {
		this.launchLibraryAPI = launchLibraryAPI;
	}

	@Override
	public void loadDetails(boolean forceUpdate, int missionID) {
		disposable = launchLibraryAPI.getMission(missionID)
				.compose(singleIOToMainThreadScheduler())
				.map(missionsResult -> missionsResult.getMissions().get(0))
				.subscribe(mission -> {
					view.setProgress(false);
					showMission(mission);
				}, throwable -> {
					Log.e(TAG, "Observable Subscribing Error", throwable);
					view.setProgress(false);
					view.showConnectionError();
				});
	}

	@Override
	public void attachView(MissionContract.View view) {
		this.view = view;
	}

	@Override
	public void detachView() {
		if (disposable != null) disposable.dispose();
		view = null;
	}

	private void showMission(Mission mission) {
		view.showMissionDescription(mission.getDescription());
	}
}
