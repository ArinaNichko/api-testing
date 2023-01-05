package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Employee  {
    String name;
    String position;
    String[] phones;
    Address[] addresses;
    Company company;

    @JsonIgnore
    public boolean isEmpty() {
        return this.equals(new Address());
    }

}

