package me.sunny.demo.dp.oop.behavioral.command.player;

import me.sunny.demo.dp.oop.behavioral.command.role.Command;

public class StopCommand implements Command {
    private AudioPlayerReceiver myAudio;

    public StopCommand(AudioPlayerReceiver audioPlayer){
        myAudio = audioPlayer;
    }
    @Override
    public void execute() {
        myAudio.stop();
    }
}
