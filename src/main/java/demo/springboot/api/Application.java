package demo.springboot.api;

import demo.springboot.api.periodictable.ElementStore;
import demo.springboot.api.periodictable.ElementStoreJson;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * Spring Boot application entry point.
 */
@SpringBootApplication
public class Application {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Constructs an ElementStore implementation for DI.
   *
   * @return an ElementStore instance.
   */
  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public ElementStore elementStore() {
    
    ElementStoreJson store = new ElementStoreJson();
    try {
      store.initialize();
      return store;
    } catch (IOException | ParseException e) {
      throw new BeanCreationException("ElementStore", "Unable to create ElementStoreJSON");
    }
  }
}
