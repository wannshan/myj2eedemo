package cn.wannshan.test;

import cn.wannshan.j2ee.ws.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

/**
 *
 * Created by  on
 */
@ContextConfiguration(locations={
        "classpath:spring/spring.xml",
        "classpath:spring/spring-redis.xml"})
public class MyTest extends AbstractTestNGSpringContextTests {

    @Autowired
    WebServiceTemplate webServiceTemplate;

    @Test
    public void testWebservice() throws JAXBException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        //这是个包名，是你利用maven插件根据xsd文件生成pojo类，存放包名
        marshaller.setContextPath("cn.wannshan.j2ee.ws.dto");
        //指定Jaxb方案实现类。spring提供Jaxb2Marshaller
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);

        //查询Country
        GetCountryRequest getCountryRequest=new GetCountryRequest();
        getCountryRequest.setName("Spain");
        GetCountryResponse getCountryResponse= (GetCountryResponse) webServiceTemplate.marshalSendAndReceive(getCountryRequest);
        Assert.assertEquals(getCountryResponse.getCountry().getName(), "Spain");

        //保存Country
        Country country=new Country();
        country.setName("中国");
        country.setCapital("北京");
        country.setPopulation(1400000000);
        SaveCountryRequest saveCountryRequest=new SaveCountryRequest();
        saveCountryRequest.setCountry(country);
        SaveCountryResponse saveCountryResponse= (SaveCountryResponse) webServiceTemplate.marshalSendAndReceive(saveCountryRequest);
        Assert.assertEquals(saveCountryResponse.getName(), "中国");
    }
}
