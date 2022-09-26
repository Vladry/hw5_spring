package vlad.homework5.DTO.account;

import vlad.homework5.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto extends AbstractEntity {

    String number;
    String currency;
    Double balance;

    Long customer_id;

    public Long getId(){
        return id;
    }


}
