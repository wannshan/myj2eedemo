package cn.wannshan.j2ee.common.pojo;

/**
 *
 * Created by  on 2017/8/23.
 */
public class CacheHost {
    private String name;
    private String hashName;
    private String ip;
    private Integer port;

    public CacheHost(String name, String hashName,String ip, Integer port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.hashName=hashName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }
}