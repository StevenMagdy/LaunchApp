package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

public class Mission {

	@SerializedName("id") private int id;
	@SerializedName("name") private String name;
	@SerializedName("description") private String description;
	@SerializedName("type") private int type;
	@SerializedName("typeName") private String typeName;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
