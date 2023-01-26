package tests;

import static enums.ResourceFiles.CREATE_PRODUCT_RESPONSE;
import static enums.ResourceFiles.DELETE_PRODUCT_RESPONSE;
import static enums.ResourceFiles.UPDATE_PRODUCT_RESPONSE;
import static enums.ResourcePath.CREATE_PRODUCT_PATH;
import static enums.ResourcePath.DELETE_PRODUCT_PATH;
import static enums.ResourcePath.GET_PRODUCT_PATH;
import static enums.ResourcePath.UPDATE_PRODUCT_PATH;
import static enums.ResponseCodes.CREATED;
import static enums.ResponseCodes.OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.JsonHelper.readJsonFileAsObject;

import configurations.BaseTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Slf4j
public class ProductTest extends BaseTest {

  @ParameterizedTest
  @CsvFileSource(resources = "/config/test-data.csv", numLinesToSkip = 1)
  // Test #2 is failing
  void getProduct(String id, String productResponseFile) {
    Response response =
        restClient.sendRequestWithParams(Method.GET, GET_PRODUCT_PATH.getPath(), Map.of("id", id));

    log.info("Response is: {}", response.asString());
    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));

    //    You are comparing objects here. Do you override equals and hashCode?
    assertThat(response.as(Product.class),
        equalTo(readJsonFileAsObject(responsesTemplatePath + productResponseFile, Product.class)));
  }

  @Test
  public void createProduct() {
    Product product =
        readJsonFileAsObject(requestsTemplatePath + createProductRequest, Product.class);
    Response response =
        restClient.sendRequestWithBody(Method.POST, CREATE_PRODUCT_PATH.getPath(), product);

    String createdProductMassage = response.jsonPath().getString("message");
    String expectedProductMessage =
        readJsonFileAsObject(responsesTemplatePath + CREATE_PRODUCT_RESPONSE.getPath(), Map.class)
            .get("message")
            .toString();

    log.info("Message is {}", createdProductMassage);
    assertThat(response.statusCode(), Matchers.equalTo(CREATED.getValue()));
    assertThat(createdProductMassage, equalTo(expectedProductMessage));
  }

  @Test
  public void updateProduct() {
    Product product =
        readJsonFileAsObject(requestsTemplatePath + updateProductRequest, Product.class);
    Response response =
        restClient.sendRequestWithBody(Method.PUT, UPDATE_PRODUCT_PATH.getPath(), product);

    String updatedProductMassage = response.jsonPath().getString("message");
    String expectedProductMessage =
        readJsonFileAsObject(responsesTemplatePath + UPDATE_PRODUCT_RESPONSE.getPath(), Map.class)
            .get("message")
            .toString();

    log.info("Message is {}", updatedProductMassage);
    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(updatedProductMassage, equalTo(expectedProductMessage));
  }

  @Test
  public void deleteProduct() {
    Product product = readJsonFileAsObject(requestsTemplatePath + deleteProductRequest, Product.class);
    Response response =
        restClient.sendRequestWithBody(Method.DELETE, DELETE_PRODUCT_PATH.getPath(), product);

    String deletedProductMassage = response.jsonPath().getString("message");
    String expectedProductMessage =
            readJsonFileAsObject(responsesTemplatePath + DELETE_PRODUCT_RESPONSE.getPath(), Map.class)
                    .get("message")
                    .toString();

    //    Asserts should be visually separated and be as much readable as possible
    log.info("Message is {}", deletedProductMassage);
    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(expectedProductMessage, equalTo(expectedProductMessage));
  }
}