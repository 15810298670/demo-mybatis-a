package com.aixuexi.xiuchun.dao;

import com.aixuexi.xiuchun.entity.TestMybatis;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Repository
public interface TestMybatisDao extends BaseMapper<TestMybatis> {

    @Update("update test_mybatis set name = #{name} where id = #{id}")
    int updateName(@Param("id") int id, @Param("name") String name);


    @Update("update test_mybatis set code = #{code} where id = #{id}")
    int updateCode(@Param("id") int id, @Param("code") String code);


}
