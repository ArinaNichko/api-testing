package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Address;
import models.Company;
import models.Employee;
import models.EmployeeModel;
import models.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONFileReader {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static Product readJSONFile(String path) {
        try {
            return objectMapper.readValue(new File(path), Product.class);
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

    public static String JSOnFileToString(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
