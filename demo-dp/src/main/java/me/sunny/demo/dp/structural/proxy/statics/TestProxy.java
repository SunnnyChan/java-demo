package me.sunny.demo.dp.structural.proxy.statics;

import org.testng.annotations.Test;

public class TestProxy {

    @Test
    public void test() {
        Talk talker = new PeopleTalk("Sunny", "18");
        talker.talk("No proxy test: ");

        TalkProxy talkProxy = new TalkProxy(talker);
        talkProxy.talk("Proxy test：", "File Miles Away");
    }

}
