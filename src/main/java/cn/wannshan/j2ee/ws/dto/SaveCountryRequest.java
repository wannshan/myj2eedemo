package cn.wannshan.j2ee.ws.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 文件功能：xxxx
 * Created by  on 2018/1/23.
 */
@XmlRootElement(name = "saveCountryRequest" ,namespace="http://j2eeweb.wannshan.cn/hr/schemas")
public class SaveCountryRequest {


    private Country country;

    @XmlElement(name = "country" ,namespace="http://j2eeweb.wannshan.cn/hr/schemas")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
