package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MissionsResult {

	@SerializedName("total") private int total;
	@SerializedName("missions") private List<Mission> missions;
	@SerializedName("offset") private int offset;
	@SerializedName("count") private int count;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
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
