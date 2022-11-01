package com.varun;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is an incorrect way of testing as it doesn't actually test for concurrent access of the collection but rather
 * performs the test in a linear fashion. This test will always pass as the occurrence one of addIfCollectionIsEmpty()
 * is first finished and then only second occurrence of addIfCollectionIsEmpty() is triggered.
 * <p>
 * Hence, we end up not testing for concurrency but rather test the correctness of the business logic.
 */
public class AtMaxOneSizedCollectionIncorrectTest {

  private AtMaxOneSizedCollection<String> atMaxOneSizedCollection;

  @Before
  public void setUp() {
    this.atMaxOneSizedCollection = new AtMaxOneSizedCollection<>();
  }

  @Test
  public void testAtMaxOnceInsertionSuccessful() {
    atMaxOneSizedCollection.addIfCollectionIsEmpty("foo");
    atMaxOneSizedCollection.addIfCollectionIsEmpty("foo");
    assertEquals(atMaxOneSizedCollection.size(), 1);
  }
}