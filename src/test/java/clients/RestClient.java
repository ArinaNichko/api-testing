package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.Product;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyMap;
import static java.util.Optional.ofNullable;
import static utils.PropertiesHelper.getProperty;

public class RestClient {
  public static String baseURL = getProperty("baseUrl");
  private static final String NULL = "null";

  public Response sendRequestWithParams(Method requestType, String url, Map<String, String> queryParams) {
    return sendRequestForHttpMethods(requestType, url, null, queryParams);
  }

  public Response sendRequestWithBody(Method requestType, String url, Object requestBody) {
    return sendRequestForHttpMethods(requestType, url, requestBody, emptyMap());
  }

  private Response sendRequestForHttpMethods(Method httpMethod, String url, Object requestBody,  Map<String, String> params) {
    RequestSpecification requestSpecification = given()
            .contentType(ContentType.JSON)
            .body(ofNullable(requestBody).orElse(NULL))
            .queryParams(ofNullable(params).orElse(emptyMap()));
    return requestSpecification.request(httpMethod, baseURL + url);
  }
  public void initialize() {
    RestAssured.baseURI = getProperty("baseUrl");
  }
}
