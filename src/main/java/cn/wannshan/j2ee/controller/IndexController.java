package cn.wannshan.j2ee.controller;

import cn.wannshan.j2ee.cache.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by JD on 2017/8/17.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String hello(){
        System.out.println("hello world");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/testCache")
    public String testCache(){
        for(int i=1;i<100000;i++){
            String key=String.valueOf(i);
            RedisUtil redisUtil=new RedisUtil();
            redisUtil.selectRedisPool(key);
            redisUtil.setValue(key,"test"+i);
        }
        return "redirect:/index.jsp";
    }
}
