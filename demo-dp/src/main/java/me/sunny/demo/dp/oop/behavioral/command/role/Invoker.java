package me.sunny.demo.dp.oop.behavioral.command.role;

/**
 * 负责调用命令对象执行请求，相关的方法叫做行动方法。
 */
public class Invoker {
    /**
     * 持有命令对象
     */
    private Command command = null;
    /**
     * 构造方法
     */
    public Invoker(Command command){
        this.command = command;
    }
    /**
     * 行动方法
     */
    public void action(){

        command.execute();
    }
}
