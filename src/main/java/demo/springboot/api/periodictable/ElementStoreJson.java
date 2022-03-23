package demo.springboot.api.periodictable;

import demo.springboot.api.model.Element;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Element Store JSON Datasource.
 */
public class ElementStoreJson implements ElementStore {
  HashMap<Long, Element> elements = new HashMap<>();

  public void initialize() throws FileNotFoundException, IOException, ParseException {
    this.readPeriodicTable();
  }

  @Override
  public Element getByAtomicNumber(int atomicNumber) {
    return this.elements.get(Long.valueOf(atomicNumber));
  }

  /**
   * Read the PeriodicTableJSON.json resource and store it in a HashMap for quick
   * queries.
   */
  private void readPeriodicTable() throws IOException, ParseException, FileNotFoundException {
    JSONParser jsonParser = new JSONParser();
    InputStream stream = getClass().getClassLoader().getResourceAsStream("PeriodicTableJSON.json");
    if (stream == null) {
      throw new FileNotFoundException("Couldn't find a the JSON resource stream.");
    }

    Object obj = jsonParser.parse(new InputStreamReader(stream));
    JSONArray elementList = (JSONArray) obj;

    for (int i = 0; i < elementList.size(); i++) {
      JSONObject element = (JSONObject) elementList.get(i);
      this.elements.put((Long) element.get("number"), new Element(
          (String) element.get("name"),
          (String) element.get("symbol"),
          (String) element.get("appearance"),
          (String) element.get("summary"),
          (String) element.get("source"),
          (String) element.get("phase")));
    }
  }
}