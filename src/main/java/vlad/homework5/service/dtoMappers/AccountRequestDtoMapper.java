package vlad.homework5.service.dtoMappers;

import vlad.homework5.DTO.account.AccountRequestDto;
import vlad.homework5.domain.Account;
import vlad.homework5.domain.Currency;
import vlad.homework5.domain.Customer;
import vlad.homework5.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRequestDtoMapper extends DtoMapperFacade<Account, AccountRequestDto>{

    @Autowired
    private CustomerService customerService;

    public AccountRequestDtoMapper(){
        super(Account.class, AccountRequestDto.class);
    }

    public void decorateEntity(final Account account, final AccountRequestDto dto){
        try {
            Customer c = customerService.getById(dto.getCustomerId());
            account.setCustomer(c);
            Currency[] cur = Currency.values();
            Currency cU = cur[dto.getCurrency()];
            account.setCurrency(cU);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("invalid customer or currency");
        }
    }
}
