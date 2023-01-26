package configurations;

import static utils.PropertiesHelper.getInstance;

import clients.RestClient;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import utils.PropertiesHelper;

public class BaseTest {
  protected static RestClient restClient;
  protected static PropertiesHelper propertiesHelper;

  protected static String responsesTemplatePath;
  protected static String requestsTemplatePath;

  protected static String updateProductRequest;
  protected static String createProductRequest;
  protected static String deleteProductRequest;

  protected static String updateEmployeePath;

  @BeforeClass
  public static void beforeClassConfiguration() {
    propertiesHelper = getInstance();
    restClient = new RestClient();

    configureLog4j();
    initializeConstants();
  }

  private static void configureLog4j() {
    PropertyConfigurator.configure(propertiesHelper.getProperty("log4jPropertiesPath"));
  }

  private static void initializeConstants() {
    responsesTemplatePath = propertiesHelper.getProperty("responsesTemplatePath");
    requestsTemplatePath = propertiesHelper.getProperty("requestsTemplatePath");

    updateProductRequest = propertiesHelper.getProperty("updateProductRequest");
    createProductRequest = propertiesHelper.getProperty("createProductRequest");
    deleteProductRequest = propertiesHelper.getProperty("deleteProductRequest");

    updateEmployeePath = propertiesHelper.getProperty("updateEmployeePath");
  }
}

