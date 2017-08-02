package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Agency {

	@SerializedName("id") private int id;
	@SerializedName("name") private String name;
	@SerializedName("abbrev") private String abbrev;
	@SerializedName("countryCode") private String countryCode;
	@SerializedName("type") private int type;
	@SerializedName("wikiURL") private String wikiURL;
	@SerializedName("infoURLs") private List<String> infoURLs;

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

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getWikiURL() {
		return wikiURL;
	}

	public void setWikiURL(String wikiURL) {
		this.wikiURL = wikiURL;
	}

	public List<String> getInfoURLs() {
		return infoURLs;
	}

	public void setInfoURLs(List<String> infoURLs) {
		this.infoURLs = infoURLs;
	}
}
