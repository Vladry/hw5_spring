package vlad.homework5.DTO.account;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListAccountRequestDto {
    private List<AccountRequestDto> list;

}
