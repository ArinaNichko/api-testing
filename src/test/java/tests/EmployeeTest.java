package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.JsonHelper.readJsonFileAsString;
import static utils.JsonHelper.readJsonStringAsObject;
import static utils.UpdateJsonHelper.updateFieldByPath;

import configurations.BaseTest;
import java.util.List;
import models.EmployeeModel;
import org.junit.Test;


public class EmployeeTest extends BaseTest {

  @Test
  public void updateEmployeeName() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.name", "Anna");
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getName(),
            equalTo("Anna"));
  }

  @Test
  public void updateAddressCity() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.addresses[0].city", "Lviv");
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getAddresses()
                    .get(0)
                    .getCity(),
            equalTo("Lviv"));
  }

  @Test
  public void updatePhones() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.phones", List.of("testPhone"));
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getPhones()[0],
            equalTo("testPhone"));
  }

  @Test
  public void updateCompanyName() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.company.name", "SoftServe");
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getCompany()
                    .getName(),
            equalTo("SoftServe"));
  }

  @Test
  public void updatePhone() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.phones[0]", "4554");
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getPhones()[0],
            equalTo("4554"));
  }
}
