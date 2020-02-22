package com.springcloud.mapper;

import com.springcloud.domain.DeptVO;
import com.springcloud.domain.DeptVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptDAO {
    long countByExample(DeptVOExample example);

    int deleteByExample(DeptVOExample example);

    int deleteByPrimaryKey(Integer deptno);

    int insert(DeptVO record);

    int insertSelective(DeptVO record);

    List<DeptVO> selectByExample(DeptVOExample example);

    DeptVO selectByPrimaryKey(Integer deptno);

    int updateByExampleSelective(@Param("record") DeptVO record, @Param("example") DeptVOExample example);

    int updateByExample(@Param("record") DeptVO record, @Param("example") DeptVOExample example);

    int updateByPrimaryKeySelective(DeptVO record);

    int updateByPrimaryKey(DeptVO record);
}