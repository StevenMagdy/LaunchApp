package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaunchesResult {

	@SerializedName("total") private int total;
	@SerializedName("launches") private List<Launch> launches;
	@SerializedName("offset") private int offset;
	@SerializedName("count") private int count;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Launch> getLaunches() {
		return launches;
	}

	public void setLaunches(List<Launch> launches) {
		this.launches = launches;
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
