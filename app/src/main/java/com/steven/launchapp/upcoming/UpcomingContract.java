package com.steven.launchapp.upcoming;

import com.steven.launchapp.models.Launch;

import java.util.List;

interface UpcomingContract {

	interface View {

		void showLaunches(List<Launch> launches);

		void setProgress(boolean inProgress);

		void showConnectionError();
	}

	interface Presenter {

		void loadLaunches(boolean forceUpdate, int launchesNumber);

		void attachView(View view);

		void detachView();
	}
}
