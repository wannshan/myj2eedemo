package cn.wannshan.j2ee.ws.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * �ļ����ܣ�xxxx
 * Created by  on 2018/1/23.
 */
@XmlRootElement(name = "getCountryResponse")
public class GetCountryResponse {

    private Country country;

    @XmlElement(name = "country" ,namespace="http://j2eeweb.wannshan.cn/hr/schemas")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
