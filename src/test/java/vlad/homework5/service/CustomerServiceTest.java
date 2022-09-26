package vlad.homework5.service;

import vlad.homework5.DAO.CustomerDao;
import vlad.homework5.domain.Customer;
import vlad.homework5.repository.CustomerJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerDao customerDao;
    @Mock
    CustomerJpaRepository customerJpaRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    void check_getListAllFromPaged_success() {
        Customer c = new Customer();
        Page<Customer> pageCust = new PageImpl<>(List.of(c));
        when(customerJpaRepository.findAll(any(Pageable.class))).thenReturn(pageCust);
        List<Customer> custListRes = customerService.getListAllFromPaged(1, 1);
        assertNotNull(custListRes.get(0));
        assertEquals(c, custListRes.get(0));
    }

    @Test
    void check_getPagedAll_success() {
        Customer c = new Customer();
        Page<Customer> pageCust = new PageImpl<>(List.of(c));
        when(customerJpaRepository.findAll(any(Pageable.class))).thenReturn(pageCust);
        Page<Customer> custListRes = customerService.getPagedAll(1, 1);
        assertNotNull(custListRes.toList().get(0));
        assertEquals(c, custListRes.toList().get(0));
    }
}