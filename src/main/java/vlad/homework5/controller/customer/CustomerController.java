package vlad.homework5.controller.customer;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import vlad.homework5.DTO.Views;
import vlad.homework5.DTO.customer.CustomerRequestDto;
import vlad.homework5.DTO.customer.CustomerResponseDto;
import vlad.homework5.DTO.customer.listCustomerDto;
import vlad.homework5.domain.Customer;
import vlad.homework5.facade.CustomerFacade;
import vlad.homework5.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerFacade facade;

    public CustomerController(CustomerService service,
                              CustomerFacade facade) {
        this.customerService = service;
        this.facade = facade;
    }


    /*** RETRIEVE ***/

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/customers/all-paged")
    public List<CustomerResponseDto> getAllPaged(
            @RequestParam("pagenumber") int pageNumber,
            @RequestParam("pagesize") int pageSize
    ) {
//        Page<Customer> custPage = customerService.getPagedAll(pageNumber, pageSize);
        List<Customer> custList = customerService.getListAllFromPaged(pageNumber, pageSize);
        List<CustomerResponseDto> custRsDtoList = custList.stream().map(facade::convertToDto)
                .collect(Collectors.toList());
        return custRsDtoList;
    }

    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.Public.class)
    @GetMapping("/customers/all")
    public List<CustomerResponseDto> findAll() {
        List<Customer> lc = customerService.findAll();
        List<CustomerResponseDto> cRsDto = lc.stream().map(facade::convertToDto).collect(Collectors.toList());
        log.info("@GetMapping(\"/customers/all\")-> CustomerResponseDto: " + cRsDto);
        return cRsDto;
    }

    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.Public.class)
    @GetMapping("/customers/{id}")
    public CustomerResponseDto getById(@PathVariable("id") Long id) {
        return facade.convertToDto(customerService.getById(id));
    }


    /*** CREATE ***/
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/customers")
    public Customer save(
            @Valid @RequestBody CustomerRequestDto c) {
        Customer cEntity = facade.convertToEntity(c);
        customerService.save(cEntity);
        return cEntity;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("update/customers")
    public Customer update(
            @RequestBody CustomerRequestDto c) {
        Customer cEntity = facade.convertToEntity(c);
        customerService.save(cEntity);
        return cEntity;
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/customers/all")
    public void saveAll(
            @Valid @RequestBody listCustomerDto<CustomerRequestDto> lDto) {
        List<CustomerRequestDto> lc = lDto.getList();
        customerService.saveAll(lc.stream().map(facade::convertToEntity)
                .collect(Collectors.toList()));
    }


    /*** DELETE ***/
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/customers")
    public void delete(
            @RequestBody Customer c) {
        customerService.delete(c);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/customers/all")
    public void deleteAll(
            @RequestBody listCustomerDto<CustomerRequestDto> lDto) {
        List<CustomerRequestDto> lc = lDto.getList();
        customerService.deleteAll(lc.stream().map(facade::convertToEntity)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/customers/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        customerService.deleteById(id);
    }

}
