package com.steven.launchapp.launch;

import java.util.Date;

interface LaunchContract {

	interface View {

		void showName(String name);

		void showStatus(int code);

		void showNet(Date date);

		void showWindowStart(Date date);

		void showWindowEnd(Date date);

		void showLiveUrl(String url);

		void setRocketID(int id);

		void setMissionID(int id);

		void setProgress(boolean inProgress);

		void showConnectionError();
	}

	interface Presenter {

		void loadDetails(boolean forceUpdate, int launchID);

		void attachView(View view);

		void detachView();
	}
}
