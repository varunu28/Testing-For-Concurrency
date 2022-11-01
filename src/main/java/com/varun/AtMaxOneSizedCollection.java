package com.varun;

import java.util.ArrayList;

/**
 * AtMaxOneSizedCollection is an abstraction over an ArrayList where our goal is to have a collection that can have at
 * most one entity in the data structure. So the valid states of the data structure are either empty or a collection
 * of size one.
 */
public class AtMaxOneSizedCollection<T> extends ArrayList<T> {

  /**
   * Method to add an element to collection if collection is empty.
   *
   * @param element Element that is intended to be added to the collection
   * @return boolean representing if the element was inserted to the collection successfully or not.
   */
  public synchronized boolean addIfCollectionIsEmpty(T element) {
    boolean isEmpty = super.isEmpty();
    if (isEmpty) {
      super.add(element);
    }
    return !isEmpty;
  }
}
