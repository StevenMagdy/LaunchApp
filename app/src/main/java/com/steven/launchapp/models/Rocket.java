package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rocket {

	@SerializedName("id") private int id;
	@SerializedName("name") private String name;
	@SerializedName("configuration") private String configuration;
	@SerializedName("defaultPads") private String defaultPads;
	@SerializedName("family") private RocketFamily rocketFamily;
	@SerializedName("wikiURL") private String wikiURL;
	@SerializedName("infoURLs") private List<String> infoURLs;
	@SerializedName("imageSizes") private List<Integer> imageSizes;
	@SerializedName("imageURL") private String imageURL;

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

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getDefaultPads() {
		return defaultPads;
	}

	public void setDefaultPads(String defaultPads) {
		this.defaultPads = defaultPads;
	}

	public RocketFamily getRocketFamily() {
		return rocketFamily;
	}

	public void setRocketFamily(RocketFamily rocketFamily) {
		this.rocketFamily = rocketFamily;
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

	public List<Integer> getImageSizes() {
		return imageSizes;
	}

	public void setImageSizes(List<Integer> imageSizes) {
		this.imageSizes = imageSizes;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
