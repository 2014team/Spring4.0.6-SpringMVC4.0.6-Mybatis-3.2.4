package com.ssm.base.domain;

import java.io.Serializable;

public class BaseDO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

}
