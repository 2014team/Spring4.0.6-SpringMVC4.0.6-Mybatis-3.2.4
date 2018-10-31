package com.ssm.wechat.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.wechat.bean.JSApiTicket;
import com.ssm.wechat.util.Sign;

@Controller
public class ShareController {

	@RequestMapping("/weixin/share")
	public ModelAndView getPositionInfoDetail(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("/weixin/share");

		JSApiTicket jsApiTicket = TokenThread.jsApiTicket;
		String jsapi_ticket = jsApiTicket.getTicket();// JS-SDK权限凭证
		String appId = TokenThread.appid; // appId
		String url = "http://1d6e11fc.ngrok.io/weixin/share";

		Map<String, String> map = Sign.sign(jsapi_ticket, url); // 签名
		String timestamp = ""; // 时间戳
		String nonceStr = ""; // 随机字符串
		String signature = ""; // 最后签名
		if (null != map) {
			timestamp = map.get("timestamp");
			nonceStr = map.get("nonceStr");
			signature = map.get("signature");
			System.out.println("signature = " + signature);
		}

		String title = "test_title"; // 分享标题
		String desc = "test_description"; // 分享描述
		String link = url; // 分享链接
		String imgUrl = ""; // 分享图标

		mv.addObject("appId", appId);
		mv.addObject("timestamp", timestamp);
		mv.addObject("nonceStr", nonceStr);
		mv.addObject("signature", signature);
		mv.addObject("url", url);
		mv.addObject("title", title);
		mv.addObject("desc", desc);
		mv.addObject("link", link);
		mv.addObject("imgUrl", imgUrl);
		return mv;
	}

}
