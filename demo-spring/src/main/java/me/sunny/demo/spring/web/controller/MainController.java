package me.sunny.demo.spring.web.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
public class MainController {

    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public String index() {
        HashMap<Integer, String> response = new HashMap<Integer, String>();
        response.put(1, "Hello World");
        return JSON.toJSONString(response);
    }
}
