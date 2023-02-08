package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonPropertyOrder({ "id", "name", "description", "price", "category_id"})
public class Product {
  private int id;
  private String name;
  private String description;
  private double price;
  @JsonProperty("category_id")
  private int categoryId;
  @JsonProperty("category_name")
  private String categoryName;
}
