package me.sunny.demo.dp.oop.behavioral.command.player;

import me.sunny.demo.dp.oop.behavioral.command.role.Command;

public class JuliaClient {
    public static void main(String[]args){
        //创建接收者对象
        AudioPlayerReceiver audioPlayer = new AudioPlayerReceiver();
        //创建命令对象
        Command playCommand = new PlayCommand(audioPlayer);
        Command rewindCommand = new RewindCommand(audioPlayer);
        Command stopCommand = new StopCommand(audioPlayer);
        //创建请求者对象
        KeypadInvoker keypad = new KeypadInvoker();
        keypad.setPlayCommand(playCommand);
        keypad.setRewindCommand(rewindCommand);
        keypad.setStopCommand(stopCommand);
        //测试
        keypad.play();
        keypad.rewind();
        keypad.stop();
        keypad.play();
        keypad.stop();
    }
}
