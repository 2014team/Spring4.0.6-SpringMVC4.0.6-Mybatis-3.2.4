package com.ssm.wechat.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.ssm.wechat.bean.AccessToken;
import com.ssm.wechat.bean.JSApiTicket;

import net.sf.json.JSONObject;


/**
 * 公众平台通用接口工具类
 */
public class WeixinUtil {
	
	// 获取access_token的接口地址（GET） 限200（次/天） 
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	// 获取JS-SDK权限凭证 接口地址（GET） 限200（次/天） 
	public final static String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	/** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */ 
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null; 
        StringBuffer buffer = new StringBuffer(); 
        try { 
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化 
            TrustManager[] tm = { new MyX509TrustManager() }; 
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE"); 
            sslContext.init(null, tm, new java.security.SecureRandom()); 
            // 从上述SSLContext对象中得到SSLSocketFactory对象 
            SSLSocketFactory ssf = sslContext.getSocketFactory(); 
 
            URL url = new URL(requestUrl); 
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection(); 
            httpUrlConn.setSSLSocketFactory(ssf); 
 
            httpUrlConn.setDoOutput(true); 
            httpUrlConn.setDoInput(true); 
            httpUrlConn.setUseCaches(false); 
            // 设置请求方式（GET/POST） 
            httpUrlConn.setRequestMethod(requestMethod); 
 
            if ("GET".equalsIgnoreCase(requestMethod)) 
                httpUrlConn.connect(); 
 
            // 当有数据需要提交时 
            if (null != outputStr) { 
                OutputStream outputStream = httpUrlConn.getOutputStream(); 
                // 注意编码格式，防止中文乱码 
                outputStream.write(outputStr.getBytes("UTF-8")); 
                outputStream.close(); 
            } 
 
            // 将返回的输入流转换成字符串 
            InputStream inputStream = httpUrlConn.getInputStream(); 
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8"); 
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 
 
            String str = null; 
            while ((str = bufferedReader.readLine()) != null) { 
                buffer.append(str); 
            } 
            bufferedReader.close(); 
            inputStreamReader.close(); 
            // 释放资源 
            inputStream.close(); 
            inputStream = null; 
            httpUrlConn.disconnect(); 
            jsonObject = JSONObject.fromObject(buffer.toString()); 
        } catch (ConnectException ce) { 
        	ce.printStackTrace();
        } catch (Exception e) { 
        	e.printStackTrace();
        } 
        return jsonObject; 
    }
	
	
    /** 
     * 获取access_token 
     *  
     * @param appid 凭证 
     * @param appsecret 密钥 
     * @return 
     */ 
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null; 
     
        String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret); 
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null); 
        // 如果请求成功 
        if (null != jsonObject) {
            try { 
                accessToken = new AccessToken(); 
                accessToken.setToken(jsonObject.getString("access_token")); 
                accessToken.setExpiresIn(jsonObject.getInt("expires_in")); 
            } catch (Exception e) { 
                accessToken = null; 
            } 
        } 
        return accessToken; 
    }
	
	
    /**
     * 获取JS-SDK权限凭证
     * @param accessToken
     * @return
     */
    public static JSApiTicket getJSApiTicket(String accessToken) {
    	JSApiTicket jsApiTicket = null; 
     
        String requestUrl = JSAPI_TICKET_URL.replace("ACCESS_TOKEN", accessToken); 
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null); 
        // 如果请求成功 
        if (null != jsonObject) { 
            try { 
            	jsApiTicket = new JSApiTicket(); 
            	jsApiTicket.setTicket(jsonObject.getString("ticket")); 
            	jsApiTicket.setExpiresIn(jsonObject.getInt("expires_in")); 
            } catch (Exception e) {
            	jsApiTicket = null; 
            } 
        } 
        return jsApiTicket; 
    }
	
}