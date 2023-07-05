package enums;

public enum ResourceFiles {
  CREATE_PRODUCT_RESPONSE("/csv/post-response.csv"),
  UPDATE_PRODUCT_RESPONSE("/csv/put-response.csv"),
  DELETE_PRODUCT_RESPONSE("/csv/delete-response.csv"),
  UPDATE_PRODUCT_REQUEST("/put-request.json"),
  CREATE_PRODUCT_REQUEST("/post-request.json"),
  DELETE_PRODUCT_REQUEST("/post-request.json"),
  SIMPLE_PRODUCT("/simple-product.json"),
  COMPLEX_PRODUCT("/complex-product.json"),
  PRODUCT_WITHOUT_DESCRIPTION("/product-without-description.json");
  private final String path;

  ResourceFiles(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}