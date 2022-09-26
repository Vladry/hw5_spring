package vlad.homework5.service.dtoMappers;

import vlad.homework5.DTO.employer.EmployerResponseDto;
import vlad.homework5.domain.Employer;
import org.springframework.stereotype.Service;


@Service
public class EmployeeResponseDtoMapper extends DtoMapperFacade<Employer, EmployerResponseDto>{

    public EmployeeResponseDtoMapper() {
        super(Employer.class, EmployerResponseDto.class);
    }

    @Override
    protected void decorateEntity(final Employer entity, final EmployerResponseDto dto){}

    @Override
    protected void decorateDto(final EmployerResponseDto dto, final Employer entity){}
}
