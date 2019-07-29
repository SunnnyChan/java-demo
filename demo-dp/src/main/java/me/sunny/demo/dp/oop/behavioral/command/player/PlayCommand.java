package me.sunny.demo.dp.oop.behavioral.command.player;

import me.sunny.demo.dp.oop.behavioral.command.role.Command;

public class PlayCommand implements Command {

    private AudioPlayerReceiver myAudio;

    public PlayCommand(AudioPlayerReceiver audioPlayer){
        myAudio = audioPlayer;
    }

    /**
     * 执行方法
     */
    @Override
    public void execute() {
        myAudio.play();
    }

}
