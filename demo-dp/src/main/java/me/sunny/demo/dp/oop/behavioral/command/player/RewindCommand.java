package me.sunny.demo.dp.oop.behavioral.command.player;

import me.sunny.demo.dp.oop.behavioral.command.role.Command;

public class RewindCommand implements Command {
    private AudioPlayerReceiver myAudio;

    public RewindCommand(AudioPlayerReceiver audioPlayer){
        myAudio = audioPlayer;
    }
    @Override
    public void execute() {
        myAudio.rewind();
    }
}
