package com.xunyanhui.model;

/*
 * 类型表
 */
public class ArtistType {

	private String id;
	private String name;// 名称
	private String description;// 类型描述

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	@Override
	public String toString() {
		return "ArtistType [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}

}
