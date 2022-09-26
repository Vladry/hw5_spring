package vlad.homework5.DTO.employer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EnlistedEmployerDto {

    private List<EmployerRequestDto> list;


}
