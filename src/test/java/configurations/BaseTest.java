package configurations;

import static enums.ResourcePath.CREATE_PRODUCT;
import static enums.ResourcePath.DELETE_PRODUCT;
import static enums.ResourcePath.GET_ALL_PRODUCT;
import static utils.FileHelper.readFileAsProduct;
import static utils.PropertiesHelper.getInstance;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import clients.RestClient;
import io.restassured.http.Method;
import io.restassured.response.Response;
import models.Product;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import utils.PropertiesHelper;

public class BaseTest {
  private static final String PRODUCT_CREATION_TEST_DATA = "src/test/resources/testData/productCreationTestData.csv";
  private static final String PATH_SEPARATOR_REGEXP = ", ";
  private static final String PATH_REPLACE_REGEXP = "\\[|\\]";
  private static final String PATH_REPLACEMENT = "";
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
    createProducts();
  }

  public static void createProducts() {

    List<Product> creationProductsList = readFileAsProduct(PRODUCT_CREATION_TEST_DATA);
    Response response = restClient.sendRequestWithParams(Method.GET, GET_ALL_PRODUCT.getPath(), Collections.emptyMap());
    String allProductsId = response.jsonPath().getString("records.id")
            .replaceAll(PATH_REPLACE_REGEXP, PATH_REPLACEMENT);
    List<String> allProductsIdlist = List.of(allProductsId.split(PATH_SEPARATOR_REGEXP));
    List<String> creationProductsIdlist = creationProductsList.stream().map(Product::getId)
            .map((Integer i) -> Integer.toString(i)).toList();

    deleteProductsList(creationProductsIdlist, allProductsIdlist);

    for (Product product : creationProductsList) {
      restClient.sendRequestWithBody(Method.POST, CREATE_PRODUCT.getPath(), product);
    }

  }

  public static void deleteProductsList(List<String> creationProductsIdList, List<String> allProductsIdList) {
    for (String creationProductId : creationProductsIdList) {
      for (String productId : allProductsIdList) {
        if (creationProductId.equals(productId)) {
          restClient.sendRequestWithBody(Method.DELETE, DELETE_PRODUCT.getPath(), Map.of("id", creationProductId));
        }
      }
    }
  }

  private static void configureLog4j() {
    PropertyConfigurator.configure(propertiesHelper.getProperty("log4jPropertiesPath"));
  }

  private static void initializeConstants() {
    responsesTemplatePath = propertiesHelper.getProperty("responsesTemplatePath");
    requestsTemplatePath = propertiesHelper.getProperty("requestsTemplatePath");
  }
}

