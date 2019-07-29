package me.sunny.demo.dp.oop.behavioral.command.role;

/**
 * ConcreteCommand（具体命令类）：
 * 具体命令类是抽象命令类的子类，实现了在抽象命令类中声明的方法，它对应具体的接收者对象，将接收者对象的动作绑定其中。
 * 在实现execute()方法时，将调用接收者对象的相关操作(Action)。
 */
public class ConcreteCommand implements Command {

    //持有相应的接收者对象
    private Receiver receiver = null;
    /**
     * 构造方法
     */
    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute() {
        //通常会转调接收者对象的相应方法，让接收者来真正执行功能
        receiver.action();
    }
}
