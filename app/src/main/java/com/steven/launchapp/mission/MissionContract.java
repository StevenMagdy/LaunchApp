package com.steven.launchapp.mission;

public interface MissionContract {

	interface View {

		void setProgress(boolean inProgress);

		void showMissionDescription(String description);

		void showConnectionError();
	}

	interface Presenter {

		void loadDetails(boolean forceUpdate, int missionID);

		void attachView(View view);

		void detachView();
	}
}
