package me.sunny.demo.dp;

import me.sunny.demo.dp.oop.behavioral.state.vote.VoteManager;
import org.junit.Test;

public class StateTest {

  @Test
  public void voteTest() {
    VoteManager vm = new VoteManager();
    for (int i=0; i<9; i++){
      vm.vote("u1","A");
    }
  }
}
