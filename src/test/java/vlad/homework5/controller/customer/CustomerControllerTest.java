package vlad.homework5.controller.customer;

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
import vlad.homework5.DTO.customer.CustomerResponseDto;
import vlad.homework5.domain.Customer;
import vlad.homework5.facade.CustomerFacade;
import vlad.homework5.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@ContextConfiguration(classes = CustomerController.class)
@Slf4j
public class CustomerControllerTest {

    @MockBean
    private CustomerService service;
    @MockBean
    private CustomerFacade facade;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void check_getAllPaged_success() throws Exception {
        Customer c = new Customer();
        CustomerResponseDto custRsDto = new CustomerResponseDto().setName("Vasya").setAge(40);
        List<Customer> lc = new ArrayList<>(List.of(c));
        when(service.getListAllFromPaged(any(Integer.class), any(Integer.class))).thenReturn(lc);
        when(facade.convertToDto(any(Customer.class))).thenReturn(custRsDto);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/all-paged")
                        .param("pagenumber", "1").param("pagesize", "2").contentType("application/json"))

                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fn").value("Vasya"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age", Matchers.is(40)))
                .andReturn();
        log.info("controller.getAllPaged() returned: " + result.getResponse().getContentAsString());
    }

    @Test
    public void check_findAll_success() throws Exception{
        Customer c = new Customer();
        List<Customer> lc = new ArrayList<>(List.of(c));
        CustomerResponseDto custRsDto = new CustomerResponseDto();
        when(service.findAll()).thenReturn(lc);
        when(facade.convertToDto(any(Customer.class))).thenReturn(custRsDto);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/all"))
                        .andExpect(status().is(200));


        // /customers/all"
        // List<CustomerResponseDto>
        //  List<Customer> lc = customerService.findAll();
    }

    @Test
    public void check_getById_success() throws Exception{
        Customer c = new Customer();
        when(service.getById(any(Long.class))).thenReturn(c);
        when(facade.convertToDto(any(Customer.class))).thenReturn(new CustomerResponseDto()
                .setEmail("vasya@puk").setAge(40));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("vasya@puk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(40));
    }
}