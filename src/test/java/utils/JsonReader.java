package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import models.Address;
import models.Company;
import models.Employee;
import models.EmployeeModel;
import models.Product;


public class JsonReader {
  static ObjectMapper objectMapper = new ObjectMapper();

  public static Product readJsonFile(String path) {
    try {
      return objectMapper.readValue(new File(path), Product.class);
    } catch (IOException e) {
      throw new RuntimeException(e);

    }
  }

  public static String jsonFileToString(String path) {
    try {
      return Files.readString(Path.of(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static Employee readJsonFilEmployee(String path) {
    try {
      return objectMapper.readValue(new File(path), EmployeeModel.class).getEmployee();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static Employee changeCity(String path, String change, int number) {
    Employee employee = readJsonFilEmployee(path);
    employee.getAddresses().get(number).setCity(change);
    return employee;
  }

  public static Employee changePhoneEmployee(Employee employee, String[] newValue) {
    employee.setPhones(newValue);
    return employee;
  }

  public static Employee changeValue(Employee employee, String path, String newValue) {
    String[] pathValue = path.split("[.\\[\\]]");
    int num = path.indexOf("[");
    int index = Character.getNumericValue(path.charAt(num + 1));
    switch (pathValue[pathValue.length - 1]) {
      case "name" -> {
        if ((pathValue.length - 1) <= 1) {
          employee.setName(newValue);
          break;
        }
        employee.getCompany().setName(newValue);
      }
      case "position" -> employee.setPosition(newValue);
      case "city" -> employee.getAddresses().get(index).setCity(newValue);
      case "country" -> employee.getAddresses().get(index).setCountry(newValue);
      case "id" -> employee.getCompany().setId(newValue);
      default -> throw new IllegalStateException("Unexpected value: " + pathValue[pathValue.length - 1]);
    }

    return employee;
  }


  public static Employee changeAddressEmployee(Employee employee, List<Address> newValue) {
    employee.setAddresses(newValue);
    return employee;
  }

  public static Employee changeCompanyEmployee(Employee employee, Company newValue) {
    employee.setCompany(newValue);
    return employee;
  }
}
