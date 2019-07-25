package me.sunny.demo.dp.behavioral.command.player;

import me.sunny.demo.dp.behavioral.command.role.Command;

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
