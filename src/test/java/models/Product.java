package models;

import builder.ProductBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonDeserialize(builder = ProductBuilder.class)
@EqualsAndHashCode
@JsonPropertyOrder({"id", "name", "description", "price", "category_id"})
public class Product {
  private final int id;
  private final String name;
  private final String description;
  private final double price;
  @JsonProperty("category_id")
  private final int categoryId;
  @JsonProperty("category_name")
  private final String categoryName;

  public Product(int id, String name, String description, double price, int categoryId, String categoryName)  {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }

  public double getPrice() {
    return price;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public String getName() {
    return name;
  }
}
