package demo.springboot.api;

import demo.springboot.api.model.Element;
import demo.springboot.api.periodictable.ElementStore;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Query execution logic.
 */
@Component
public class QueryExecution implements Function<Integer, Element> {

  private final ElementStore elementStore;

  @Autowired
  public QueryExecution(ElementStore elementStore) {
    this.elementStore = elementStore;
  }

  /**
   * Executes an atomic number query on the ElementStore.
   */
  public Element apply(Integer atomicNumber) {
    return this.elementStore.getByAtomicNumber(atomicNumber);
  }
}
