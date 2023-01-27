package enums;

public enum ResourceFiles {
  CREATE_PRODUCT_RESPONSE("/post-response.json"),
  GET_PRODUCT_RESPONSE("/get-response-id-2.json"),
  UPDATE_PRODUCT_RESPONSE("/put-response.json"),
  DELETE_PRODUCT_RESPONSE("/delete-response.json"),
  UPDATE_PRODUCT_REQUEST("/put-request.json"),
  CREATE_PRODUCT_REQUEST("/post-request.json"),
  DELETE_PRODUCT_REQUEST("/post-request.json");

  private final String path;

  ResourceFiles(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}