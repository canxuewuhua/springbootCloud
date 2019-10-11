package com.spring.cloud.controller;

import com.spring.cloud.common.ResultDTO;
import com.spring.cloud.domain.UserVO;
import com.spring.cloud.domain.UserVOExample;
import com.spring.cloud.mapper.UserDAO;
import com.spring.cloud.service.UserService;
import com.spring.cloud.util.ResultUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yongqiang.zhu
 */
@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService userService;
	/**
	 *  用户信息
	 * @return
	 */
	@ApiOperation(value="用户信息", notes="获取用户信息")
	@RequestMapping(name = "获取用户信息", path = "/getUserName",method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO getUserName(){
		ResultDTO resultDTO = new ResultDTO();


		List<UserVO> userVOS = userService.getUserList();
		resultDTO.setCode(100);
		resultDTO.setMessage("成功！");
		resultDTO.setData(userVOS);
		return resultDTO;
	}
}
