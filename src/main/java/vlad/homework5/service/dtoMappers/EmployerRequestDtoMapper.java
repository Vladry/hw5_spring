package vlad.homework5.service.dtoMappers;

import vlad.homework5.DTO.employer.EmployerRequestDto;
import vlad.homework5.domain.Employer;
import org.springframework.stereotype.Service;


@Service
public class EmployerRequestDtoMapper extends DtoMapperFacade<Employer, EmployerRequestDto>{

    public EmployerRequestDtoMapper() {
        super(Employer.class, EmployerRequestDto.class);
    }

    @Override
    protected void decorateEntity(Employer entity, final EmployerRequestDto dto){}

    @Override
    protected void decorateDto(EmployerRequestDto dto, Employer entity){}
}
