package models;

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
    int category_id;
    String category_name;

}