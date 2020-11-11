package me.sunny.demo.basics.current.java8;

import java.util.concurrent.locks.StampedLock;

import org.junit.Test;

public class StampedLockTest {

  private double x, y;

  private final StampedLock sl = new StampedLock();

  @Test
  public void test() {

  }

  // an exclusively locked method
  void move(double deltaX, double deltaY) {
    long stamp = sl.writeLock();
    try {
      x += deltaX;
      y += deltaY;
    } finally {
      sl.unlockWrite(stamp);
    }
  }

  // A read-only method
  double distanceFromOrigin() {
    long stamp = sl.tryOptimisticRead();

    double currentX = x, currentY = y;

    if (!sl.validate(stamp)) {
      stamp = sl.readLock();
      try {
        currentX = x;
        currentY = y;
      } finally {
        sl.unlockRead(stamp);
      }
    }
    return Math.sqrt(currentX * currentX + currentY * currentY);
  }
}
