package cn.wannshan.j2ee.ws.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 文件功能：xxxx
 * Created by  on 2018/1/23.
 */
@XmlRootElement(name = "getCountryRequest", namespace="http://j2eeweb.wannshan.cn/hr/schemas")
public class GetCountryRequest {


    private  String name;

    @XmlElement(name = "name" ,namespace="http://j2eeweb.wannshan.cn/hr/schemas")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
