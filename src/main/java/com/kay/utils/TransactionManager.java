package com.kay.utils;

/**
 * 和事务管理相关的工具类，它包括，开启事务，提交事务，回滚事务和释放事务
 */
public class TransactionManager {

    //当前线程上的connection的获取
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    /**
     * 提交事务
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    /**
     * 回滚事务
     */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    /**
     * 释放连接
     */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();//把线程还回线程池中
            connectionUtils.removeConnection();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



}
