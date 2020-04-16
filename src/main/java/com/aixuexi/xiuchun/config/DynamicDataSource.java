package com.aixuexi.xiuchun.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create on 20/4/3 下午1:44 by jc
 **/
public class DynamicDataSource  extends AbstractRoutingDataSource {

    public final static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    private List<Object> slaveDataSources = new ArrayList<Object>();

    private AtomicInteger squence = new AtomicInteger(0);

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Object key = "";
        //主库
        if (DynamicDataSourceHolder.isMaster()) {
            key = DynamicDataSourceHolder.MASTER;
        } else {
            //从库
            key = getSlaveKey();
        }
        logger.debug("==> select datasource key [{}]", key);
        return key;

    }

    public void setSlaveDataSources(List<Object> slaveDataSources) {
        this.slaveDataSources = slaveDataSources;
    }

    /**
     * 轮询获取从库
     *
     * @return
     */
    public Object getSlaveKey() {
        if (squence.intValue() == Integer.MAX_VALUE) {
            synchronized (squence) {
                if (squence.intValue() == Integer.MAX_VALUE) {
                    squence = new AtomicInteger(0);
                }
            }
        }
        int idx = squence.getAndIncrement() % slaveDataSources.size();
        return slaveDataSources.get(idx);
    }

}
