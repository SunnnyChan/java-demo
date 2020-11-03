package me.sunny.demo.basics.hash;

import org.junit.Test;

public class ShardingTest {
  private Long id;

  @Test
  public void getShardingKey() {
    id = 20004L;
    System.out.println(String.format("%04d", Math.abs(id.hashCode()) % 1024 / 64));
  }

  @Test
  public void getGroupTableNameByShardingKey() {
    Long shardingKeyValue = 20004L;
    System.out.println("kbtinteractioncenter_" + String.format("%04d", Math.abs(shardingKeyValue.hashCode()) % 1024 / 64)
        + "."
        + "" + String.format("%04d", Math.abs(shardingKeyValue.hashCode()) % 1024));
  }
}
