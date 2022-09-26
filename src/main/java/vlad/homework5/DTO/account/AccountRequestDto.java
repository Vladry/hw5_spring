package vlad.homework5.DTO.account;

import vlad.homework5.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto extends AbstractEntity {

    @Min(0)
    @Max(4)
    private int currency;
    @Min(0)
    private Double balance;
    @Min(1)
    private Long customer_id;
    private String account_id;
    private String number;
}

