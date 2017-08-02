package com.steven.launchapp;

import com.steven.launchapp.models.LaunchesResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LaunchLibraryAPI {

	@GET("launch")
	Observable<LaunchesResult> getUpcomingLaunches(@Query("next") int launchesNumber);
}
