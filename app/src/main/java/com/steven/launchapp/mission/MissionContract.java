package com.steven.launchapp.mission;

 interface MissionContract {

	interface View {

		void setProgress(boolean inProgress);

		void showMissionName(String name);

		void showMissionType(String type);

		void showMissionDescription(String description);

		void showConnectionError();
	}

	interface Presenter {

		void loadDetails(boolean forceUpdate, int missionID);

		void attachView(View view);

		void detachView();
	}
}
