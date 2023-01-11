package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Employee {
  private String name;
  private String position;
  private String[] phones;
  private List<Address> addresses;
  private Company company;

  @JsonIgnore
  public boolean isEmpty() {
    return this.equals(new Address());
  }

}

