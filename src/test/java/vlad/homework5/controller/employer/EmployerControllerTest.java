package vlad.homework5.controller.employer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vlad.homework5.DTO.employer.EmployerRequestDto;
import vlad.homework5.DTO.employer.EmployerResponseDto;
import vlad.homework5.DTO.employer.EnlistedEmployerDto;
import vlad.homework5.domain.Employer;
import vlad.homework5.service.EmployerService;
import vlad.homework5.service.dtoMappers.EmployeeResponseDtoMapper;
import vlad.homework5.service.dtoMappers.EmployerRequestDtoMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(EmployerController.class)
@ContextConfiguration(classes = {EmployerController.class, EmployerService.class})
@RunWith(SpringRunner.class)
public class EmployerControllerTest {

    @MockBean
    private EmployerService service;
    @MockBean
    private EmployeeResponseDtoMapper respDtoMapper;
    @MockBean
    private EmployerRequestDtoMapper reqDtoMapper;
    @Autowired
    MockMvc mockMvc;


    @Test
    public void check_saveAllEmployers_success() throws Exception {
        EnlistedEmployerDto enlistedEmpDto = new EnlistedEmployerDto();
        List<EmployerRequestDto> lEmp = new ArrayList<>(List.of(new EmployerRequestDto()));
        Employer e = new Employer();
        Set<Employer> se = new HashSet<>(List.of(e));
        enlistedEmpDto.setList(lEmp);
        String enlistedEmployerDtoJsonStr = new ObjectMapper().writer().writeValueAsString(enlistedEmpDto);
        log.info("passed arg to controller enlistedEmployerDtoJsonStr: "+ enlistedEmployerDtoJsonStr);

        when(reqDtoMapper.convertToEntity(any(EmployerRequestDto.class))).thenReturn(e);
//        doNothing().when(service.saveAll_fromSet(anySet())); // -так писать только когда saveAll_fromSet что-то возвращает!
        doNothing().when(service).saveAll_fromSet(anySet()); //когда saveAll_fromSet ничего не возвращает - строчка выше не сработает.

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/employers/list")
                        .contentType("application/json").content(enlistedEmployerDtoJsonStr))
                .andExpect(status().is(200))
                .andReturn();
        log.info("controller returned: "+result.getResponse().getContentAsString());
    }

    @Test
    public void findAll() throws Exception{
        Employer e = new Employer();
        List<Employer> le = new ArrayList<>(List.of(e));
        EmployerResponseDto eReDto = new EmployerResponseDto().setName("Vasya");
//        List<EmployerResponseDto> leDto = new ArrayList<>(List.of(eReDto));
        when(service.findAll()).thenReturn(le);
        when(respDtoMapper.convertToDto(any(Employer.class))).thenReturn(eReDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/employers/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Vasya"))
                );
    }

    @Test
    public void getEmployerById() throws Exception{
        Employer e = new Employer();
        EmployerResponseDto eReDto = new EmployerResponseDto().setName("Vasya");
        when(service.getById(anyLong())).thenReturn(e);
        when(respDtoMapper.convertToDto(e)).thenReturn(eReDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/employers/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Vasya")));
    }
}