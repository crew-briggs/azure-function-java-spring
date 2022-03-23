package demo.springboot.api.periodictable;

import demo.springboot.api.model.Element;

/**
 * Element Store Interface.
 */
public interface ElementStore {
  public Element getByAtomicNumber(int atomicNumber);
}