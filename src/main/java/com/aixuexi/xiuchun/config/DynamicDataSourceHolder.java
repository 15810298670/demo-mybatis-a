package com.aixuexi.xiuchun.config;

/**
 * create on 20/4/3 下午1:48 by jc
 **/
public class DynamicDataSourceHolder {
    /**
     * 主数据库标识
     */
    public static final String MASTER = "master";

    /**
     * 从数据库标识
     */
    public static final String SLAVE = "slave";

    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    private DynamicDataSourceHolder() {
        //
    }

    public static void putDataSource(String key) {
        holder.set(key);
    }

    public static String getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }

    public static boolean isMaster(){
        if(holder.get() == null){
            return true;
        }
        return holder.get().equals(MASTER);
    }


}
