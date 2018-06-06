package com.dangelo.xxoa.model;

import java.util.List;


public class MonArtcles {
	public String date; // "2014-08-11",
	
	public List<contents> contentsList; // [
	public String getDate() {
		return date;
	}

	public List<contents> getContentsList() {
		return contentsList;
	}

	public void setContentsList(List<contents> contentsList) {
		this.contentsList = contentsList;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
