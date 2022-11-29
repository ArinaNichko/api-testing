package configurations;

import clients.RestClient;
import org.junit.BeforeClass;
import utils.PropertiesHelper;

public class BaseTest {
    protected static RestClient restClient;

    @BeforeClass
    public static void beforeClassConfiguration() {
        PropertiesHelper.initializeProperties();
        restClient = new RestClient();
        restClient.initialize();
    }
}

