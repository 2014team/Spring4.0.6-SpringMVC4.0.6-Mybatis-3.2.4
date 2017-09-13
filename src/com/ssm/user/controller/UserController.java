package com.ssm.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.common.JsonResult;
import com.ssm.user.entity.User;
import com.ssm.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	 
	@Autowired
	UserService userService;
	
	@RequestMapping("/list")
	@ResponseBody
	public JsonResult list(){
		JsonResult result = new JsonResult();
		List<User> userList = userService.select(null);
		result.success(userList);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(){
		User user = new User();
		user.setName("zhangsan");
		user.setComeFrom("Andriod");
		user.setPassWord("aaaa");
		userService.save(user);
		return null;
	}
}
