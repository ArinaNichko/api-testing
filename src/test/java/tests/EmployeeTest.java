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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.ObjectsArgumentsProvider;

@Slf4j
public class EmployeeTest extends BaseTest {

  @ParameterizedTest
  @ArgumentsSource(ObjectsArgumentsProvider.class)
  void updateEmployeeName(String employeeName) {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.name", employeeName);

    log.info("Updated employee name is: {}", employeeName);
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getName(),
            equalTo(employeeName));
  }

  @Test
  public void updateAddressCity() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String valueToUpdate = "Lviv";
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.addresses[0].city", valueToUpdate);

    log.info("Updated address city is: {}", valueToUpdate);
    assertThat(
            readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getAddresses()
                    .get(0)
                    .getCity(),
            equalTo(valueToUpdate));
  }

  @ParameterizedTest
  @MethodSource("utils.DataUtils#provideObjectsData")
  void updateAddressCity(String addressCity) {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.addresses[0].city", addressCity);

    log.info("Updated address city is: {}", addressCity);
    assertThat(readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getAddresses()
                    .get(0)
                    .getCity(),
            equalTo(addressCity));
  }

  @Test
  public void updatePhones() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.phones", List.of("testPhone"));

    log.info("Updated phones are: {}", List.of("testPhone"));
    assertThat(
            readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getPhones()[0],
            equalTo("testPhone"));

  }

  @ParameterizedTest
  @ValueSource(strings = {"SoftServe", "GlobalLogic", "Luxoft"})
   void updateCompanyName(String companyName) {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.company.name", companyName);

    log.info("Updated company name is: {}", companyName);
    assertThat(
            readJsonStringAsObject(jsonString, EmployeeModel.class)
            .getEmployee()
            .getCompany()
            .getName(),
        equalTo(companyName));
  }

  @Test
  public void updatePhone() {
    String pathToJsonFile = readJsonFileAsString(updateEmployeePath);
    String phone = "4554";
    String jsonString = updateFieldByPath(pathToJsonFile, "employee.phones[0]", phone);

    log.info("Updated phone is: {}", phone);
    assertThat(
            readJsonStringAsObject(jsonString, EmployeeModel.class)
                    .getEmployee()
                    .getPhones()[0],
            equalTo(phone));
  }
}
