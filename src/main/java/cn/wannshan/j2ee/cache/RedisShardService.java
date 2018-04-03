package cn.wannshan.j2ee.cache;

import cn.wannshan.j2ee.common.pojo.CacheHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by yufei on 2017/8/23.
 */
@Service
public class RedisShardService {

    @Autowired
    private  Map<String,MyJedisPool> jedisPoolMap;

    public Map<String, MyJedisPool> getJedisPoolMap() {
        return jedisPoolMap;
    }

    public void setJedisPoolMap(Map<String, MyJedisPool> jedisPoolMap) {
        this.jedisPoolMap = jedisPoolMap;
    }

    private JedisPool currentJedisPool=null;
    private  Jedis Jedis=null;
    private  static  List<CacheHost> hostList=new ArrayList<CacheHost>();
    private static  Shard<CacheHost> shard=null;

    private void init(){
        if(shard==null) {
            for (String key : getJedisPoolMap().keySet()) {
                hostList.add(new CacheHost(getJedisPoolMap().get(key).getNodeName(), key,"", 0));
            }
            shard = new Shard(RedisShardService.hostList);
        }
    }

    /***
     *
     */
    public void selectRedisPool(String key){
        init();
        CacheHost cacheHost= RedisShardService.shard.getShardInfo(key);
        currentJedisPool=jedisPoolMap.get(cacheHost.getHashName());

        try{
        if(Jedis!=null)
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
