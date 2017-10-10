package com.ssm.user.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.common.JsonResult;
import com.ssm.user.domain.UserDO;
import com.ssm.user.dto.UserDTO;
import com.ssm.user.service.UserService;
import com.ssm.user.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	 
	@Autowired
	UserService userService;
	
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(UserDTO UserDTO){
		JsonResult result = new JsonResult();
		UserDO userDO = new UserDO();
		BeanUtils.copyProperties(UserDTO, userDO);
		try {
			//userService.save(tuserDO);
			//为了测试事物
			userService.saveUser(userDO);
			if(userDO.getId()>0){
				result.success();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.failure();
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
		UserDO user = new UserDO();
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
		List<UserDO> userList = userService.select(null);
		result.success(userList);
		return result;
	}
	
}
