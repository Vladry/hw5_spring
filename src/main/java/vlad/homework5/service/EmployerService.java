package vlad.homework5.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vlad.homework5.domain.Employer;
import vlad.homework5.exception.DataNotFoundException;
import vlad.homework5.exception.StoringDataException;
import vlad.homework5.repository.EmployerJpaRepository;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployerService {

    private final EmployerJpaRepository JpaRepository;

    public EmployerService(EmployerJpaRepository JpaRepository) {
        this.JpaRepository = JpaRepository;
    }

    public Employer save(Employer obj) throws StoringDataException {
        Employer e = null;
        try {
            e = JpaRepository.save(obj);
        } catch (Exception ex) {
            String exMessage = "in employerService.save(employer)";
            throw new StoringDataException(exMessage);
        }
        return e;
    }

    public void deleteAll(List<Employer> entities) {
        JpaRepository.deleteAll(entities);
    }

    public void saveAll_fromSet(Set<Employer> entities) throws StoringDataException {
        try {
            JpaRepository.saveAll(entities);
        } catch (Exception e) {
            String exMessage = "in employerService.saveAll_fromSet(Set<Employer> entities)";
            throw new StoringDataException(exMessage);
        }
        // этот метод был бы более правильным в таком виде:
//    public Collection<Employer> saveAll_fromSet(Set<Employer> entities) {
//        return JpaRepository.saveAll(entities);
//    }
    }

    public boolean deleteById(Long id) {
        try {
            JpaRepository.deleteById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void deleteAllEmployersFromDB() {
        JpaRepository.deleteAll();
    }

    public boolean delete(Employer obj) {
        try {
            JpaRepository.delete(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveAll(List<Employer> entities) {
        JpaRepository.saveAll(entities);
    }

    public List<Employer> findAll() throws DataNotFoundException {
        try {
            return JpaRepository.findAll();
        } catch (Exception e) {
            String message = "in employerService.findAll(). " + e.getMessage();
            throw new DataNotFoundException(message);
        }
    }

    public Employer getById(Long id) throws DataNotFoundException {
        try {
            return JpaRepository.getById(id);
        } catch (Exception e) {
            String message = "in employerService.getById(Long id). " + e.getMessage();
            throw new DataNotFoundException(message);
        }
    }
}

