package basics.adt

import org.junit.Test
import org.junit.Assert._

class TestArrayQueue {
  @Test def emptyOnCreate(): Unit = {
    val testQueue = new ArrayQueue[Int]
    assertTrue(testQueue.isEmpty)
  }
  @Test def addOneToQueue() = {
    val testQueue = new ArrayQueue[Int]
    testQueue.enqueue(10)
    assertFalse(testQueue.isEmpty)
    assertEquals(10, testQueue.peek)
    assertEquals(10, testQueue.dequeue())
    assertTrue(testQueue.isEmpty)
  }
  @Test def addRemoveAdd() = {
    val testQueue = new ArrayQueue[Int]
    val nums1 = Array(1, 2, 3)
    for (n <- nums1) testQueue.enqueue(n)
    for (n <- nums1) assertEquals(n, testQueue.dequeue())
    assertTrue(testQueue.isEmpty)
  }
}