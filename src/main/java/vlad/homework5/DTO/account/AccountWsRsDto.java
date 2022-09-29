package vlad.homework5.DTO.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vlad.homework5.domain.AbstractEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountWsRsDto {

    String number;
    String currency;
    Double balance;
    String customerName;
}
