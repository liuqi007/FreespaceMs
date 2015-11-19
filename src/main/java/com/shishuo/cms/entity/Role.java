package com.shishuo.cms.entity;

import java.util.Date;

/**
 * 角色实体类
 * 
 * @author liuqi
 * 
 */

public class Role {

	/**
	 * 角色Id
	 */
	private long roleId;

	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 是否启用  0:启用   1:不启用
	 */
	private int isUse;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
