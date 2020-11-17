package me.sunny.demo.basics.language.current;

import org.junit.Test;

public class ThreadLocalTest {

  // ThreadLocal 中的Hash魔数，1640531527(十六进制为0x61c88647)，而-1640531527就是32位带符号整数的黄金分割值。
  private static final int HASH_INCREMENT = 0x61c88647;

  /**
   *  ThreadLocal 哈希算法：
   *  keyIndex = ((i + 1) * HASH_INCREMENT) & (length - 1)
   *
   *  i为ThreadLocal实例的个数，
   *
   *  HASH_INCREMENT就是哈希魔数0x61c88647，
   *
   *  length为ThreadLocalMap中可容纳的Entry(K-V结构)的个数(或者称为容量)。
   *  在ThreadLocal中的内部类ThreadLocalMap的初始化容量为16，扩容后总是2的幂次方。
   */
  @Test
  public void testKeyHashFunction() throws Exception {
    hashCode(4);
    hashCode(16);
    hashCode(32);
  }

  private static void hashCode(int capacity) throws Exception {
    int keyIndex;
    for (int i = 0; i < capacity; i++) {
      keyIndex = ((i + 1) * HASH_INCREMENT) & (capacity - 1);
      System.out.print(keyIndex);
      System.out.print(" ");
    }
    System.out.println();
  }
}
