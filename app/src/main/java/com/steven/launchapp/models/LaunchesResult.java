package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaunchesResult extends Result {

	@SerializedName("launches") private List<Launch> launches;

	public List<Launch> getLaunches() {
		return launches;
	}

	public void setLaunches(List<Launch> launches) {
		this.launches = launches;
	}
}
