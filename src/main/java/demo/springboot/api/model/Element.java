package demo.springboot.api.model;

/**
 * Element model.
 */
public class Element {
  private final String name;
  private final String symbol;
  private final String appearance;
  private final String summary;
  private final String wikiPage;
  private final String phase;

  /**
   * Constructs a new Element.
   *
   * @param name Element name.
   * @param symbol Symbold name.
   * @param appearance Appearance.
   * @param summary Summary.
   * @param wikiPage wikiPage address.
   * @param phase Phase.
   */
  public Element(
      String name,
      String symbol,
      String appearance,
      String summary,
      String wikiPage,
      String phase) {
    this.name = name;
    this.symbol = symbol;
    this.appearance = appearance;
    this.summary = summary;
    this.wikiPage = wikiPage;
    this.phase = phase;
  }

  public String getName() {
    return this.name;
  }

  public String getSymbol() {
    return this.symbol;
  }

  public String getAppearance() {
    return this.appearance;
  }

  public String getSummary() {
    return this.summary;
  }

  public String getWikiPage() {
    return this.wikiPage;
  }

  public String getPhase() {
    return this.phase;
  }
}