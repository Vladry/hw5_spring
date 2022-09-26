package vlad.homework5.service;

import vlad.homework5.DAO.AccountDao;
import vlad.homework5.repository.AccountJpaRepository;
import vlad.homework5.domain.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    AccountDao accountDao;
    AccountJpaRepository accountJpaRepository;

    public AccountService(AccountJpaRepository accountRepository, AccountDao accountDao){
        this.accountJpaRepository = accountRepository;
        this.accountDao = accountDao;
    }


/*** TRANSFER methods ***/
    public boolean putAmount(String accNum, Double amount) {
        return accountDao.putAmount(accNum, amount);
    }

    public boolean drawAmount(String accNum, Double amount) {
        return accountDao.drawAmount(accNum, amount);
    }

    public boolean transferAmount(String from, String to, Double amount) {
        boolean putSuccess = false;
        boolean drawSuccess = accountDao.drawAmount(from, amount);
        if (drawSuccess) {
            putSuccess = accountDao.putAmount(to, amount);
        } else {
            accountDao.putAmount(from, amount);
            return false;
        }
        if (putSuccess) {
            return true;
        } else {
            accountDao.putAmount(from, amount);
            return false;
        }
    }

    public void saveAll(List<Account> entities) {
        System.out.println("in service.saveAll() ");
        accountJpaRepository.saveAll(entities);
    }


    public Account save(Account a){
        accountJpaRepository.save(a);
        return a;
    }

    public void delete(Account a) {
        accountJpaRepository.delete(a);
    }

    public void deleteAll(List<Account> entities) {
        accountJpaRepository.deleteAll(entities);
    }

    public List<Account> findAll() {
        return accountJpaRepository.findAll();
    }

    public void deleteById(Long id){
        accountJpaRepository.deleteById(id);}

    public Optional<Account> getById(Long id) {
        return accountDao.getById(id);
    }

}
