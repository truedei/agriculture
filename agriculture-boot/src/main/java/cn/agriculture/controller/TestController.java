package cn.agriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/zhenghui/")
public class TestController {


    @GetMapping("login")
    @ResponseBody
    public String post(String name){
        System.out.println("登录了,账号："+name);
        return "登录了,账号："+name;
    }

}
