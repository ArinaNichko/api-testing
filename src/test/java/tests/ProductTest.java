package tests;

import static builder.Director.constructComplexProduct;
import static builder.Director.constructProductWithoutDescription;
import static builder.Director.constructSimpleProduct;
import static enums.ResourceFiles.COMPLEX_PRODUCT;
import static enums.ResourceFiles.CREATE_PRODUCT_REQUEST;
import static enums.ResourceFiles.CREATE_PRODUCT_RESPONSE;
import static enums.ResourceFiles.DELETE_PRODUCT_REQUEST;
import static enums.ResourceFiles.DELETE_PRODUCT_RESPONSE;
import static enums.ResourceFiles.PRODUCT_WITHOUT_DESCRIPTION;
import static enums.ResourceFiles.SIMPLE_PRODUCT;
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

import builder.ProductBuilder;
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
import stategies.CsvReader;
import stategies.FileReaderStrategy;
import stategies.JsonReader;

@Slf4j
public class ProductTest extends BaseTest {
  private static final FileReaderStrategy CSV_READER = new CsvReader();
  private static final FileReaderStrategy JSON_READER = new JsonReader();
  private final String PRODUCT_TEST_DATA = "/testData/productTestData.csv";

  @ParameterizedTest
  @CsvFileSource(resources = PRODUCT_TEST_DATA, numLinesToSkip = 1)
  public void getProduct(String id, String productResponseFile) {
    Response response =
            restClient.sendRequestWithParams(Method.GET, GET_PRODUCT.getPath(), Map.of("id", id));
    Product expectedProduct = JSON_READER.readFile(responsesTemplatePath + productResponseFile, Product.class);

    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(response.as(Product.class),
            equalTo(expectedProduct));
  }

  @Test
  public void createProduct() {
    Product product =
            JSON_READER.readFile(requestsTemplatePath + CREATE_PRODUCT_REQUEST.getPath(), Product.class);
    Response response =
            restClient.sendRequestWithBody(Method.POST, CREATE_PRODUCT.getPath(), product);

    String createdProductMassage = response.jsonPath().getString("message");
    String expectedProductMessage =
            CSV_READER.readFile(responsesTemplatePath + CREATE_PRODUCT_RESPONSE.getPath(), Map.class)
                    .get("message")
                    .toString();

    assertThat(response.statusCode(), Matchers.equalTo(CREATED.getValue()));
    assertThat(createdProductMassage, equalTo(expectedProductMessage));
  }

  @Test
  public void updateProduct() {
    Product product =
            JSON_READER.readFile(requestsTemplatePath + UPDATE_PRODUCT_REQUEST.getPath(), Product.class);
    Response response =
            restClient.sendRequestWithBody(Method.PUT, UPDATE_PRODUCT.getPath(), product);

    String updatedProductMassage = response.jsonPath().getString("message");
    String expectedProductMessage =
            CSV_READER.readFile(responsesTemplatePath + UPDATE_PRODUCT_RESPONSE.getPath(), Map.class)
                    .get("message")
                    .toString();

    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(updatedProductMassage, equalTo(expectedProductMessage));
  }

  @Test
  public void deleteProduct() {
    Product product = JSON_READER.readFile(requestsTemplatePath + DELETE_PRODUCT_REQUEST.getPath(), Product.class);
    Response response =
            restClient.sendRequestWithBody(Method.DELETE, DELETE_PRODUCT.getPath(), product);

    String expectedProductMessage =
            CSV_READER.readFile(responsesTemplatePath + DELETE_PRODUCT_RESPONSE.getPath(), Map.class)
                    .get("message")
                    .toString();

    assertThat(response.statusCode(), Matchers.equalTo(OK.getValue()));
    assertThat(expectedProductMessage, equalTo(expectedProductMessage));
  }

  @Test
  public void getComplexProduct() {
    ProductBuilder builder = new ProductBuilder();
    constructComplexProduct(builder);
    Product actualProduct = builder.build();

    Product expectedProduct = JSON_READER.readFile
            (expectedProductsTemplatePath + COMPLEX_PRODUCT.getPath(), Product.class);

    assertThat(actualProduct, equalTo(expectedProduct));
  }

  @Test
  public void getSimpleProduct() {
    ProductBuilder builder = new ProductBuilder();
    constructSimpleProduct(builder);
    Product actualProduct = builder.build();

    Product expectedProduct = JSON_READER.readFile
            (expectedProductsTemplatePath + SIMPLE_PRODUCT.getPath(), Product.class);

    assertThat(actualProduct, equalTo(expectedProduct));
  }

  @Test
  public void getProductWithoutDescription() {
    ProductBuilder builder = new ProductBuilder();
    constructProductWithoutDescription(builder);
    Product actualProduct = builder.build();

    Product expectedProduct = JSON_READER.readFile(
            expectedProductsTemplatePath + PRODUCT_WITHOUT_DESCRIPTION.getPath(), Product.class);

    assertThat(actualProduct, equalTo(expectedProduct));
  }
}