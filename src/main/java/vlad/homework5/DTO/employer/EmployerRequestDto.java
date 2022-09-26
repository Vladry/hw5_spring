package vlad.homework5.DTO.employer;

import vlad.homework5.DTO.customer.CustomerRequestDto;
import vlad.homework5.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRequestDto extends AbstractEntity {
    @Size(min = 3, message = "name must be longer")
    private String name;
    @Size(min = 3, message = "street must be longer")
    private String street;
    @Size(min = 3, message = "address must be longer")
    private String address;
    private Set<CustomerRequestDto> customers;


}
