package cn.wannshan.j2ee.ws;

import cn.wannshan.j2ee.ws.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
/**
 * 文件功能：xxxx
 * Created by  on 2018/1/22.
 */
@Endpoint
public class CountryQuery {

    private static final String NAMESPACE_URI = "http://j2eeweb.wannshan.cn/hr/schemas";

    @Autowired
    CountryRepository countryRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse queryCountry(@RequestPayload GetCountryRequest request) throws Exception {
        GetCountryResponse response = new GetCountryResponse();
//        Country country=new Country();
//        country.setName("中国");
//        country.setCapital("北京");
//        country.setCurrency("人民币");
//        country.setPopulation(1400000000);
//        response.setCountry(country);
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveCountryRequest")
    @ResponsePayload
    public SaveCountryResponse saveCountry(@RequestPayload SaveCountryRequest request) throws Exception {
        SaveCountryResponse response = new SaveCountryResponse();
        response.setName("china");
        return response;
    }

}
