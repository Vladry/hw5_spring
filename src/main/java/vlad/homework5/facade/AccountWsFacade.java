package vlad.homework5.facade;

import org.springframework.stereotype.Component;
import vlad.homework5.DTO.account.AccountRequestDto;
import vlad.homework5.DTO.account.AccountWsRsDto;
import vlad.homework5.DTO.customer.CustomerRequestDto;
import vlad.homework5.DTO.customer.CustomerResponseDto;
import vlad.homework5.domain.Account;
import vlad.homework5.domain.Customer;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountWsFacade extends GeneralFacade<Account, AccountRequestDto, AccountWsRsDto> {
    public AccountWsFacade() {
        super(Account.class, AccountWsRsDto.class);
    }

    @Override
    protected void decorateDto(AccountWsRsDto dto, Account entity) {
        dto.setCustomerName(entity.getCustomer().getName());
    }

    @Override
    protected void decorateEntity(Account entity, AccountRequestDto dto) {
        super.decorateEntity(entity, dto);
    }
}
