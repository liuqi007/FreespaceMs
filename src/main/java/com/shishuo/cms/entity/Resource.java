package com.shishuo.cms.entity;

import java.util.Date;

public class Resource {
	private long resId;
	private long parentResId;
	private String name;
	private String url;
	private String iconcss;
	private int type;
	private int isUse;
	private Date createTime;
	private Date updateTime;
	private int sort;
	public long getResId() {
		return resId;
	}

	public void setResId(long resId) {
		this.resId = resId;
	}

	public long getParentResId() {
		return parentResId;
	}

	public void setParentResId(long parentResId) {
		this.parentResId = parentResId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getIconcss() {
		return iconcss;
	}

	public void setIconcss(String iconcss) {
		this.iconcss = iconcss;
	}
	
}
