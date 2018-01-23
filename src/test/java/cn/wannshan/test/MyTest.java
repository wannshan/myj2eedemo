package cn.wannshan.test;

import cn.wannshan.j2ee.ws.dto.GetCountryRequest;
import cn.wannshan.j2ee.ws.dto.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

/**
 * 文件功能：xxxx
 * Created by  on 2018/1/23.
 */
@ContextConfiguration(locations={
        "classpath:spring/spring.xml",
        "classpath:spring/spring-redis.xml"})
public class MyTest extends AbstractTestNGSpringContextTests {

    @Autowired
    WebServiceTemplate webServiceTemplate;

    @Test
    public void testGetPullCase() throws JAXBException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("cn.wannshan.j2ee.ws.dto");
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);

        GetCountryRequest getCountryRequest=new GetCountryRequest();
        getCountryRequest.setName("Spain");

        GetCountryResponse getCountryResponse= (GetCountryResponse) webServiceTemplate.marshalSendAndReceive(getCountryRequest);

        System.out.println(getCountryResponse.getCountry().getName());
    }
}
