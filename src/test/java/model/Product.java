package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    int id;
    String name;
    String description;
    double price;
    int categoryId;
    String categoryName;

    public Product(Object readJSONFile) {
    }


}
