package vlad.homework5.service;

import vlad.homework5.DAO.AccountDao;
import vlad.homework5.domain.Account;
import vlad.homework5.repository.AccountJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountDao accountDao;
    @Mock
    AccountJpaRepository accountJpaRepository;
    @InjectMocks
    AccountService accountService;

    @Test
    void check_putAmount_success() {
    when(accountDao.putAmount(any(String.class), any (Double.class))).thenReturn(true);
        boolean result = accountService.putAmount("1", 50.0);
        assertTrue(result);
    }

    @Test
    void drawAmount() {
        when(accountDao.drawAmount(any(String.class), any (Double.class))).thenReturn(true);
        boolean result = accountService.drawAmount("1", 50.0);
        assertTrue(result);
    }

    @Test
    void transferAmount() {
        when(accountDao.putAmount(any(String.class), any (Double.class))).thenReturn(true);
        when(accountDao.drawAmount(any(String.class), any (Double.class))).thenReturn(true);
        boolean result = accountService.transferAmount("1", "2", 50.0);
        assertTrue(result);
    }

    @Test
    void check_putAmount_fail() {
        when(accountDao.putAmount(any(String.class), any (Double.class))).thenReturn(false);
        boolean result = accountService.putAmount("1", 50.0);
        assertFalse(result);
    }

    @Test
    void drawAmount_fail() {
        when(accountDao.drawAmount(any(String.class), any (Double.class))).thenReturn(false);
        boolean result = accountService.drawAmount("1", 50.0);
        assertFalse(result);
    }

    @Test
    void transferAmount_fail() {
        when(accountDao.putAmount(any(String.class), any (Double.class))).thenReturn(false);
        when(accountDao.drawAmount(any(String.class), any (Double.class))).thenReturn(false);
        boolean result = accountService.transferAmount("1", "2", 50.0);
        assertFalse(result);
    }


    @Test
    void save() {
        Account acc = new Account();
        when(accountJpaRepository.save(any (Account.class))).thenReturn(acc);
        Account accResult = accountService.save(acc);
        assertEquals(acc, accResult);
    }

    @Test
    void findAll() {
        Account acc = new Account();
        when(accountJpaRepository.findAll()).thenReturn(newArrayList(List.of(acc)));
        List<Account> la = accountService.findAll();
        assertEquals(acc, la.get(0));
    }

    @Test
    void getById() {
        Account acc = new Account();
        when(accountDao.getById(any (Long.class))).thenReturn(Optional.of(acc));
        Optional<Account> accResultOpt = accountService.getById(1L);
        assertNotNull(accResultOpt.get());
        assertEquals(acc, accResultOpt.get());
    }
}