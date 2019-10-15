package com.springcloud.controller;

import com.springcloud.common.ResultDTO;
import com.springcloud.util.ResultUtils;
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
@RequestMapping("/user")
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
		System.out.println("discoveryClient.getServices().size() = " + discoveryClient.getServices().size());
		for (String s : discoveryClient.getServices()) {
			System.out.println("services " + s);
			List<ServiceInstance> serviceInstances = discoveryClient.getInstances(s);
			for (ServiceInstance si : serviceInstances) {
				System.out.println("    services:" + s + ":getHost()=" + si.getHost());
				System.out.println("    services:" + s + ":getPort()=" + si.getPort());
				System.out.println("    services:" + s + ":getServiceId()=" + si.getServiceId());
				System.out.println("    services:" + s + ":getUri()=" + si.getUri());
				System.out.println("    services:" + s + ":getMetadata()=" + si.getMetadata());
			}
		}
		return ResultUtils.success();
	}
}
