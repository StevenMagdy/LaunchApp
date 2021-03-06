package com.steven.launchapp.rocket;

public interface RocketContract {

	interface View {

		void setProgress(boolean inProgress);

		void showRocketName(String name);

		void showRocketFamily(String name);

		void showRocketSite(String url);

		void showRocketWiki(String url);

		void showRocketImage(String imageURL);

		void showConnectionError();
	}

	interface Presenter {

		void loadDetails(boolean forceUpdate, int rocketID);

		void attachView(View view);

		void detachView();
	}
}
