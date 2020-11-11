package me.sunny.demo.basics.current;

import java.util.Deque;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TransferQueue;

public class QueueTest {
  private Deque deque;

  private ConcurrentLinkedDeque concurrentLinkedDeque;
  private ConcurrentLinkedQueue concurrentLinkedQueue;
  private PriorityQueue priorityQueue;

  private DelayQueue delayQueue;
  private BlockingDeque  blockingDeque;
  private TransferQueue transferQueue;

  private PriorityBlockingQueue priorityBlockingQueue;
  private SynchronousQueue synchronousQueue;
  private LinkedTransferQueue linkedTransferQueue;
  private LinkedBlockingDeque linkedBlockingDeque;
  private LinkedBlockingQueue linkedBlockingQueue;
  private ArrayBlockingQueue arrayBlockingQueue;
}
