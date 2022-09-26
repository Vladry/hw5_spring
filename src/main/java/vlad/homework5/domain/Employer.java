package vlad.homework5.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employers")
public class Employer extends AbstractEntity {
    private String name;
    private String street;
    private String address;

    @JsonIgnore   //без этого не будут из базы выдаваться customers!
    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "customers_employers")  -эту строку нельзя, т.к. она уже есть в Customer!
    private Set<Customer> customers = new HashSet<>();


}
