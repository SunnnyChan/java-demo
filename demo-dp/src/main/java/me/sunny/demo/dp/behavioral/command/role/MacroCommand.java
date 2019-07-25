package me.sunny.demo.dp.behavioral.command.role;

/**
 * 代表宏命令的接口，以定义出具体宏命令所需要的接口
 */
public interface MacroCommand extends Command {
    /**
     * 宏命令聚集的管理方法
     * 可以添加一个成员命令
     */
    void add(Command cmd);
    /**
     * 宏命令聚集的管理方法
     * 可以删除一个成员命令
     */
    void remove(Command cmd);

}
