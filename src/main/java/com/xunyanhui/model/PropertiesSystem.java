/**
 * 创建日期：2016-12-14下午6:24:38
 * 修改日期：
 * 作者：邢传军
 * 目的：系统自定义的配置文件
 */
package com.xunyanhui.model;

public class PropertiesSystem {
	// 静态资源的上传路径
	private String repository_path;
	// 静态资源的访问url的path部分
	private String image_base_url;

	public String getRepository_path() {
		return repository_path;
	}

	public void setRepository_path(String repository_path) {
		this.repository_path = repository_path;
	}

	public String getImage_base_url() {
		return image_base_url;
	}

	public void setImage_base_url(String image_base_url) {
		this.image_base_url = image_base_url;
	}

}
