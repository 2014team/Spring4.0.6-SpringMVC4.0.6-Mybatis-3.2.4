package com.ssm.common;

/**
 * 业务编码
 */
public enum ResultCode {
	E200("成功！"),

    E500("程序内部出错！");
	
    private final String message;

    private ResultCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return this.name();
    }

}
