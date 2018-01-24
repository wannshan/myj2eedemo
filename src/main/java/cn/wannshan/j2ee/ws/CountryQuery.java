package cn.wannshan.j2ee.ws;

import cn.wannshan.j2ee.ws.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
/**
 * xxx
 * Created by  on 2018/1/22.
 */
@Endpoint
public class CountryQuery {

    private static final String NAMESPACE_URI = "http://j2eeweb.wannshan.cn/hr/schemas";

    @Autowired
    CountryRepository countryRepository;

    /***
     * 查询country
     * @param request
     * @return
     * @throws Exception
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse queryCountry(@RequestPayload GetCountryRequest request) throws Exception {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }

    /**
     * 保存country
     * @param request
     * @return
     * @throws Exception
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveCountryRequest")
    @ResponsePayload
    public SaveCountryResponse saveCountry(@RequestPayload SaveCountryRequest request) throws Exception {
        SaveCountryResponse response = new SaveCountryResponse();
        countryRepository.putCounttry(request.getCountry());
        response.setName(request.getCountry().getName());
        return response;
    }

}
