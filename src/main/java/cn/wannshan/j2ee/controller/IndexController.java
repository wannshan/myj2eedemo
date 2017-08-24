package cn.wannshan.j2ee.controller;

import cn.wannshan.j2ee.cache.RedisShardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by JD on 2017/8/17.
 */
@Controller
public class IndexController {
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
}
