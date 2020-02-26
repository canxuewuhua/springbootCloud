package com.kuang.service;

import com.kuang.common.ResultDTO;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author yongqiang.zhu
 * @date 2020/2/26 10:23
 *
 * 服务降级
 */
@Component
public class DeptClientServiceFallBackFactory implements FallbackFactory{
	public Object create(Throwable throwable) {
		return new DeptClientService() {
			public ResultDTO getUserName() {
				ResultDTO resultDTO = new ResultDTO();
				resultDTO.setCode(10000);
				resultDTO.setMessage("没有对应的信息，客户端提供了降级的信息，这个服务现在已经被关闭");
				return resultDTO;
			}
		};
	}
}
