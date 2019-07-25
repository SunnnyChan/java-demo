package me.sunny.demo.dp.behavioral.command.player.macro;

import java.util.ArrayList;
import java.util.List;

import me.sunny.demo.dp.behavioral.command.role.Command;
import me.sunny.demo.dp.behavioral.command.role.MacroCommand;

public class MacroAudioCommand implements MacroCommand {
    private List<Command> commandList = new ArrayList<Command>();
    /**
     * 宏命令聚集管理方法
     */
    @Override
    public void add(Command cmd) {
        commandList.add(cmd);
    }
    /**
     * 宏命令聚集管理方法
     */
    @Override
    public void remove(Command cmd) {
        commandList.remove(cmd);
    }
    /**
     * 执行方法
     */
    @Override
    public void execute() {
        for(Command cmd : commandList){
            cmd.execute();
        }
    }
}
