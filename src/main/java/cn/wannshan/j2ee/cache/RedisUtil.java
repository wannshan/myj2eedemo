package cn.wannshan.j2ee.cache;

import cn.wannshan.j2ee.common.pojo.CacheHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件功能：xxxx
 * Created by yufei on 2017/8/23.
 */
@Component
public class RedisUtil{

    @Autowired
    private static Map<String,MyJedisPool> jedisPoolMap;
    private JedisPool currentJedisPool;
    private  Jedis Jedis;
    private  static  List<CacheHost> hostList=new ArrayList<CacheHost>();
    private static  Shard<CacheHost> shard;
    static {
        for(String key:jedisPoolMap.keySet()){
            hostList.add(new CacheHost(jedisPoolMap.get(key).getNodeName(),"",0));
        }
        shard=new Shard(RedisUtil.hostList);
    }

    /***
     * 获取连接,@TODO做个连接池
     */
    public void selectRedisPool(String key){
        CacheHost cacheHost= RedisUtil.shard.getShardInfo(key);
        currentJedisPool=jedisPoolMap.get(cacheHost.getName());
        try{
        Jedis.close();
        Jedis=currentJedisPool.getResource();
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    public void setValue(String key,String value){
        Jedis.set(key,value);
    }

    public String getValue(String key){
        return Jedis.get(key);
    }
}
