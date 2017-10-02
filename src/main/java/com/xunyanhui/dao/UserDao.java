package com.xunyanhui.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.bean.UserInfoForEasemob;
import com.xunyanhui.model.Artist;
import com.xunyanhui.model.AuthFile;
import com.xunyanhui.model.Coupon;
import com.xunyanhui.model.Employer;
import com.xunyanhui.model.User;

public interface UserDao {
	/**
	 * 获取用户对应的cid
	 * @param id
	 *            要获取User的id
	 * @return 根据id取到的User 如果没有则返回值为Null
	 */
	public String getCid(String id);

	/**
	 * 获取首页的艺人列表
	 * 
	 * @param date
	 *            时间戳
	 * @param page
	 *            要获取的页数
	 * @param pageCount
	 *            每页的页数
	 * @return 返回用户对象
	 */
	public List<User> getHomeArtistList(
			@Param(value = "date") Date date,// 已完成
			@Param(value = "beginIndex") int beginIndex,
			@Param(value = "pageCount") int pageCount);

	/**
	 * 根据ID获取用户信息
	 * 
	 * @param id
	 *            要获取User的id
	 * @return 根据id取到的User 如果没有则返回值为Null
	 */
	public User getUserById(String id);

	/**
	 * 
	 * @param name
	 *            用户名
	 * @param passwd
	 *            密码
	 * @return 根据用户名和密码取到的User，如果没有则为null
	 */
	// public User getUserById(String name, String passwd);

	/**
	 * 取所有的User
	 * 
	 * @return 返回值为List的User集合
	 */
	// public List<User> getUserList();
	public User getUserByUserNameAndPasswd(
			@Param(value = "userName") String userName,
			@Param(value = "passwd") String passwd);

	/**
	 * 是否有该用户的信息
	 * 
	 * @param userName
	 *            需要验证的用户名
	 * @return 返回值 0 没有该用户 1 有该用户
	 */
	public int hasUserByUserName(@Param(value = "userName") String userName);

	/**
	 * 艺人页的艺人列表查询方法
	 * 
	 * @param date
	 *            时间戳
	 * @param order
	 *            排序方法
	 * @param type
	 *            类型
	 * @param gender
	 *            性别
	 * @param beginIndex
	 *            开始索引
	 * @param pageCount
	 *            每页的数量
	 * @return
	 */
	public List<User> getArtiArtistList(@Param(value = "date") Date date,
			@Param(value = "order") int order,
			@Param(value = "type") String type,
			@Param(value = "gender") int gender,
			@Param(value = "beginIndex") int beginIndex,
			@Param(value = "pageCount") int pageCount);

	public List<User> searchByKey(@Param(value = "key") String key,
			@Param(value = "date") Date date,
			@Param(value = "beginIndex") int beginIndex,
			@Param(value = "pageCount") int pageCount);

	/**
	 * 根据id取出艺人信息
	 * 
	 * @param id
	 *            需要取出艺人的id
	 * @return 取出的艺人对象
	 */
	public Artist getAtristById(String id);

	/**
	 * 返回总用户数
	 * 
	 * @return 返回值的所有用户数的long类型的数值
	 */
	// public long getUserCount();
	public long getUserCount();

	/**
	 * 获取推荐艺人列表
	 * 
	 * @param time
	 *            时间戳
	 * @param page
	 *            要取的页数
	 * @param pageCount
	 *            每页的数量
	 * @return 返回艺人用户列表
	 */

	public User getreCommendArtist(@Param(value = "time") Date time,
			@Param(value = "page") int page,
			@Param(value = "pageCount") int pageCount);

	/**
	 * 根据id修改密码
	 * 
	 * @param id
	 *            需要修改密码的用户Id
	 * @param oldPasswd
	 *            以前的密码
	 * @param newPasswd
	 *            修改的新密码
	 */
	public int updatePasswd(
			@Param(value = "id") String id, 
			@Param(value = "oldPasswd") String oldPasswd, 
			@Param(value = "newPasswd") String newPasswd);

	/**
	 * 注册一个用户
	 * 
	 * @param user
	 *            需要注册的用户数据
	 */
	public void addUser(User user);

	public void registUser(User user);

	public int hasMobile(String mobile);

	/**
	 * 添加一条艺人数据
	 * 
	 * @param artist
	 *            需要添加的艺人信息的对象
	 */

	public void addArtist(Artist artist);

	/**
	 * 添加一条雇主信息
	 * 
	 * @param employer
	 *            需要插入的雇主信息的对象
	 */

	public void addEmployer(Employer employer);

	/**
	 * 给用户添加一张卡卷
	 * 
	 * @param id
	 *            需要添加卡卷的用户id
	 * @param coupon
	 *            添加卡卷信息
	 */
	public void addCoupon(String id, Coupon coupon);

	/**
	 * 更新卡卷使用情况，使卡卷的为已使用。
	 */
	public void updateCoupon();

	/**
	 * 更行认证文件
	 * 
	 * @param authFile
	 */
	public void addAuthFile(AuthFile authFile);

	public void updateAuthFile();
	/**
	 * 用于将当前所用用户添加到环信的用户系统中
	 */
	public List<UserInfoForEasemob> selectAllUser();
	
	
	/**
	 * 根据注册的手机号修改密码，用于密码找回或密码重置
	 * 
	 * @param mobile
	 *            需要修改密码的用户注册mobile
	 * @param passwd
	 *            修改的新密码
	 */
	public int updatePasswdByMobile(
			@Param(value = "mobile") String mobile, 
			@Param(value = "passwd") String passwd);
	

}
