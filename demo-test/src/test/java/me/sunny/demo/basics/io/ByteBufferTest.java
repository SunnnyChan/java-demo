package me.sunny.demo.basics.io;

import java.nio.ByteBuffer;

import org.junit.Test;

public class ByteBufferTest {

  @Test
  public void testBuffer() {
    // 初始化一个大小为6的ByteBuffer
    ByteBuffer buffer = ByteBuffer.allocate(6);
    // 初始状态：position: 0, limit: 6, capacity: 6
    print(buffer);

    // 往buffer中写入3个字节的数据
    buffer.put((byte)1);
    buffer.put((byte)2);
    buffer.put((byte)3);
    // 写入之后的状态：position: 3, limit: 6, capacity: 6
    print(buffer);

    System.out.println("************** after flip **************");
    buffer.flip();
    // 切换为读取模式之后的状态：position: 0, limit: 3, capacity: 6
    print(buffer);

    buffer.get();
    buffer.get();
    // 读取两个数据之后的状态：position: 2, limit: 3, capacity: 6
    print(buffer);
  }

  private void print(ByteBuffer buffer) {
    System.out.printf("position: %d, limit: %d, capacity: %d\n",
        buffer.position(), buffer.limit(), buffer.capacity());
  }
}
