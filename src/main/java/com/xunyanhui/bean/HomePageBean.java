package com.xunyanhui.bean;

import java.util.List;

public class HomePageBean {

	private String[] headPic;
	private List<HomeArtist> artist;
	private List<HomePic> homePic;


	public String[] getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String[] headPic) {
		this.headPic = headPic;
	}
	
	public List<HomePic> getHomePic() {
		return homePic;
	}

	public void setHomePic(List<HomePic> homePic) {
		this.homePic = homePic;

	}

	public List<HomeArtist> getArtist() {
		return artist;
	}

	public void setArtist(List<HomeArtist> artist) {
		this.artist = artist;
	}


	@Override
	public String toString() {
		return "HomePageBean [homePic=" + homePic + ", artist=" + artist + "]";
	}


}
