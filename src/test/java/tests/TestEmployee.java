package tests;

import configurations.BaseTest;
import models.Address;
import models.Company;
import models.Employee;
import models.EmployeeModel;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.JSONFileReader.*;
import static utils.JsonHelper.updateFieldsByPath;

public class TestEmployee extends BaseTest {

    private final Employee employee = readJSONFileEmployee(updateEmployeePath);

    @Test
    public void changeCountry() {

        Employee result = changeValue(employee, changeCountryEmployeePath, changeCountryEmployeeValue);
        Employee expected = readJSONFileEmployee(updateEmployeePath);
        expected.getAddresses().get(0).setCountry(changeCountryEmployeeValue);

        assertThat(result, equalTo(expected));
    }


    @Test
    public void changeName() {

        Employee actual = changeValue(employee, changeNameEmployeePath, changeNameEmployeeValue);
        Employee expected = readJSONFileEmployee(updateEmployeePath);
        expected.setName(changeNameEmployeeValue);

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void changePosition() {

        Employee actual = changeValue(employee, changePositionEmployeePath, changePositionEmployeeValue);
        Employee expected = readJSONFileEmployee(updateEmployeePath);
        expected.setPosition(changePositionEmployeeValue);

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void changeCity() {

        Employee actual = changeValue(employee, changeCityEmployeePath, changeCityEmployeeValue);
        Employee expected = readJSONFileEmployee(updateEmployeePath);
        expected.getAddresses().get(0).setCity(changeCityEmployeeValue);

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void changeCompanyName() {
        Employee actualEmployee = changeValue(employee, changeCompanyNamePath, changeCompanyNameValue);
        Employee expectedEmployee = readJSONFileEmployee(updateEmployeePath);
        expectedEmployee.getCompany().setName(changeCompanyNameValue);

        assertThat(actualEmployee, equalTo(expectedEmployee));
    }


    @Test
    public void changeCompanyId() {
        Employee actual = changeValue(employee, changeCompanyIdPath, changeCompanyIdValue);
        Employee expected = readJSONFileEmployee(updateEmployeePath);
        expected.getCompany().setId(changeCompanyIdValue);

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void setAddressEmployee() {
        List<Address> addresses = List.of(
                new Address("Burgas", "Bulgaria"),
                new Address("Zaporizhia", "Ukraine"));

        Employee actual = changeAddressEmployee(employee, addresses);
        Employee expected = readJSONFileEmployee(updateEmployeePath);
        expected.setAddresses(addresses);

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void setPhoneEmployee() {
        String[] phones = {"1235", "4567"};
        Employee actual = changePhoneEmployee(employee, phones);
        Employee expected = readJSONFileEmployee(updateEmployeePath);
        expected.setPhones(phones);

        assertThat(actual, equalTo(expected));
    }


    @Test
    public void setCompanyEmployee() {
        Company company = new Company("QA", "6758");
        Employee actual = changeCompanyEmployee(employee, company);
        Employee expected = readJSONFileEmployee(updateEmployeePath);
        expected.setCompany(company);

        assertThat(actual, equalTo(expected));
    }

  @Test
  public void myNewTest() {
    assertThat(changeValue(employee, "name", "Andrii").getName(), equalTo("Andrii"));
    assertThat(changeValue(employee, "position", "manual QA").getPosition(), equalTo("manual QA"));
    assertThat(
        changeValue(employee, "addresses[0].city", "Lviv").getAddresses().get(0).getCity(),
        equalTo("Lviv"));

    //    Doesn't work!!!
    //    assertThat(changeValue(employee, "company.name", "SoftServe").getCompany().getName(),
    // equalTo("SoftServe"));

    assertThat(
        updateFieldsByPath(updateEmployeePath, "employee.name", "Anna", EmployeeModel.class)
            .getEmployee()
            .getName(),
        equalTo("Anna"));

    assertThat(
        updateFieldsByPath(
                updateEmployeePath, "employee.company.name", "SoftServe", EmployeeModel.class)
            .getEmployee()
            .getCompany()
            .getName(),
        equalTo("SoftServe"));

    assertThat(
        updateFieldsByPath(
                updateEmployeePath, "employee.addresses[0].city", "Lviv", EmployeeModel.class)
            .getEmployee()
            .getAddresses()
            .get(0)
            .getCity(),
        equalTo("Lviv"));

    assertThat(
        updateFieldsByPath(
                updateEmployeePath, "employee.phones", List.of("testPhone"), EmployeeModel.class)
            .getEmployee()
            .getPhones()[0],
        equalTo("testPhone"));
  }
}
