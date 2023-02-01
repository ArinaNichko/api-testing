package tests;

import static enums.ResourceFiles.CREATE_PRODUCT_RESPONSE;
import static enums.ResourceFiles.DELETE_PRODUCT_REQUEST;
import static enums.ResourceFiles.DELETE_PRODUCT_RESPONSE;
import static enums.ResourceFiles.UPDATE_PRODUCT_REQUEST;
import static enums.ResourceFiles.UPDATE_PRODUCT_RESPONSE;
import static enums.ResourcePath.CREATE_PRODUCT;
import static enums.ResourcePath.DELETE_PRODUCT;
import static enums.ResourcePath.GET_PRODUCT;
import static enums.ResourcePath.UPDATE_PRODUCT;
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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest extends BaseTest {
  private final String PRODUCT_TEST_DATA = "/testData/productTestData.csv";
  private final String PRODUCT_CREATION_TEST_DATA = "/testData/productCreationTestData.csv";


  @ParameterizedTest
  @Order(2)
  @CsvFileSource(resources = PRODUCT_TEST_DATA, numLinesToSkip = 1)
  public void getProduct(String id, String productResponseFile) {
    Response response =
            restClient.sendRequestWithParams(Method.GET, GET_PRODUCT.getPath(), Map.of("id", id));


    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));

    assertThat(response.as(Product.class),
            equalTo(readJsonFileAsObject(responsesTemplatePath + productResponseFile, Product.class)));
  }

  @ParameterizedTest
  @Order(1)
  @CsvFileSource(resources = PRODUCT_CREATION_TEST_DATA, numLinesToSkip = 1)
  public void createProduct(String createProductRequestPath) {
    Product product =
            readJsonFileAsObject(requestsTemplatePath + createProductRequestPath, Product.class);
    Response response =
            restClient.sendRequestWithBody(Method.POST, CREATE_PRODUCT.getPath(), product);
    System.out.println(response.asString());
    String createdProductMassage = response.jsonPath().getString("message");
    String expectedProductMessage =
            readJsonFileAsObject(responsesTemplatePath + CREATE_PRODUCT_RESPONSE.getPath(), Map.class)
                    .get("message")
                    .toString();
    System.out.println(createdProductMassage);
    assertThat(response.statusCode(), Matchers.equalTo(CREATED.getValue()));
    assertThat(createdProductMassage, equalTo(expectedProductMessage));
  }

  @Test
  public void updateProduct() {
    Product product =
            readJsonFileAsObject(requestsTemplatePath + UPDATE_PRODUCT_REQUEST.getPath(), Product.class);
    Response response =
            restClient.sendRequestWithBody(Method.PUT, UPDATE_PRODUCT.getPath(), product);

    String updatedProductMassage = response.jsonPath().getString("message");
    String expectedProductMessage =
            readJsonFileAsObject(responsesTemplatePath + UPDATE_PRODUCT_RESPONSE.getPath(), Map.class)
                    .get("message")
                    .toString();

    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(updatedProductMassage, equalTo(expectedProductMessage));
  }

  @Test
  public void deleteProduct() {
    Product product = readJsonFileAsObject(requestsTemplatePath + DELETE_PRODUCT_REQUEST.getPath(), Product.class);
    Response response =
            restClient.sendRequestWithBody(Method.DELETE, DELETE_PRODUCT.getPath(), product);

    String expectedProductMessage =
            readJsonFileAsObject(responsesTemplatePath + DELETE_PRODUCT_RESPONSE.getPath(), Map.class)
                    .get("message")
                    .toString();

    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(expectedProductMessage, equalTo(expectedProductMessage));
  }
}