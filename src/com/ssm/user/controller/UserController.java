package com.ssm.user.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.common.JsonResult;
import com.ssm.user.entity.User;
import com.ssm.user.service.UserService;
import com.ssm.user.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	 
	@Autowired
	UserService userService;
	
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(UserVO userVO){
		JsonResult result = new JsonResult();
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		userService.save(user);
		if(user.getId()>0){
			result.success();
			return result;
		}
		result.failure();
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResult delete(Integer id){
		JsonResult result = new JsonResult();
		int index = userService.delete(id);
		if(index>0){
			result.success();
			return result;
		}
		result.failure();
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public JsonResult update(UserVO userVO){
		JsonResult result = new JsonResult();
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		int index = userService.update(user);
		if(index>0){
			result.success();
			return result;
		}
		result.failure();
		return result;
	}
	
	@RequestMapping("/select")
	@ResponseBody
	public JsonResult select(){
		JsonResult result = new JsonResult();
		List<User> userList = userService.select(null);
		result.success(userList);
		return result;
	}
	
}
