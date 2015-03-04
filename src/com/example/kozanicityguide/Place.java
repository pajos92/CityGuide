package com.example.kozanicityguide;

public class Place {

	private String name;
	private int iconId;
	private PlaceType type;
	private double latitude;
	private double longitude;
	private int distance;
	private String description;

	public Place(String name, int iconId, PlaceType type, double latitude,
			double longitude, String description) {
		super();
		this.name = name;
		this.iconId = iconId;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlaceType getType() {
		return type;
	}

	public void setType(PlaceType type) {
		this.type = type;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public int getDistanceInMeters() {
		return distance;
	}

	public void setDistanceInMeters(int distance) {
		this.distance = distance;
	}

}
