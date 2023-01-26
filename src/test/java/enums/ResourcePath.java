package enums;

public enum ResourcePath {
  GET_PRODUCT_PATH("/read_one.php"),
  UPDATE_PRODUCT_PATH("/update.php"),
  DELETE_PRODUCT_PATH("/delete.php"),
  CREATE_PRODUCT_PATH("/create.php");

  private final String path;

  ResourcePath(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
