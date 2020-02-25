package com.springcloud.controller;

import com.kuang.common.ResultDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.domain.DeptVO;
import com.springcloud.domain.DeptVOExample;
import com.springcloud.mapper.DeptDAO;
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
@RequestMapping("/provider")
public class UserController {

	@Autowired
	private DeptDAO deptDAO;

	/**
	 *  用户信息
	 * @return
	 */
	@ApiOperation(value="用户信息", notes="获取用户信息")
	@RequestMapping(name = "获取用户信息", path = "/getUserName",method = RequestMethod.GET)
	@ResponseBody
	@HystrixCommand(fallbackMethod = "hystrixGet")
	public ResultDTO getUserName(){
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(10001);
		resultDTO.setMessage("成功！");

		DeptVOExample deptVOExample = new DeptVOExample();
		List<DeptVO> deptVOS = deptDAO.selectByExample(deptVOExample);

		resultDTO.setData(deptVOS);
		if (deptVOS.size() > 0){
			throw new RuntimeException("此处抛出的是一个异常信息");
		}

		return resultDTO;
	}

	public ResultDTO hystrixGet(){
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(200);
		resultDTO.setMessage("成功！");
		resultDTO.setData("SUCCESS");
		return resultDTO;
	}
}
