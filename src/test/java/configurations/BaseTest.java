package configurations;

import static enums.ResourcePath.CREATE_PRODUCT;
import static enums.ResourcePath.DELETE_PRODUCT;
import static enums.ResourcePath.GET_ALL_PRODUCTS;
import static utils.FileHelper.readCsvFileAsObject;
import static utils.PropertiesHelper.getInstance;

import clients.RestClient;
import io.restassured.http.Method;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import models.Product;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import utils.PropertiesHelper;

public class BaseTest {
  private static final String PRODUCT_CREATION_TEST_DATA = "src/test/resources/testData/productCreationTestData.csv";
  protected static RestClient restClient;
  protected static PropertiesHelper propertiesHelper;
  protected static String responsesTemplatePath;
  protected static String requestsTemplatePath;

  @BeforeClass
  public static void beforeClassConfiguration() {
    propertiesHelper = getInstance();
    restClient = new RestClient();

    configureLog4j();
    initializeConstants();
    createTestProducts();
  }

  private static void createTestProducts() {
    List<Product> productsToCreate =
        readCsvFileAsObject(new File(PRODUCT_CREATION_TEST_DATA), Product.class);

    List<Integer> allProductsId =
        restClient
            .sendRequestWithParams(Method.GET, GET_ALL_PRODUCTS.getPath(), Collections.emptyMap())
            .jsonPath()
            .getList("records.id", Integer.class);
    List<Integer> productsIdToDelete = getProductsIdToDelete(productsToCreate, allProductsId);

    deleteProducts(productsIdToDelete);
    createProducts(productsToCreate);
  }

  private static void deleteProducts(List<Integer> productsIdToDelete) {
    productsIdToDelete.forEach(productIdToCreate ->
            restClient.sendRequestWithBody(
                    Method.DELETE, DELETE_PRODUCT.getPath(), Map.of("id", productIdToCreate)));
  }

  private static List<Integer> getProductsIdToDelete(List<Product> productToCreate, List<Integer> allProductsId) {
    return productToCreate.stream().map(Product::getId)
            .filter(allProductsId::contains).collect(Collectors.toList());
  }

  private static void createProducts(List<Product> productsToCreate) {
    productsToCreate.forEach(
            productToCreate ->
                    restClient.sendRequestWithBody(Method.POST, CREATE_PRODUCT.getPath(), productToCreate));
  }

  private static void configureLog4j() {
    PropertyConfigurator.configure(propertiesHelper.getProperty("log4jPropertiesPath"));
  }

  private static void initializeConstants() {
    responsesTemplatePath = propertiesHelper.getProperty("responsesTemplatePath");
    requestsTemplatePath = propertiesHelper.getProperty("requestsTemplatePath");
  }
}
