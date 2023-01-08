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
  String name;
  String position;
  String[] phones;
  List<Address> addresses;
  Company company;

  @JsonIgnore
  public boolean isEmpty() {
    return this.equals(new Address());
  }

}

