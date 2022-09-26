package vlad.homework5.DTO.customer;

import vlad.homework5.DTO.employer.EmployerRequestDto;
import vlad.homework5.domain.AbstractEntity;
import vlad.homework5.domain.Account;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
//@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto extends AbstractEntity {

    @Size(min = 3, message = "name must be longer!")
    private String name;
    @Email
    private String email;
    @Min(18)
    @Max(100)
    private Integer age;
    @Pattern(regexp = "(\\+38|0)[0-9]{9}]")   // https://www.baeldung.com/java-regex-validate-phone-numbers
    private String phoneNumber;
//    @NotBlank
//    private String password;
    private Set<EmployerRequestDto> employers;
    private Set<Account> accounts;



/*    public CustomerRequestDto(String name, String email, int age, String suggestedPassword) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.accounts = new HashSet<>();
        this.suggestedPassword = suggestedPassword;
    }

   public CustomerRequestDto(String name) {
        this.name = name;
    }

    public CustomerRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public CustomerRequestDto(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public CustomerRequestDto(Long id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }*/


}
