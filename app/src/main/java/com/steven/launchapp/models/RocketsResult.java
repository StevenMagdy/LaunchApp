package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RocketsResult {

	@SerializedName("rockets") private List<Rocket> rockets;

	public List<Rocket> getRockets() {
		return rockets;
	}

	public void setRockets(List<Rocket> rockets) {
		this.rockets = rockets;
	}
}
