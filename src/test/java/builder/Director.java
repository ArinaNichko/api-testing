package builder;

public class Director {

  public static void constructSimpleProduct(Builder builder) {
    builder.setId(10);
    builder.setName("Cross-Back Training Tank");
  }

  public static void constructComplexProduct(Builder builder) {
    builder.setId(10);
    builder.setName("Cross-Back Training Tank");
    builder.setDescription("The most awesome phone of 2013!");
    builder.setPrice(299.00);
    builder.setCategoryId(10);
  }


  public static void constructProductWithoutDescription(Builder builder) {
    builder.setId(10);
    builder.setName("Cross-Back Training Tank");
    builder.setPrice(299.00);
    builder.setCategoryId(10);
  }
}
