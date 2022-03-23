package demo.springboot.api;

import org.junit.jupiter.api.Test;

public class ApplicationTest {
  
  /**
   * Validate the application can start without throwing Exceptions.
   * 
   * @throws Exception
   */
  @Test
  public void validateApplicationCanStart() throws Exception {
    Application.main(new String[] {});
  }
}