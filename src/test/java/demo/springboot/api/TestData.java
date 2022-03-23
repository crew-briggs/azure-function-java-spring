package demo.springboot.api;

import demo.springboot.api.model.Element;

public class TestData {
  public static Element hydrogen() {
    return new Element(
      "Hydrogen", 
      "H", 
      "colorless gas", 
      "Hydrogen is a chemical element with chemical symbol H and atomic number 1. With an atomic weight of 1.00794 u, hydrogen is the lightest element on the periodic table. Its monatomic form (H) is the most abundant chemical substance in the Universe, constituting roughly 75% of all baryonic mass.",
      "https://en.wikipedia.org/wiki/Hydrogen",
      "Gas");
  }
}
