package com.ssm.user.domain;

import java.util.Date;

import com.ssm.base.domain.BaseDO;

public class UserDO extends BaseDO {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String comeFrom;
	private Date createDate;
	private Date updateDate;

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

	public String getComeFrom() {
		return comeFrom;
	}

	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
