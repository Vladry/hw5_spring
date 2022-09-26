package vlad.homework5.facade;

import vlad.homework5.DTO.customer.CustomerRequestDto;
import vlad.homework5.DTO.customer.CustomerResponseDto;
import vlad.homework5.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerFacade extends GeneralFacade<Customer, CustomerRequestDto, CustomerResponseDto> {
    public CustomerFacade() {
        super(Customer.class, CustomerResponseDto.class);
    }

    @Override
    protected void decorateDto(CustomerResponseDto dto, Customer entity) {
        List<String> accNumberList = entity.getAccounts().stream()
                .map(a-> a.getCurrency().toString()).collect(Collectors.toList());
        String accNumberString = accNumberList.toString();
        dto.setAccounts_currency(accNumberString);
    }

    @Override
    protected void decorateEntity(Customer entity, CustomerRequestDto dto) {
        super.decorateEntity(entity, dto);
    }
}
