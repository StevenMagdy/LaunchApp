package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MissionsResult extends Result {

	@SerializedName("missions") private List<Mission> missions;

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
}
