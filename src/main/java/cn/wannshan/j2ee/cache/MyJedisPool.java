package cn.wannshan.j2ee.cache;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

/**
 * 文件功能：xxxx
 * Created by yufei on 2017/8/23.
 */
public class MyJedisPool extends JedisPool {
    private  String nodeName;
    public MyJedisPool(final GenericObjectPoolConfig poolConfig, final String host, int port,final String nodeName){
        super(poolConfig,host,port);
        this.nodeName=nodeName;
    }
    public String getNodeName() {
        return nodeName;
    }
}
