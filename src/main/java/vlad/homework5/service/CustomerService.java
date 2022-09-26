package vlad.homework5.service;

import vlad.homework5.DAO.CustomerDao;
import vlad.homework5.exception.DataNotFoundException;
import vlad.homework5.exception.StoringDataException;
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


    public List<Customer> getListAllFromPaged(int pageNumber, int pageSize) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return customerJpaRepository.findAll(pageable).toList();
    }

    ;


    public void saveAll(List<Customer> entities) throws StoringDataException{
        try {
            customerJpaRepository.saveAll(entities);
        } catch (Exception e) {
            String exMessage = "in customerService.saveAll(List<Customer> entities)";
            throw new StoringDataException(exMessage);
        }
    }

    public void deleteAll(List<Customer> entities) {
        customerJpaRepository.deleteAll(entities);
    }

    public void deleteById(Long id) {
        customerJpaRepository.deleteById(id);
    }


    public Page<Customer> getPagedAll(int pageNumber, int pageSize) throws DataNotFoundException {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        try {
            return customerJpaRepository.findAll(pageable);
        } catch (Exception e) {
            String message = "in customerService.findAll(pageable). " + e.getMessage();
            throw new DataNotFoundException(message);
        }
    }

    public Customer update(Customer customer) {
        return customerJpaRepository.save(customer);
    }

    public void save(Customer c) throws StoringDataException{
        try {
            customerJpaRepository.save(c);
        } catch (Exception e) {
            String exMessage = "in customerService.save(Customer c)";
            throw new StoringDataException(exMessage);
        }
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
    public List<Customer> findAll() throws DataNotFoundException {
        try {
            return customerJpaRepository.findAll();
        } catch (Exception e) {
            String message = "in customerService.findAll(). " + e.getMessage();
            throw new DataNotFoundException(message);
        }
    }

    @Transactional(readOnly = true)
    public Customer getById(Long id) throws DataNotFoundException {
        try {
            return customerJpaRepository.getById(id);
        } catch (Exception e) {
            String message = "in customerService.getById(id). " + e.getMessage();
            throw new DataNotFoundException(message);
        }
    }
}
