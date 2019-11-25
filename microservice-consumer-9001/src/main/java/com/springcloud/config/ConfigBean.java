package com.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yongqiang.zhu
 * @date 2019/11/26 0:13
 */
@Configuration
public class ConfigBean {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
