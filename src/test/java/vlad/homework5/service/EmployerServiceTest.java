package vlad.homework5.service;

import vlad.homework5.domain.Employer;
import vlad.homework5.repository.EmployerJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployerServiceTest {

    @Mock
    EmployerJpaRepository repo;

    @InjectMocks
    EmployerService employerService;

    @Test
    void save() {
        Employer e = new Employer();
        when(repo.save(any (Employer.class))).thenReturn(e);
        Employer eResult = employerService.save(e);
        assertEquals(e, eResult);
    }


    @Test
    void findAll() {
        Employer e = new Employer();
        when(repo.findAll()).thenReturn(new ArrayList<Employer>(List.of(e)));
        List<Employer> leRes = employerService.findAll();
        assertNotNull(leRes.get(0));
        assertEquals(e, leRes.get(0));
    }

    @Test
    void getById() {
        Employer e = new Employer();
        when(repo.getById(any(Long.class))).thenReturn(e);
        Employer eRes = employerService.getById(1L);
        assertEquals(e, eRes);
    }
}