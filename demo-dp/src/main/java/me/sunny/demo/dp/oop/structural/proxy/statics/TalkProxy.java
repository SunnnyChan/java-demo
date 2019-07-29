package me.sunny.demo.dp.oop.structural.proxy.statics;

public class TalkProxy implements Talk{
    private Talk talker;

    public TalkProxy(Talk talker) {
        this.talker = talker;
    }

    public void talk(String msg) {
        this.talker.talk(msg);
    }

    public void talk(String msg, String songName) {
        this.talker.talk(msg);
        this.song(songName);
    }

    private void song(String songName) {
        System.out.println("唱歌：" + songName);
    }
}
