package com.varun;

import edu.umd.cs.mtc.MultithreadedTest;
import edu.umd.cs.mtc.TestFramework;
import org.junit.Test;

/**
 * MultithreadedTestTC is a framework that allows us to perform multiple interleaving of threads over a block of
 * code. Using this framework we can specify the actual execution flow of our code when accessed by multiple threads
 * and the framework runs through various possible inter-leavings of threads. This allows us to catch the bugs which
 * might not be visible in the first run of our code.
 * <p>
 * {@link <a href="https://www.cs.umd.edu/projects/PL/multithreadedtc/overview.html">MultiThreadedTC</a>}
 */
public class AtMaxOneSizedCollectionMultithreadedTest extends MultithreadedTest {

  private AtMaxOneSizedCollection<String> atMaxOneSizedCollection;

  @Override
  public void initialize() {
    this.atMaxOneSizedCollection = new AtMaxOneSizedCollection<>();
  }

  public void thread1() {
    atMaxOneSizedCollection.addIfCollectionIsEmpty("foo");
  }

  public void thread2() {
    atMaxOneSizedCollection.addIfCollectionIsEmpty("foo");
  }

  @Override
  public void finish() {
    assertEquals(1, atMaxOneSizedCollection.size());
  }

  @Test
  public void testAtMaxOneSizedCollection() throws Throwable {
    TestFramework.runManyTimes(new AtMaxOneSizedCollectionMultithreadedTest(), 100);
  }
}
