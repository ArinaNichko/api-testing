package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Company {

    private String name;
    private String id;

    @JsonIgnore
    public boolean isEmpty() {
        return this.equals(new Company());
    }
}
