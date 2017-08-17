package cn.wannshan.j2ee.controller;

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
}
