package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {
  private int id;
  private String name;
  private String description;
  private double price;
  @JsonProperty("category_id")
  private int categoryId;
  @JsonProperty("category_name")
  private String categoryName;

  public Product(String[] args) {
    this.id = Integer.parseInt(args[0]);
    this.name = args[1];
    this.description = args[2];
    this.price = Double.parseDouble(args[3]);
    this.categoryId = Integer.parseInt(args[4]);
  }
}