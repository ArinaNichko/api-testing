package enums;

public enum ResourcePath {
  GET_PRODUCT("/read_one.php"),
  GET_ALL_PRODUCTS("/read.php"),
  UPDATE_PRODUCT("/update.php"),
  DELETE_PRODUCT("/delete.php"),
  CREATE_PRODUCT("/create.php"),
  UPDATE_EMPLOYEE_JSON("src/test/resources/employeeJson/employee.json"),
  UPDATE_EMPLOYEE_XML("src/test/resources/employeeJson/employee.xml");
  private final String path;

  ResourcePath(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
