package demo.springboot.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import demo.springboot.api.model.Element;
import java.util.Optional;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

/**
 * Unit test for Http Handler class.
 */
@SpringBootTest(classes = { Application.class, TestConfiguration.class })
public class QueryExecutionHandlerFunctionTest {
  
     /**
     * Http Handler 'null' query should return 400: BadRequest.
     */
    @Test
    public void invalidAtomicNumberNotFound() throws Exception {
      final HttpResponseMessage ret = executeWithMocks(0);
      assertEquals(ret.getStatus(), HttpStatus.NOT_FOUND);
    }

    /**
     * Http Handler 'null' query should return 400: BadRequest.
     */
    @Test
    public void validAtomicNumberNotFound() throws Exception {
      final HttpResponseMessage ret = executeWithMocks(1);
      assertEquals(ret.getStatus(), HttpStatus.OK);

      Element result = (Element) ret.getBody();
      assertEquals(TestData.hydrogen().getName(), result.getName());
      assertEquals(TestData.hydrogen().getSymbol(), result.getSymbol());
      assertEquals(TestData.hydrogen().getPhase(), result.getPhase());
      assertEquals(TestData.hydrogen().getWikiPage(), result.getWikiPage());
      assertEquals(TestData.hydrogen().getSummary(), result.getSummary());
    }

    /**
     * HttpHandler test setup helper method.  
     * @param <Result>
     * @param query
     * @return HttpResponse message.
     * @throws GremlinIllegalConfigurationException
     */
    private <Result> HttpResponseMessage executeWithMocks(int atomicNumber) {
      // Setup
      @SuppressWarnings("unchecked")
      final HttpRequestMessage<Optional<String>> req = mock(HttpRequestMessage.class);

      doAnswer(new Answer<HttpResponseMessage.Builder>() {
          @Override
          public HttpResponseMessage.Builder answer(InvocationOnMock invocation) {
              HttpStatus status = (HttpStatus) invocation.getArguments()[0];
              return new HttpResponseMessageMock.HttpResponseMessageBuilderMock().status(status);
          }
      }).when(req).createResponseBuilder(any(HttpStatus.class));

      final ExecutionContext context = mock(ExecutionContext.class);
      doReturn(Logger.getGlobal()).when(context).getLogger();
      doReturn("executeQueryV1").when(context).getFunctionName();
      doReturn("1").when(context).getInvocationId();

      final QueryExecutionHandler queryExecutionHandler = new QueryExecutionHandler(Application.class);

      return queryExecutionHandler.execute(req, atomicNumber, context);
    }
}