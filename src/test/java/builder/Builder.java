package builder;

public interface Builder {
  void setId(int id);
  void setName(String name);
  void setDescription(String description);
  void setCategoryId(int categoryId);
  void setCategoryName(String categoryName);

  void setPrice(double price);
}
