package demo.springboot.api;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import demo.springboot.api.periodictable.ElementStore;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestApplicationConfiguration {
  @Bean("testPeriodicTable")
  @Primary
  public ElementStore elementStore() {
    
    ElementStore elementStore = mock(ElementStore.class);

    when(elementStore.getByAtomicNumber(0)).thenReturn(null);
    when(elementStore.getByAtomicNumber(1)).thenReturn(TestData.hydrogen());

    return elementStore;
  }
}