package cn.wannshan.j2ee.common.pojo;

/**
 * 文件功能：xxxx
 * Created by yufei on 2017/8/23.
 */
public class CacheHost {
    private String name;
    private String ip;
    private Integer port;

    public CacheHost(String name, String ip, Integer port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
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
}