package demo.springboot.api;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import demo.springboot.api.common.ErrorMessages;
import demo.springboot.api.model.Element;
import java.util.Optional;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Query execution API.
 */
public class QueryExecutionHandler extends FunctionInvoker<Integer, Element> {

  /**
   * Constructs a new instance of the QueryExecutionHandler class.
   */
  public QueryExecutionHandler() {
    super();
  }

  /**
   * Constructs a new instance of the QueryExecutionHandler class.
   * Note. This constructor seems to be needed if tests are calling this handler
   * specifically,
   * so the FunctionInvoker can 'find' the QueryExecution component.
   *
   * @param configurationClass the Spring boot configuration class.
   */
  public QueryExecutionHandler(java.lang.Class<?> configurationClass) {
    super(configurationClass);
  }

  /**
   * Query API entrypoint.
   *
   * @param request the client request.
   * @param context the client request context.
   * 
   * @return The API response.
   */
  @FunctionName("atomicnumberquery")
  public HttpResponseMessage execute(
      @HttpTrigger(methods = {
          HttpMethod.GET },
          route = "v1/atomicnumber/{atomicnumber}",
          name = "calculatePrimes",
          authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
      @BindingName("atomicnumber") int atomicNumber,
      final ExecutionContext context) {

    Element element = handleRequest(atomicNumber, context);
    if (element == null) {
      return request
          .createResponseBuilder(HttpStatus.NOT_FOUND)
          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
          .body(ErrorMessages.ELEMENT_NOT_FOUND)
          .build();
    }

    return request
        .createResponseBuilder(HttpStatus.OK)
        .body(element)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
