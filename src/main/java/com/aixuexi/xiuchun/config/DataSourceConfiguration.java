package com.aixuexi.xiuchun.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create on 20/4/1 下午7:45 by jc
 **/
@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "druid.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "slaveDataSource1")
    @ConfigurationProperties(prefix = "druid.slave1")
    public DataSource slaveDataSource1(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "slaveDataSource2")
    @ConfigurationProperties(prefix = "druid.slave2")
    public DataSource slaveDataSource2(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 构建动态数据源
     * @param masterDataSource
     * @param slaveDataSource1
     * @param slaveDataSource2
     * @return
     */
    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("slaveDataSource1") DataSource slaveDataSource1,
                                               @Qualifier("slaveDataSource2") DataSource slaveDataSource2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        targetDataSources.put("master", masterDataSource);
        targetDataSources.put("slave1", slaveDataSource1);
        targetDataSources.put("slave2", slaveDataSource2);
        dynamicDataSource.setTargetDataSources(targetDataSources);

        List<Object> slaveDataSources = new ArrayList<Object>();
        slaveDataSources.add("slave1");
        slaveDataSources.add("slave2");
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        dynamicDataSource.setSlaveDataSources(slaveDataSources);

        return dynamicDataSource;

    }


}
