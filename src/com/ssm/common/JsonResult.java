package com.ssm.common;

import com.ssm.common.utils.GsonUtil;


public class JsonResult {
	/**
	 * 返回编码
	 */
	private String code;
	/**
	 * 返回消息
	 */
	private String message;
	
	/**
	 * 业务数据 
	 */
	private Object data;
	
	public JsonResult(){
		super();
	}
	
	public JsonResult(ResultCode resultCode, Object data) {
		super();
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
		this.data = data;
	}
	
	/**
	 * 成功
	 * @param data
	 */
	public void success(){
		setResultCode(ResultCode.E200);
	}
	
	/**
	 * 成功
	 * @param data
	 */
	public void success(Object data){
		setResultCode(ResultCode.E200);
		setData(data);
	}
	
	/**
	 * 失败
	 * @param data
	 */
	public void failure(){
		setResultCode(ResultCode.E500);
	}
	
	/**
	 * 失败
	 * @param data
	 */
	public void failure(Object data){
		setResultCode(ResultCode.E500);
		setData(data);
	}
	
	public void setResultCode(ResultCode resultCode){
		this.code = resultCode.getCode();
		this.message = resultCode.getMessage();
	}
	
	public void setResultCode(ResultCode resultCode, Object data){
		setResultCode(resultCode);
		this.data = data;
	}
	
	/**
	 * 转换成json字符串
	 */
	public String toJsonStr(){
		return GsonUtil.toJsonAll(this);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
