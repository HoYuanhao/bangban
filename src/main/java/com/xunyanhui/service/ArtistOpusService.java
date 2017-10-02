/**
 * 创建日期：2017-1-11上午10:04:40
 * 创建日期：2016-12-20下午4:02:27
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service;


import com.xunyanhui.model.ArtistOpus;

public interface ArtistOpusService {

	
	/**
	 *  新建小样或作品
	 * @param artistOpus
	 * @return
	 */
	public int insertArtistOpus(ArtistOpus artistOpus);
	/**
	 * 维护小样封面信息
	 * @param artistOpus
	 * @return
	 */
	public int updateArtistOpusHomePic(ArtistOpus artistOpus);
	/**
	 * 维护小样信息
	 * @param artistOpus
	 * @return
	 */
	public int updateArtistOpus(ArtistOpus artistOpus);
	/**
	 * 检查小样或作品是否为用户发布
	 * @param oid		
	 * @return
	 */
	public String checkUidOfOpus(String oid);
	/**
	 * 修改小样的点赞数
	 * @param oid			小样或作品的id
	 * @param value			1点赞，-1取消点赞
	 * @return
	 */
	public int  updatePraise( String oid,int value);
	/**
	 * 修改艺人的好评数和好评率
	 * @param artistid			艺人的id
	 * @param praise			1好评，0差评
	 * @return
	 */
	public int  updateGoodLevel(String artistid,int praise);
	
	/**
	 * 将小样或作品的播放数加1
	 * @param oid				小样或作品id
	 * @return
	 */
	public int incPlayNum(String oid);
}
