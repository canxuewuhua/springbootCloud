package com.kuang.service;

import com.kuang.common.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yongqiang.zhu
 * @date 2020/2/23 18:35
 */
@Component
@FeignClient(value = "MICROSERVICE-PROVIDER-USER", fallbackFactory = DeptClientServiceFallBackFactory.class)
public interface DeptClientService {

	@GetMapping("/provider/getUserName")
	public ResultDTO getUserName();

}
