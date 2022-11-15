package tests;

import model.Product;
import org.junit.Test;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.BuildPathHelper.SUCCESS_CODE;
import static utils.BuildPathHelper.propertiesReader;
import static utils.JSONFileReader.JSOnFileToString;
import static utils.JSONFileReader.readJSONFile;
import static utils.RestClient.getProductRequest;
import static utils.RestClient.postProductRequest;
import static utils.RestClient.putProductRequest;
import static utils.RestClient.deleteProductRequest;


public class PositiveTest {


    @Test
    public void getProduct() {

        Product response = getProductRequest()
                .assertThat()
                .statusCode(SUCCESS_CODE)
                .extract().response().as(Product.class);
        assertThat(response, equalTo(readJSONFile(propertiesReader("pathTemplateResponses") + propertiesReader("pathGetResponse"))));
    }


    @Test
    public void createProduct() {
        Product product = readJSONFile(propertiesReader("pathTemplateRequests") + propertiesReader("pathPostRequest"));

        String responsePost = postProductRequest(product)
                .assertThat()
                .statusCode(SUCCESS_CODE)
                .extract().response().asString();
        assertThat(responsePost, equalTo(JSOnFileToString(propertiesReader("pathTemplateResponses") + propertiesReader("pathPostResponse"))));
    }


    @Test
    public void updateProduct() {

        Product product = readJSONFile(propertiesReader("pathTemplateRequests") + propertiesReader("pathPutRequest"));
        String responsePut = putProductRequest(product)
                .assertThat()
                .statusCode(SUCCESS_CODE)
                .extract().response().asString();
        assertThat(responsePut, equalTo(JSOnFileToString(propertiesReader("pathTemplateResponses") + propertiesReader("pathPutResponse"))));
    }

    @Test
    public void deleteProduct() {
        Product product = readJSONFile(propertiesReader("pathTemplateRequests") + propertiesReader("pathDeleteRequest"));
        String responseDelete = deleteProductRequest(product)
                .assertThat()
                .statusCode(SUCCESS_CODE)
                .extract().response().asString();
        assertThat(responseDelete, equalTo(JSOnFileToString(propertiesReader("pathTemplateResponses") + propertiesReader("pathDeleteResponse"))));
    }
}