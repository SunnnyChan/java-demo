package me.sunny.demo.spring.web.controller.advice;

import java.util.Arrays;
import java.util.HashMap;

import me.sunny.demo.spring.web.controller.ControllerAdviceTestController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackageClasses = ControllerAdviceTestController.class)
public class SpringControllerAdvice {

    @ExceptionHandler(SCExceptionForTest.class)
    @ResponseBody
    public HashMap<String, String> runtimeException(RuntimeException ex) {
        Arrays.stream(ex.getStackTrace()).forEach(r -> System.out.println(r.toString()));
        HashMap<String, String> ret = new HashMap<>();
        ret.put("errorMsg", ex.getMessage());
        return ret;
    }
}
