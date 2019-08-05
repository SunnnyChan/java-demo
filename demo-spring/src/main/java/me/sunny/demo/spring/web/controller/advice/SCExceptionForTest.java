package me.sunny.demo.spring.web.controller.advice;

public class SCExceptionForTest extends RuntimeException {

    private String msg;

    public SCExceptionForTest(String msg) {
        super(msg);
        this.msg = msg;
    }

    public SCExceptionForTest(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
