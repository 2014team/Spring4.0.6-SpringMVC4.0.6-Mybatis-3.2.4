package com.ssm.wechat.controller;

import com.ssm.wechat.bean.AccessToken;
import com.ssm.wechat.bean.JSApiTicket;
import com.ssm.wechat.util.WeixinUtil;

/** 
 * 定时获取微信access_token的线程 
 *  
 */  
public class TokenThread implements Runnable {
	
    public static String appid = "wx5ddac47164724834";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "33c48adf57e2181188053139ece23d2a";
    
    public static AccessToken accessToken = null;
    
    public static JSApiTicket jsApiTicket = null;
  
    
    public void run() {
        while (true) {
            try {
                accessToken = WeixinUtil.getAccessToken(appid, appsecret);  
                if (null != accessToken) {
                    System.out.println("获取access_token成功，有效时长"+ accessToken.getExpiresIn()+"秒 token:"+ accessToken.getToken());
                    
                    // 获取成功之后，然后紧接着获取 调用 JS-SDK权限凭证
                    String accessTokenStr = accessToken.getToken();
                    if(!"".equals(accessTokenStr)){
                    	jsApiTicket = WeixinUtil.getJSApiTicket(accessTokenStr);
                    	if(null != jsApiTicket){
                    		System.out.println("获取jsApiTicket成功，有效时长"+ jsApiTicket.getExpiresIn()+"秒 token:"+ jsApiTicket.getTicket());
                    	}
                    }
                    // 休眠7000秒  
                    Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);  
                } else {
                    // 如果access_token为null，60秒后再获取 ,有限制，不能太频繁
                    Thread.sleep(60 * 1000);  
                }  
            } catch (InterruptedException e) {
                try {  
                    Thread.sleep(60 * 1000);  
                } catch (InterruptedException e1) {
                	  System.out.println(e1);  
                }  
                System.out.println(e);  
            }  
        }  
    }  






} 







