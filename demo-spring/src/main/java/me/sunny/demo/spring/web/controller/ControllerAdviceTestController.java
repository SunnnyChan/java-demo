package me.sunny.demo.spring.web.controller;

import java.util.HashMap;

import me.sunny.demo.spring.web.controller.advice.SCExceptionForTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerAdviceTestController {

    @RequestMapping(value = "/detail", method= RequestMethod.GET)
    @ResponseBody()
    public HashMap detail(@RequestParam("id") Integer id) {
        if (id == null) {
            throw new SCExceptionForTest("user detail exception.");
        }
        HashMap<String, String> user = new HashMap<>();
        user.put("name", "sunny");
        user.put("id", id.toString());

        return user;
    }
}
