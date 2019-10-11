package com.springcloud.controller;

import com.springcloud.common.ResultDTO;
import com.springcloud.util.ResultUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongqiang.zhu
 */
@RestController
@RequestMapping("/user")
public class UserController {

	/**
	 *  用户信息
	 * @return
	 */
	@ApiOperation(value="用户信息", notes="获取用户信息")
	@RequestMapping(name = "获取用户信息", path = "/getUserName",method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO getUserName(){
		ResultDTO resultDTO = new ResultDTO();
		return ResultUtils.success();
	}
}
