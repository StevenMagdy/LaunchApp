package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

public class RocketFamily {

	@SerializedName("id") private int id;
	@SerializedName("name") private String name;
	@SerializedName("agencies") private String agencies;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgencies() {
		return agencies;
	}

	public void setAgencies(String agencies) {
		this.agencies = agencies;
	}
}
