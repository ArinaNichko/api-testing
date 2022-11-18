package clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import models.Product;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.PropertiesHelper.getProperty;

public class RestClient {
  public static String baseURL = getProperty("baseUrl");


  public Response performGetRequest(String url, Map<String, String> params) {
    return given().queryParams(params).when().get(url);
  }

  public void initialize() {
    RestAssured.baseURI = getProperty("baseUrl");
  }

  public static ValidatableResponse postProductRequest(Product product) {
    return given()
        .baseUri(baseURL)
        .contentType(ContentType.JSON)
        .body(product)
        .when()
        .post(getProperty("pathForCreate"))
        .then();
  }

  public static ValidatableResponse putProductRequest(Product product) {
    return RestAssured.given()
        .baseUri(baseURL)
        .contentType(ContentType.JSON)
        .body(product)
        .when()
        .put(getProperty("pathForUpdate"))
        .then();
  }

  public static ValidatableResponse deleteProductRequest(Product product) {
    return RestAssured.given()
        .baseUri(baseURL)
        .contentType(ContentType.JSON)
        .body(product)
        .when()
        .delete(getProperty("pathForDelete"))
        .then();
  }
}
