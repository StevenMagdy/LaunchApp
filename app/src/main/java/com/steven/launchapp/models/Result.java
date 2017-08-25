package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

class Result {

	@SerializedName("total") private int total;
	@SerializedName("offset") private int offset;
	@SerializedName("count") private int count;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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
