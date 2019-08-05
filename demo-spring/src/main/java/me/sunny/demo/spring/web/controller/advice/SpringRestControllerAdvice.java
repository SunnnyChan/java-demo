package me.sunny.demo.spring.web.controller.advice;

import java.util.HashMap;
import me.sunny.demo.spring.web.controller.RestAdviceTestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = RestAdviceTestController.class)
public class SpringRestControllerAdvice {
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("binder.getFieldDefaultPrefix : " + binder.getFieldDefaultPrefix());
        System.out.println("binder.getFieldMarkerPrefix : "+ binder.getFieldMarkerPrefix());
    }
    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "harry");
    }
    /**
     * Description : 全局异常捕捉处理
     * Group :
     *
     * @author honghh
     * @date  2019/4/1 0001 10:34
     * @param ex
     * @return
     */
    @ExceptionHandler(RRExceptionForTest.class)
    public HashMap apiExceptionHandler(RRExceptionForTest ex) {
        System.out.println("ApiException 异常抛出：" + ex);
        HashMap<String, String> ret = new HashMap<>();
        ret.put("errorCode", new Integer(ex.getCode()).toString());
        ret.put("errorMsg", ex.getMsg());
        return ret;
    }
}
