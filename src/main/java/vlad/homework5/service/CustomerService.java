package vlad.homework5.service;

import vlad.homework5.DAO.CustomerDao;
import vlad.homework5.repository.CustomerJpaRepository;
import vlad.homework5.domain.Currency;
import vlad.homework5.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//@Slf4j
public class CustomerService {

    private final CustomerDao customerDao;
    private final CustomerJpaRepository customerJpaRepository;

    public CustomerService(CustomerDao customerDao, CustomerJpaRepository customerJpaRepository) {
        this.customerDao = customerDao;
        this.customerJpaRepository = customerJpaRepository;
    }



    public List<Customer> getListAllFromPaged(int pageNumber, int pageSize){
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return customerJpaRepository.findAll(pageable).toList();
    };


    public void saveAll(List<Customer> entities) {
        customerJpaRepository.saveAll(entities);
    }

    public void deleteAll(List<Customer> entities) {
        customerJpaRepository.deleteAll(entities);
    }

    public void deleteById(Long id) {
        customerJpaRepository.deleteById(id);
    }


    public Page<Customer> getPagedAll(int pageNumber, int pageSize){
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return customerJpaRepository.findAll(pageable);
    }

    public Customer update(Customer customer) {
        return customerJpaRepository.save(customer);
    }

    public void save(Customer c) {
        customerJpaRepository.save(c);
    }

    public Customer createAccount(Currency currency, Long id) {
        return customerDao.createAccount(currency, id);
    }

    public Customer deleteAccount(String accNumber, Long id) {
        return customerDao.deleteAccount(accNumber, id);
    }

    public void delete(Customer c) {
        customerJpaRepository.delete(c);
    }


    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerJpaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer getById(Long id) {
        return customerJpaRepository.getById(id);
    }
}
