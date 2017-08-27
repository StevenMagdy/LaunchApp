package com.steven.launchapp.rocket;

import android.util.Log;

import com.steven.launchapp.models.Rocket;
import com.steven.launchapp.network.LaunchLibraryAPI;
import com.steven.launchapp.utils.RxUtils;

import io.reactivex.disposables.Disposable;

class RocketPresenter implements RocketContract.Presenter {

	private static final String TAG = RocketPresenter.class.getSimpleName();

	private LaunchLibraryAPI launchLibraryAPI;
	private Disposable disposable;
	private RocketContract.View view;

	RocketPresenter(LaunchLibraryAPI launchLibraryAPI) {
		this.launchLibraryAPI = launchLibraryAPI;
	}

	@Override
	public void loadDetails(boolean forceUpdate, int rocketID) {
		view.setProgress(true);
		disposable = launchLibraryAPI.getRocket(rocketID)
				.compose(RxUtils.singleIOToMainThreadScheduler())
				.map(rocketsResult -> rocketsResult.getRockets().get(0))
				.subscribe(rocket -> {
					view.setProgress(false);
					showDetails(rocket);
				}, throwable -> {
					Log.e(TAG, "Observable Subscribing Error", throwable);
					view.setProgress(false);
					view.showConnectionError();
				});
	}

	@Override
	public void attachView(RocketContract.View view) {
		this.view = view;
	}

	@Override
	public void detachView() {
		if (disposable != null) disposable.dispose();
		view = null;
	}

	private void showDetails(Rocket rocket) {
		view.showRocketName(rocket.getName());

		if (rocket.getRocketFamily() != null) {
			view.showRocketFamily(rocket.getRocketFamily().getName());
		}

		if (rocket.getWikiURL() != null) view.showRocketWiki(rocket.getWikiURL());
		if (rocket.getInfoURLs() != null && rocket.getInfoURLs().size() > 0) {
			view.showRocketSite(rocket.getInfoURLs().get(0));
		}

		String imageURL = rocket.getImageURL().replace(String.valueOf(rocket.getImageSizes()
				.get(rocket.getImageSizes().size() - 1)), String.valueOf(rocket.getImageSizes()
				.get(rocket.getImageSizes().size() / 2)));
		view.showRocketImage(imageURL);
	}
}
