package com.spring.cloud.mapper;

import com.spring.cloud.domain.UserVO;
import com.spring.cloud.domain.UserVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {
    long countByExample(UserVOExample example);

    int deleteByExample(UserVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserVO record);

    int insertSelective(UserVO record);

    List<UserVO> selectByExample(UserVOExample example);

    UserVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserVO record, @Param("example") UserVOExample example);

    int updateByExample(@Param("record") UserVO record, @Param("example") UserVOExample example);

    int updateByPrimaryKeySelective(UserVO record);

    int updateByPrimaryKey(UserVO record);
}