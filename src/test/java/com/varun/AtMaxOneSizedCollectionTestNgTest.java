package com.varun;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Using TestNG, we use a thread pool size of 10 to run the test through multiple threads. The problem with such a
 * test is that though it should logically end up in a failure for our thread-unsafe implementation, we are dependent
 * upon the ordering of threads for catching the error. For one of the runs, all the tests could end up passing
 * whereas for another instance we might see a failure.
 * <p>
 * So we have done something worse than not testing for concurrent access. We have now ended with a flaky test which
 * might work on our machine but then end up breaking the build during later runs.
 */
public class AtMaxOneSizedCollectionTestNgTest {

  private final AtMaxOneSizedCollection<String> atMaxOneSizedCollection = new AtMaxOneSizedCollection<>();

  @Test(threadPoolSize = 10, invocationCount = 10)
  public void testCollectionWithMultipleThreads() {
    atMaxOneSizedCollection.addIfCollectionIsEmpty("foo");
    assertThat(atMaxOneSizedCollection.size(), is(1));
  }
}
