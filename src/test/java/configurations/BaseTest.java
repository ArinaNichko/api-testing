package configurations;

import clients.RestClient;
import org.junit.BeforeClass;
import utils.PropertiesHelper;

import static utils.PropertiesHelper.getInstance;

public class BaseTest {
    protected static RestClient restClient;
    protected static PropertiesHelper propertiesHelper;
    protected static String responsesTemplatePath;

    protected static String requestsTemplatePath;
    protected static String getProductPath;
    protected static String updateProductPath;
    protected static String deleteProductPath;
    protected static String createProductPath;
    protected static String updateProductRequest;
    protected static String createProductRequest;
    protected static String deleteProductRequest;
    protected static String createProductResponse;
    protected static String getProductResponse;
    protected static String updateProductResponse;
    protected static String deleteProductResponse;

    @BeforeClass
    public static void beforeClassConfiguration() {
        propertiesHelper = getInstance();
        restClient = new RestClient();
        initializeConstants();
    }

    private static void initializeConstants() {

        responsesTemplatePath = propertiesHelper.getProperty("responsesTemplatePath");
        requestsTemplatePath = propertiesHelper.getProperty("requestsTemplatePath");
        getProductPath = propertiesHelper.getProperty("getProductPath");
        updateProductPath = propertiesHelper.getProperty("updateProductPath");
        deleteProductPath = propertiesHelper.getProperty("deleteProductPath");
        createProductPath = propertiesHelper.getProperty("createProductPath");
        updateProductRequest = propertiesHelper.getProperty("updateProductRequest");
        createProductRequest = propertiesHelper.getProperty("createProductRequest");
        deleteProductRequest = propertiesHelper.getProperty("deleteProductRequest");
        createProductResponse = propertiesHelper.getProperty("createProductResponse");
        getProductResponse = propertiesHelper.getProperty("getProductResponse");
        updateProductResponse = propertiesHelper.getProperty("updateProductResponse");
        deleteProductResponse = propertiesHelper.getProperty("deleteProductResponse");
    }
}

