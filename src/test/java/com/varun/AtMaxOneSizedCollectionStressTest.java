package com.varun;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Stress testing is another way to catch concurrency issues. Here we are using a framework(tempus-fugit) for
 * spinning up multiple threads in a concurrent fashion and run it over our business logic. With a thread count of
 * 100, there is a high chance of catching concurrency issues.
 * <p>
 * But we still can land up in a one-off scenario where the ordering of threads leads to passing of our test case
 * resulting in a flaky test.
 */
public class AtMaxOneSizedCollectionStressTest {

  @Rule
  public ConcurrentRule concurrentRule = new ConcurrentRule();

  @Rule
  public RepeatingRule rule = new RepeatingRule();

  private static final AtMaxOneSizedCollection<String> atMaxOneSizedCollection = new AtMaxOneSizedCollection<>();

  @Test
  @Concurrent(count = 100)
  @Repeating(repetition = 100)
  public void runMultipleTimes() {
    atMaxOneSizedCollection.addIfCollectionIsEmpty("foo");
  }

  @AfterClass
  public static void annotatedTestRunMultipleTimes() {
    assertEquals(1, atMaxOneSizedCollection.size());
  }
}
