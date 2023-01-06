package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Employee  {
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

