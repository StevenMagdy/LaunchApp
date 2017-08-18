package com.steven.launchapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Launch {

	@SerializedName("id") private int id;
	@SerializedName("name") private String name;
	@SerializedName("windowstart") private String windowStart;
	@SerializedName("windowend") private String windowEnd;
	@SerializedName("net") private String net;
	@SerializedName("wsstamp") private long windowStartStamp;
	@SerializedName("westamp") private long windowEndStamp;
	@SerializedName("netstamp") private long netStamp;
	@SerializedName("isostart") private String windowStartISO;
	@SerializedName("isoend") private String windowEndISO;
	@SerializedName("isonet") private String netISO;
	@SerializedName("status") private int statusCode;
	@SerializedName("tbdtime") private int tbdTime;
	@SerializedName("vidURLs") private List<String> vidURLs;
	@SerializedName("infoURLs") private List<String> infoURLs;
	@SerializedName("holdreason") private String holdReason;
	@SerializedName("failreason") private String failReason;
	@SerializedName("tbddate") private int tbdDate;
	@SerializedName("probability") private int probability;
	@SerializedName("hashtag") private String hashtag;
	//@SerializedName("location") private Location location;
	@SerializedName("rocket") private Rocket rocket;
	@SerializedName("missions") private List<Mission> missions;

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

	public String getWindowStart() {
		return windowStart;
	}

	public void setWindowStart(String windowStart) {
		this.windowStart = windowStart;
	}

	public String getWindowEnd() {
		return windowEnd;
	}

	public void setWindowEnd(String windowEnd) {
		this.windowEnd = windowEnd;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public long getWindowStartStamp() {
		return windowStartStamp;
	}

	public void setWindowStartStamp(long windowStartStamp) {
		this.windowStartStamp = windowStartStamp;
	}

	public long getWindowEndStamp() {
		return windowEndStamp;
	}

	public void setWindowEndStamp(long windowEndStamp) {
		this.windowEndStamp = windowEndStamp;
	}

	public long getNetStamp() {
		return netStamp;
	}

	public void setNetStamp(long netStamp) {
		this.netStamp = netStamp;
	}

	public String getWindowStartISO() {
		return windowStartISO;
	}

	public void setWindowStartISO(String windowStartISO) {
		this.windowStartISO = windowStartISO;
	}

	public String getWindowEndISO() {
		return windowEndISO;
	}

	public void setWindowEndISO(String windowEndISO) {
		this.windowEndISO = windowEndISO;
	}

	public String getNetISO() {
		return netISO;
	}

	public void setNetISO(String netISO) {
		this.netISO = netISO;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getTbdTime() {
		return tbdTime;
	}

	public void setTbdTime(int tbdTime) {
		this.tbdTime = tbdTime;
	}

	public List<String> getVidURLs() {
		return vidURLs;
	}

	public void setVidURLs(List<String> vidURLs) {
		this.vidURLs = vidURLs;
	}

	public List<String> getInfoURLs() {
		return infoURLs;
	}

	public void setInfoURLs(List<String> infoURLs) {
		this.infoURLs = infoURLs;
	}

	public String getHoldReason() {
		return holdReason;
	}

	public void setHoldReason(String holdReason) {
		this.holdReason = holdReason;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public int getTbdDate() {
		return tbdDate;
	}

	public void setTbdDate(int tbdDate) {
		this.tbdDate = tbdDate;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	// public Location getLocation() {
	// 	return location;
	// }
	//
	// public void setLocation(Location location) {
	// 	this.location = location;
	// }

	public Rocket getRocket() {
		return rocket;
	}

	public void setRocket(Rocket rocket) {
		this.rocket = rocket;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
}
