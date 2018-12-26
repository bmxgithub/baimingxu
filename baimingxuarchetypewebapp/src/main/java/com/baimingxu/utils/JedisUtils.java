package com.baimingxu.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtils {
    private static  JedisPool pool =null;

    //创建连接池
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("jedis");
        String maxTotal = bundle.getString("maxTotal");
        String maxIdle = bundle.getString("maxIdle");
        String url = bundle.getString("url");
        String port = bundle.getString("port");
        //创建连接池对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(Integer.parseInt(maxTotal));
        //设置最大空闲连接数
        config.setMaxIdle(Integer.parseInt(maxIdle));
        //创建连接池
        pool = new JedisPool(config, url, Integer.parseInt(port));
    }

    //对外提供一个方法,能够获取连接
    public static Jedis getJedis(){
        return pool.getResource();
    }

    //对外提供一个方法,释放资源
    public static void close(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }
}
