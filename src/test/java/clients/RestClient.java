package clients;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static java.util.Optional.ofNullable;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import utils.PropertiesHelper;



@Slf4j
public class RestClient {

  public static String baseURL = PropertiesHelper.getInstance().getProperty("baseUrl");

  public Response sendRequestWithParams(
          Method requestType, String url, Map<String, String> queryParams) {
    return sendRequestForHttpMethods(requestType, url, null, queryParams);
  }

  public Response sendRequestWithBody(Method requestType, String url, Object requestBody) {
    return sendRequestForHttpMethods(requestType, url, requestBody, emptyMap());
  }

  private Response sendRequestForHttpMethods(
          Method httpMethod, String url, Object requestBody, Map<String, String> params) {
    RequestSpecification requestSpecification =
            given()
                    .contentType(ContentType.JSON)
                    .body(ofNullable(requestBody).orElse(StringUtils.EMPTY))
                    .queryParams(ofNullable(params).orElse(emptyMap()));
    log.info("{} request was sent for URI: {}", httpMethod, baseURL + url);
    return requestSpecification.request(httpMethod, baseURL + url);
  }
}
