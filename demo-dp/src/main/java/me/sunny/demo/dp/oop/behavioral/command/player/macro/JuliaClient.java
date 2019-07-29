package me.sunny.demo.dp.oop.behavioral.command.player.macro;

import me.sunny.demo.dp.oop.behavioral.command.player.AudioPlayerReceiver;
import me.sunny.demo.dp.oop.behavioral.command.player.PlayCommand;
import me.sunny.demo.dp.oop.behavioral.command.player.RewindCommand;
import me.sunny.demo.dp.oop.behavioral.command.player.StopCommand;
import me.sunny.demo.dp.oop.behavioral.command.role.Command;
import me.sunny.demo.dp.oop.behavioral.command.role.MacroCommand;

public class JuliaClient {
    public static void main(String[]args){
        //创建接收者对象
        AudioPlayerReceiver audioPlayer = new AudioPlayerReceiver();
        //创建命令对象
        Command playCommand = new PlayCommand(audioPlayer);
        Command rewindCommand = new RewindCommand(audioPlayer);
        Command stopCommand = new StopCommand(audioPlayer);

        MacroCommand marco = new MacroAudioCommand();

        marco.add(playCommand);
        marco.add(rewindCommand);
        marco.add(stopCommand);
        marco.execute();
    }
}
