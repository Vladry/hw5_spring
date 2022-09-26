package vlad.homework5.controller.employer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import vlad.homework5.DTO.employer.EnlistedEmployerDto;
import vlad.homework5.DTO.employer.EmployerRequestDto;
import vlad.homework5.DTO.employer.EmployerResponseDto;
import vlad.homework5.domain.Employer;
import vlad.homework5.service.dtoMappers.EmployerRequestDtoMapper;
import vlad.homework5.service.dtoMappers.EmployeeResponseDtoMapper;
import vlad.homework5.service.EmployerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class EmployerController {

    private final EmployerService service;
    private final EmployeeResponseDtoMapper respDtoMapper;
    private final EmployerRequestDtoMapper reqDtoMapper;

    EmployerController(EmployerService employerService,
                       EmployeeResponseDtoMapper employeeRespDtoMapper,
                       EmployerRequestDtoMapper employeeReqDtoMapper) {
        this.service = employerService;
        this.respDtoMapper = employeeRespDtoMapper;
        this.reqDtoMapper = employeeReqDtoMapper;
    }


    /***  SAVING endpoints  ***/
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/employers")
    public void saveEmployer(@Valid @RequestBody() EmployerRequestDto empReqDto) {
        Employer e = reqDtoMapper.convertToEntity(empReqDto);
        service.save(e);
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/employers/list")
    public void saveAll_fromSet(@Valid @RequestBody EnlistedEmployerDto lDto) {
        log.info("in saveAll_fromSet ->");
        List<EmployerRequestDto> lEmp = lDto.getList();
        Set<Employer> le = lEmp.stream().map(reqDtoMapper::convertToEntity)
                .collect(Collectors.toSet());
        service.saveAll_fromSet(le);
    }


    /*** RETRIEVE endpoints ***/
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/employers/all")
    public List<EmployerResponseDto> findAll() {
        log.info("in findAll ->");
        return service.findAll().stream().map(respDtoMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/employers/{id}")
    public EmployerResponseDto getEmployerById(@PathVariable("id") Long id) {
        log.info("in getEmployerById ->");
        Employer e = service.getById(id);
        return respDtoMapper.convertToDto(e);
    }


    /***  DELETE endpoints  ***/
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/employers")   //todo тут DTO mapping пока не реализую
    public void deleteEmployer(@RequestBody Employer e) {
        service.delete(e);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/employers/{id}")
    public void deleteEmployerById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/employers/list")
    public void deleteAllEmployers(@RequestBody EnlistedEmployerDto lDto) {
        List<EmployerRequestDto> le = lDto.getList();
        service.deleteAll(le.stream().map(reqDtoMapper::convertToEntity).collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/employers/all")
    public void deleteAllEmployersFromDB() {
        service.deleteAllEmployersFromDB();
    }
}
