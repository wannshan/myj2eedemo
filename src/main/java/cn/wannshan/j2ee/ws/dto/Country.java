package cn.wannshan.j2ee.ws.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "country",
        propOrder = {"name", "population", "capital"}
)
public class Country {
    @XmlElement(
            required = true
    )
    protected String name;
    protected int population;
    @XmlElement(
            required = true
    )
    protected String capital;

    public Country() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public int getPopulation() {
        return this.population;
    }

    public void setPopulation(int value) {
        this.population = value;
    }

    public String getCapital() {
        return this.capital;
    }

    public void setCapital(String value) {
        this.capital = value;
    }
}