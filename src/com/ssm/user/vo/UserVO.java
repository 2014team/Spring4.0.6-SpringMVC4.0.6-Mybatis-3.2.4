package com.ssm.user.vo;

import com.ssm.base.entity.BaseEntity;

public class UserVO extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String comeFrom;
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

}
