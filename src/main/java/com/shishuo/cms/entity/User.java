/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.entity;

import java.util.Date;

/**
 * 用户实体类
 * 
 * @author liuqi
 * 
 */

public class User {

	/**
	 * 管理员Id
	 */
	private long userId;

	/**
	 * 管理员名称
	 */
	private String name;

	
	/**
	 * 登录帐号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 时间
	 */
	private Date createTime;
	
	/**
	 * 时间
	 */
	private Date updateTime;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
