package tests;


import static enums.ResponseCodes.CREATED;
import static enums.ResponseCodes.OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.JsonHelper.readJsonFileAsObject;
import static utils.JsonHelper.readJsonFileAsString;

import configurations.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.Map;
import models.Product;
import org.hamcrest.Matchers;
import org.junit.Test;


public class PositiveTest extends BaseTest {

  @Test
  public void getProduct() {

    Response response1 = restClient.sendRequestWithParams(Method.GET, getProductPath, Map.of("id", "2"));

    assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(response1.as(Product.class),
            equalTo(readJsonFileAsObject(responsesTemplatePath + getProductResponse, Product.class)));
  }

  @Test
  public void createProduct() {

    Product product = readJsonFileAsObject(requestsTemplatePath + createProductRequest, Product.class);
    Response response1 = restClient.sendRequestWithBody(Method.POST, createProductPath, product);

    assertThat(response1.statusCode(), Matchers.equalTo(CREATED.getValue()));
    assertThat(response1.asString(), equalTo(readJsonFileAsString(responsesTemplatePath + createProductResponse)));
  }

  @Test
  public void updateProduct() {

    Product product = readJsonFileAsObject(requestsTemplatePath + updateProductRequest, Product.class);
    Response response1 = restClient.sendRequestWithBody(Method.PUT, updateProductPath, product);

    assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(response1.asString(), equalTo(readJsonFileAsString(responsesTemplatePath + updateProductResponse)));
  }

  @Test
  public void deleteProduct() {
    Product product = readJsonFileAsObject(requestsTemplatePath + deleteProductRequest, Product.class);
    Response response1 = restClient.sendRequestWithBody(Method.DELETE, deleteProductPath, product);

    assertThat(response1.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(response1.asString(), equalTo(readJsonFileAsString(responsesTemplatePath + deleteProductResponse)));
  }
}