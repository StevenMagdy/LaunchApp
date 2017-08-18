package com.steven.launchapp.network;

import com.steven.launchapp.models.LaunchesResult;
import com.steven.launchapp.models.MissionsResult;
import com.steven.launchapp.models.RocketsResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LaunchLibraryAPI {

	@GET("launch")
	Single<LaunchesResult> getUpcomingLaunches(@Query("next") int launchesNumber);

	@GET("launch/{id}")
	Single<LaunchesResult> getLaunchDetails(@Path("id") int launchID);

	@GET("mission/{id}")
	Single<MissionsResult> getMission(@Path("id") int missionID);

	@GET("rocket/{id}")
	Single<RocketsResult> getRocket(@Path("id") int rocketID);
}
