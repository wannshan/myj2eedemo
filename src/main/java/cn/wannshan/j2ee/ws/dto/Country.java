package cn.wannshan.j2ee.ws.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * 文件功能：xxxx
 * Created by  on 2018/1/23.
 */
public class Country {

    private String name;
    private Integer population;
    private String capital;
    private String currency;

    @XmlElement(name = "name",namespace="http://j2eeweb.wannshan.cn/hr/schemas")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "population",namespace="http://j2eeweb.wannshan.cn/hr/schemas")
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @XmlElement(name = "capital",namespace="http://j2eeweb.wannshan.cn/hr/schemas")
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @XmlElement(name = "currency",namespace="http://j2eeweb.wannshan.cn/hr/schemas")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
