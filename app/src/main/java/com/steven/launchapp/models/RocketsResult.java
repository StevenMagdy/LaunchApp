package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RocketsResult {

	@SerializedName("total") private int total;
	@SerializedName("rockets") private List<Rocket> rockets;
	@SerializedName("offset") private int offset;
	@SerializedName("count") private int count;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Rocket> getRockets() {
		return rockets;
	}

	public void setRockets(List<Rocket> rockets) {
		this.rockets = rockets;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
