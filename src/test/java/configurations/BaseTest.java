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
    protected static String updateEmployeePath;
    protected static String changeNameEmployeePath;
    protected static String changeCountryEmployeePath;
    protected static String changeCityEmployeePath;
    protected static String changePositionEmployeePath;
    protected static String changeCompanyNamePath;
    protected static String changeCompanyIdPath;
    protected static String changeNameEmployeeValue;
    protected static String changeCountryEmployeeValue;
    protected static String changeCityEmployeeValue;
    protected static String changePositionEmployeeValue;
    protected static String changeCompanyNameValue;
    protected static String changeCompanyIdValue;


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
        updateEmployeePath = propertiesHelper.getProperty("updateEmployeePath");
        changeNameEmployeePath = propertiesHelper.getProperty("changeNameEmployeePath");
        changeCountryEmployeePath = propertiesHelper.getProperty("changeCountryEmployeePath");
        changeCityEmployeePath = propertiesHelper.getProperty("changeCityEmployeePath");
        changePositionEmployeePath = propertiesHelper.getProperty("changePositionEmployeePath");
        changeCompanyNamePath = propertiesHelper.getProperty("changeCompanyNamePath");
        changeCompanyIdPath = propertiesHelper.getProperty("changeCompanyIdPath");
        changeNameEmployeeValue = propertiesHelper.getProperty("changeNameEmployeeValue");
        changeCountryEmployeeValue = propertiesHelper.getProperty("changeCountryEmployeeValue");
        changeCityEmployeeValue = propertiesHelper.getProperty("changeCityEmployeeValue");
        changePositionEmployeeValue = propertiesHelper.getProperty("changePositionEmployeeValue");
        changeCompanyNameValue = propertiesHelper.getProperty("changeCompanyNameValue");
        changeCompanyIdValue = propertiesHelper.getProperty("changeCompanyIdValue");
    }
}

