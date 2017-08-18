package com.steven.launchapp.network;

import com.steven.launchapp.ApplicationModule;
import com.steven.launchapp.launch.LaunchDetailsFragment;
import com.steven.launchapp.mission.MissionFragment;
import com.steven.launchapp.rocket.RocketFragment;
import com.steven.launchapp.upcoming.UpcomingActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {

	void inject(UpcomingActivity upcomingActivity);

	void inject(LaunchDetailsFragment launchDetailsFragment);

	void inject(MissionFragment missionFragment);

	void inject(RocketFragment rocketFragment);

}
