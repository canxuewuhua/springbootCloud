package com.springcloud.controller;

import com.springcloud.common.ResultDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yongqiang.zhu
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private RestTemplate restTemplate;

	private static final String REST_URL_PREFIX = "http://MICROSERVICE-PROVIDER-USER";
	/**
	 *  用户信息
	 * @return
	 */
	@ApiOperation(value="用户信息", notes="获取用户信息")
	@RequestMapping(name = "获取用户信息", path = "/getUserInfo",method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO getUserInfo(){
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(100);
		resultDTO.setMessage("成功信息！");
		resultDTO.setData("用户信息");
		return resultDTO;
	}

	/**
	 *  用户信息
	 * @return
	 */
	@ApiOperation(value="用户信息", notes="获取用户信息")
	@RequestMapping(name = "获取用户信息", path = "/getUserName",method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO getUserName(){
		return restTemplate.getForObject(REST_URL_PREFIX + "/provider/getUserName",ResultDTO.class);
	}
}
