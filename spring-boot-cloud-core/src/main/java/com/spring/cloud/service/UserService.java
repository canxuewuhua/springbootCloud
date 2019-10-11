package com.spring.cloud.service;

import com.spring.cloud.domain.UserVO;
import com.spring.cloud.domain.UserVOExample;
import com.spring.cloud.mapper.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yongqiang.zhu
 * @date 2019/10/5 17:04
 */
@Service
@Transactional
@Slf4j
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public List<UserVO> getUserList(){
		UserVOExample userVOExample =new UserVOExample();
		List<UserVO> userVOS = userDAO.selectByExample(userVOExample);
		return userVOS;
	}
}
