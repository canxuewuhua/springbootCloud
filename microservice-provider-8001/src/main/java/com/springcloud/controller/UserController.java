package com.springcloud.controller;

import com.springcloud.common.ResultDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yongqiang.zhu
 */
@RestController
@RequestMapping("/provider")
public class UserController {

	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 *  用户信息
	 * @return
	 */
	@ApiOperation(value="用户信息", notes="获取用户信息")
	@RequestMapping(name = "获取用户信息", path = "/getUserName",method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO getUserName(){
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(100);
		resultDTO.setMessage("成功！");
		resultDTO.setData("用户信息");
		return resultDTO;
	}
	/**
	 *  服务发现
	 * @return
	 */
	@ApiOperation(value="服务发现", notes="服务发现")
	@RequestMapping(name = "服务发现", path = "/discovery",method = RequestMethod.GET)
	@ResponseBody
	public Object discovery(){
		List<String> services = discoveryClient.getServices();
		System.out.println("发现的服务如：" + services);
		List<ServiceInstance> list = discoveryClient.getInstances("MICROSERVICE-PROVIDER-USER");
		for (ServiceInstance serviceInstance : list){
			System.out.println(
					serviceInstance.getHost()+"\t"+
					serviceInstance.getPort()+"\t"+
					serviceInstance.getUri()+"\t"+
					serviceInstance.getServiceId()
			);
		}
		return this.discoveryClient;
	}
}
