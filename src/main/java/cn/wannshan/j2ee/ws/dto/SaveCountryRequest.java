//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.wannshan.j2ee.ws.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"country"}
)
@XmlRootElement(
        name = "saveCountryRequest"
)
public class SaveCountryRequest {
    @XmlElement(
            required = true
    )
    protected Country country;

    public SaveCountryRequest() {
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country value) {
        this.country = value;
    }
}
