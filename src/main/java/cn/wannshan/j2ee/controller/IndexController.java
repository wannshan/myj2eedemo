package cn.wannshan.j2ee.controller;

import cn.wannshan.j2ee.cache.RedisShardService;
import cn.wannshan.j2ee.common.pojo.GPSVo;
import cn.wannshan.j2ee.common.pojo.ResponseData;
import cn.wannshan.j2ee.common.pojo.User;
import cn.wannshan.j2ee.service.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JD on 2017/8/17.
 */
@Controller
public class IndexController {
    private static String rsmPostUrl = "http://rsm.chexiang.com/automonitor/getHistoryGPS.json";


    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @Autowired
    private  RedisShardService redisShardService;

    @RequestMapping("/index")
    public String hello(){
        System.out.println("hello world");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/testCache")
    public String testCache(){
        Long start=System.currentTimeMillis();
        for(int i=1;i<100000;i++){
            String key=String.valueOf(i);
            redisShardService.selectRedisPool(key);
            redisShardService.setValue(key, "test" + i);
        }
        System.out.println("save cache take:"+(System.currentTimeMillis()-start));
        return "redirect:/index.jsp";
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(@RequestParam Integer userId){
        String name=userService.getUserName(3);
       return  userService.getUserById(userId);
    }

    @RequestMapping("/register")
    public void register(@RequestParam Date birthDay){
        birthDay.toString();
    }

    @RequestMapping("/getHistoryPoint")
    @ResponseBody
    public List<GPSVo> getHistoryPoint(@RequestParam String platNum,@RequestParam String startTime,@RequestParam String endTime) throws UnsupportedEncodingException {

       return getGPS(URLEncoder.encode(platNum, "UTF-8"),startTime,endTime);
    }

    private  List<GPSVo> getGPS(String serialNumUrlEncoder, String startDate, String endDate) {
        // iDisplayLength=100 获取100条记录
        String params = "sEcho=1&iColumns=5&sColumns=%2C%2C%2C%2C&iDisplayStart=0&iDisplayLength=10000&mDataProp_0=serialNum&mDataProp_1"
                + "=lat&mDataProp_2=lng&mDataProp_3=createTime&mDataProp_4=caseId" + "&serialNum=" + serialNumUrlEncoder + "&startDate=" + startDate
                + "&endDate=" + endDate;
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(rsmPostUrl);
        try {
            RequestEntity requestEntity = new StringRequestEntity(params, "application/x-www-form-urlencoded", "UTF-8");
            postMethod.setRequestEntity(requestEntity);
            httpClient.executeMethod(postMethod);
            if (postMethod.getStatusCode() == 200) {
                ResponseData responseData = JSON.parseObject(postMethod.getResponseBodyAsString(), ResponseData.class);
                if (responseData.getAaData() != null) {
                    return responseData.getAaData();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
