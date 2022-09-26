package vlad.homework5.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
//@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(of = {"name", "email", "age", "phoneNumber"})


@Table(name = "customers")
public class Customer extends AbstractEntity {


    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name= "email", length = 40, nullable = true)
    private String email;
    private Integer age;
    private String phoneNumber;
    @Column(name = "suggestedPassword", length = 30, nullable = false)
//    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customers_employers")
    private Set<Employer> employers = new HashSet<>();

    @OneToMany(mappedBy="customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

}
