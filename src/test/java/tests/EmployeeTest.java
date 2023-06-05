package tests;

import static enums.ResourcePath.UPDATE_EMPLOYEE_JSON;
import static enums.ResourcePath.UPDATE_EMPLOYEE_XML;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.FileHelper.readJsonFileAsString;
import static utils.FileHelper.readJsonStringAsObject;

import configurations.BaseTest;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import models.Employee;
import models.EmployeeModel;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.ObjectsArgumentsProvider;
import utils.UpdateJsonHelper;
import utils.UpdateXmlHelper;
import utils.XMLAdapter;

@Slf4j
public class EmployeeTest extends BaseTest {
  private static final int FIRST = 0;
  private static final UpdateJsonHelper JSON_HELPER = new UpdateJsonHelper();

  @ParameterizedTest
  @ArgumentsSource(ObjectsArgumentsProvider.class)
  public void updateEmployeeName(String expectedEmployeeName) {
    String pathToJsonFile = readJsonFileAsString(UPDATE_EMPLOYEE_JSON.getPath());

    String jsonString = JSON_HELPER.updateFieldByPath(pathToJsonFile, "employee.name", expectedEmployeeName);
    Employee actualEmployee = readJsonStringAsObject(jsonString, EmployeeModel.class).getEmployee();

    assertThat("Actual Employee: " + actualEmployee,
            actualEmployee.getName(),
            equalTo(expectedEmployeeName));
  }

  @Test
  public void updateAddressCity() {
    String pathToJsonFile = readJsonFileAsString(UPDATE_EMPLOYEE_JSON.getPath());

    String expectedAddressCity = "Lviv";
    String jsonString = JSON_HELPER.updateFieldByPath(
            pathToJsonFile, "employee.addresses[0].city", expectedAddressCity);
    Employee actualEmployee = readJsonStringAsObject(jsonString, EmployeeModel.class).getEmployee();

    assertThat("Actual Employee: " + actualEmployee,
            actualEmployee.getAddresses().get(FIRST).getCity(),
            equalTo(expectedAddressCity));
  }

  @ParameterizedTest
  @MethodSource("utils.DataUtils#provideObjectsData")
  public void updateAddressCity(String expectedAddressCity) {
    String pathToJsonFile = readJsonFileAsString(UPDATE_EMPLOYEE_JSON.getPath());

    String jsonString = JSON_HELPER.updateFieldByPath(
            pathToJsonFile, "employee.addresses[0].city", expectedAddressCity);
    Employee actualEmployee = readJsonStringAsObject(jsonString, EmployeeModel.class).getEmployee();

    assertThat("Actual Employee: " + actualEmployee,
            actualEmployee.getAddresses().get(FIRST).getCity(),
            equalTo(expectedAddressCity));
  }

  @Test
  public void updatePhones() {
    String pathToJsonFile = readJsonFileAsString(UPDATE_EMPLOYEE_JSON.getPath());

    String jsonString = JSON_HELPER.updateFieldByPath(
            pathToJsonFile, "employee.phones", List.of("testPhone"));
    Employee actualEmployee = readJsonStringAsObject(jsonString, EmployeeModel.class).getEmployee();

    assertThat("Actual Employee: " + actualEmployee,
            actualEmployee.getPhones()[FIRST],
            equalTo("testPhone"));
  }

  @ParameterizedTest
  @ValueSource(strings = {"SoftServe", "GlobalLogic", "Luxoft"})
  public void updateCompanyName(String expectedCompanyName) {
    String pathToJsonFile = readJsonFileAsString(UPDATE_EMPLOYEE_JSON.getPath());

    String jsonString = JSON_HELPER.updateFieldByPath(
            pathToJsonFile, "employee.company.name", expectedCompanyName);
    Employee actualEmployee = readJsonStringAsObject(jsonString, EmployeeModel.class).getEmployee();

    assertThat("Actual Employee: " + actualEmployee,
            actualEmployee.getCompany().getName(),
            equalTo(expectedCompanyName));
  }

  @Test
  public void updatePhone() {
    String pathToJsonFile = readJsonFileAsString(UPDATE_EMPLOYEE_JSON.getPath());

    String expectedPhone = "4554";
    String jsonString = JSON_HELPER.updateFieldByPath(
            pathToJsonFile, "employee.phones[0]", expectedPhone);
    Employee actualEmployee = readJsonStringAsObject(jsonString, EmployeeModel.class).getEmployee();

    assertThat("Actual Employee: " + actualEmployee,
            actualEmployee.getPhones()[FIRST],
            equalTo(expectedPhone));
  }

  @Test
  public void updateNameWithAdapter() {
    String expectedName = "Arina";
    UpdateXmlHelper xmlHelper = new UpdateXmlHelper();
    XMLAdapter adapter = new XMLAdapter(xmlHelper);

    String jsonString = adapter.updateFieldByPath(
            UPDATE_EMPLOYEE_XML.getPath(), "employee.name", expectedName, EmployeeModel.class);
    Employee actualEmployee = readJsonStringAsObject(jsonString, EmployeeModel.class).getEmployee();

    assertThat("Actual Employee: " + actualEmployee,
            actualEmployee.getName(),
            equalTo(expectedName));
  }
}

