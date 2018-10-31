package com.ssm.interceptor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.ssm.wechat.controller.TokenThread;


/**
 * 项目初始好监听器 用于启动时，初始一些字典数据 或 启动任务调度服务
 *
 */
@Component
public class InitListener implements InitializingBean{

	
	/**
	 * 容器初始化完成后执行
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		
		//启动获取微信分享参数线程
		new Thread(new TokenThread()).start();
		
	}
    
}
