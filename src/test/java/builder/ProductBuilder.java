package builder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import models.Product;

@JsonPOJOBuilder(withPrefix = "set")
public class ProductBuilder implements Builder {
  private int id;
  private String name;
  private String description;
  private double price;

  @JsonProperty("category_id")
  private  int categoryId;
  @JsonProperty("category_name")
  private  String categoryName;

  public Product build() {
    return new Product(id, name, description, price, categoryId, categoryName);
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  @Override
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  @Override
  public void setPrice(double price) {
    this.price = price;
  }
}
