package me.sunny.demo.spring.web.controller;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

import me.sunny.demo.spring.web.controller.advice.RRExceptionForTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAdviceTestController {

    /**
     *  测试连接：http://127.0.0.1:9090/myInfo?id=
     */
    @GetMapping("/myInfo")
    public String myInfo(@RequestParam("id") Integer id) {
        if (id == null) {
            throw new RRExceptionForTest("id 不能为空！", 110);
        }
        HashMap<String, String> user = new HashMap<>();
        user.put("name", "sunny");
        user.put("id", id.toString());
        return JSON.toJSONString(user);
    }
}
