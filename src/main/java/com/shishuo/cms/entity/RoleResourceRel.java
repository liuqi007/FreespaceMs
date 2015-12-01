package com.shishuo.cms.entity;

import java.util.Date;

public class RoleResourceRel {
	private long resId;
	private long roleId;
	private Date createTime;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public long getResId() {
		return resId;
	}
	public void setResId(long resId) {
		this.resId = resId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
