package me.sunny.demo.basics.io;

import java.nio.ByteBuffer;

import org.junit.Test;

public class ByteBufferApiTest {
  @Test
  public void testMark() {
    ByteBuffer buffer = ByteBuffer.allocate(6);
    // position: 0, limit: 6, capacity: 6

    buffer.put((byte)1);
    buffer.put((byte)2);
    buffer.put((byte)3);
    // position: 3, limit: 6, capacity: 6

    // 写入三个字节数据后进行标记
    buffer.mark();
    // position: 3, limit: 6, capacity: 6

    // 再次写入一个字节数据
    buffer.put((byte)4);
    // position: 4, limit: 6, capacity: 6

    // 对buffer进行重置，此时将恢复到Mark时的状态
    buffer.reset();
    // position: 3, limit: 6, capacity: 6

    // 切换为读取模式，此时有三个数据可供读取
    buffer.flip();
    // position: 0, limit: 3, capacity: 6

    // 读取一个字节数据之后进行标记
    buffer.get();
    buffer.mark();
    // position: 1, limit: 3, capacity: 6

    // 继续读取一个字节数据
    buffer.get();
    // position: 2, limit: 3, capacity: 6

    // 进行重置之后，将会恢复到mark的状态
    buffer.reset();
    // position: 1, limit: 3, capacity: 6
  }

  @Test
  public void testRewind() {
    ByteBuffer buffer = ByteBuffer.allocate(6);
    // position: 0, limit: 6, capacity: 6

    buffer.put((byte) 1);
    buffer.put((byte) 2);
    buffer.put((byte) 3);
    // position: 3, limit: 6, capacity: 6

    buffer.rewind();  // 调用rewind()方法之后，buffer状态将会重置
    // position: 0, limit: 6, capacity: 6
  }

  @Test
  public void testCompact() {
    ByteBuffer buffer = ByteBuffer.allocate(6);
    buffer.put((byte) 1);
    buffer.put((byte) 2);
    buffer.put((byte) 3);
    buffer.put((byte) 4);
    buffer.put((byte) 5);
    buffer.put((byte) 6); // 初始化一个写满的buffer

    buffer.flip();
    // position: 0, limit: 6, capacity: 6  -- 切换为读取模式

    buffer.get();
    buffer.get();
    // position: 2, limit: 6, capacity: 6  -- 读取两个字节后，还剩余四个字节

    buffer.compact();
    // position: 4, limit: 6, capacity: 6  -- 进行压缩之后将从第五个字节开始

    buffer.put((byte) 7);
    // position: 5, limit: 6, capacity: 6  -- 写入一个字节数据的状态
  }
}
