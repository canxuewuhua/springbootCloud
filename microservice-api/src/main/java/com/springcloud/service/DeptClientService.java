package com.springcloud.service;

import com.springcloud.common.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yongqiang.zhu
 * @date 2020/2/23 18:35
 */
@FeignClient(value = "MICROSERVICE-PROVIDER-USER")
public interface DeptClientService {

	@GetMapping("/provider/getUserName")
	public ResultDTO getUserName();


}
