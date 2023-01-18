package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.JsonHelper.readJsonFileAsString;
import static utils.JsonHelper.readJsonStringAsObject;
import static utils.UpdateJsonHelper.updateFieldByPath;

import configurations.BaseTest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import models.EmployeeModel;
import org.junit.Test;


@Slf4j
public class EmployeeTest extends BaseTest {

  @Test
  public void updateEmployeeName() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String valueToUpdate = "Anna";
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.name", valueToUpdate);
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getName(),
            equalTo(valueToUpdate));
    log.info("Updated employee name is: {}", valueToUpdate);
  }

  @Test
  public void updateAddressCity() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String valueToUpdate = "Lviv";
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.addresses[0].city", valueToUpdate);
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getAddresses()
                    .get(0)
                    .getCity(),
            equalTo(valueToUpdate));
    log.info("Updated address city is: {}", valueToUpdate);
  }

  @Test
  public void updatePhones() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.phones", List.of("testPhone"));
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getPhones()[0],
            equalTo("testPhone"));
    log.info("Updated phones are: {}", List.of("testPhone"));
  }

  @Test
  public void updateCompanyName() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String valueToUpdate = "SoftServe";
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.company.name", valueToUpdate);
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getCompany()
                    .getName(),
            equalTo(valueToUpdate));
    log.info("Updated company name is: {}", valueToUpdate);
  }

  @Test
  public void updatePhone() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String valueToUpdate = "4554";
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.phones[0]", valueToUpdate);
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getPhones()[0],
            equalTo(valueToUpdate));
    log.info("Updated phone is: {}", valueToUpdate);
  }
}
