package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONFileReader {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static Product readJSONFile(String path) {
        try {
            return objectMapper.readValue(new File(path), Product.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String JSOnFileToString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Employee readJSONFileEmployee(String path) {
        try {
            return objectMapper.readValue(new File(path), EmployeeModel.class).getEmployee();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Employee changeCity(String path, String change, int number) {

        Employee employee = readJSONFileEmployee(path);
        employee.getAddresses()[number].setCity(change);
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
            case "city" -> employee.getAddresses()[index].setCity(newValue);
            case "country" -> employee.getAddresses()[index].setCountry(newValue);
            case "id" -> employee.getCompany().setId(newValue);
        }

        return employee;
    }


    public static Employee changeAddressEmployee(Employee employee, Address[] newValue) {

        employee.setAddresses(newValue);
        return employee;

    }

    public static Employee changeCompanyEmployee(Employee employee, Company newValue) {

        employee.setCompany(newValue);
        return employee;

    }
}
