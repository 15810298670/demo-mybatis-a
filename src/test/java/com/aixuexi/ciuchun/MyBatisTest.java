package com.aixuexi.ciuchun;


import com.aixuexi.xiuchun.DemoApplication;
import com.aixuexi.xiuchun.dao.TestMybatisDao;
import com.aixuexi.xiuchun.entity.TestMybatis;
import com.aixuexi.xiuchun.service.TestMybatisService;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * create on 20/3/31 下午7:23 by jc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MyBatisTest {

    @Resource
    private TestMybatisDao testMybatisDao;

    @Resource
    private TestMybatisService testMybatisService;

    /**
     * service 方法标记从库，只查从库,所有从库轮训
     */
    @Test
    public void testQuery(){

        List<TestMybatis> list = testMybatisService.query();
        System.out.println("test mybatis list :" + JSONArray.toJSONString(list));

        List<TestMybatis> list1 = testMybatisService.query();
        System.out.println("test mybatis list :" + JSONArray.toJSONString(list1));

        List<TestMybatis> list2 = testMybatisService.query();
        System.out.println("test mybatis list :" + JSONArray.toJSONString(list2));

    }

    /**
     * 方法无任何标记查主库（两种方案：a.查主库， b. 主库和所有从库轮训）
     */
    @Test
    public void testQuery2(){

        List<TestMybatis> list = testMybatisService.queryMaster();
        System.out.println("test mybatis list :" + JSONArray.toJSONString(list));


    }


    /**
     * 更新无事务
     */
    @Test
    public void testUpdate(){

        TestMybatis testMybatis = new TestMybatis();
        testMybatis.setId(1);
        testMybatis.setName("wtwyiue");

        TestMybatis testMybatis2 = new TestMybatis();
        testMybatis2.setId(2);
        testMybatis2.setCode("test update codetest update codetest update codetest update codetest update codetest update code");
        testMybatisService.update(testMybatis, testMybatis2);

    }


    /**
     * 更新有事务
     */
    @Test
    public void testUpdateTx(){

        TestMybatis testMybatis = new TestMybatis();
        testMybatis.setId(1);
        testMybatis.setName("wwwww");

        TestMybatis testMybatis2 = new TestMybatis();
        testMybatis2.setId(2);
        testMybatis2.setCode("test update codetest update codetest update codetest update codetest update codetest update code");
        testMybatisService.updateTx(testMybatis, testMybatis2);

    }










//    @Autowired
//    private MyJdbcTemplate myJdbcTemplate;
//
//    @Test
//    public void testQuery(){
//
//        List<TestMybatis> list = myJdbcTemplate.query("select * from test_mybatis",new BeanPropertyRowMapper<>(TestMybatis.class));
//        System.out.println("test mybatis list :" + JSON.toJSONString(list));
//
//    }
//
//
//    @Test
//    public void testInsert(){
//
//        Object[] paramList = new Object[]{"aaa","bbb"};
//        int n = myJdbcTemplate.update("insert into test_mybatis (name, code)values(?, ?)",paramList);
//        System.out.println("test mybatis insert :" + n);
//
//    }
}


