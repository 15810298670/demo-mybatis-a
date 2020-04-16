package com.aixuexi.xiuchun.service;

import com.aixuexi.xiuchun.dao.TestMybatisDao;
import com.aixuexi.xiuchun.entity.TestMybatis;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * create on 20/4/1 下午8:41 by jc
 **/
@Service
public class TestMybatisService {

    @Resource
    private TestMybatisDao testMybatisDao;


    /**
     * 读从库
     * @return
     */
    @Transactional(readOnly = true)
    public List<TestMybatis> query(){
        return testMybatisDao.selectAll();
    }


    /**
     * 读主库
     * @return
     */
    public List<TestMybatis> queryMaster(){
        return testMybatisDao.selectAll();
    }


    /**
     * 更新主库
     * @param testMybatis
     * @param testMybatis2
     */
    public void update(TestMybatis testMybatis,TestMybatis testMybatis2){
        testMybatisDao.updateName(testMybatis.getId(),testMybatis.getName());
        testMybatisDao.updateCode(testMybatis2.getId(),testMybatis2.getCode());

    }


    /**
     * 带事务更新主库
     * @param testMybatis
     * @param testMybatis2
     */
    @Transactional
    public void updateTx(TestMybatis testMybatis, TestMybatis testMybatis2) {
        testMybatisDao.updateName(testMybatis.getId(),testMybatis.getName());
        testMybatisDao.updateCode(testMybatis2.getId(),testMybatis2.getCode());
    }

}
