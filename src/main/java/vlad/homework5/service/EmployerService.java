package vlad.homework5.service;

import vlad.homework5.repository.EmployerJpaRepository;
import vlad.homework5.domain.Employer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployerService {

    private final EmployerJpaRepository JpaRepository;

    public EmployerService(EmployerJpaRepository JpaRepository) {
        this.JpaRepository = JpaRepository;
    }

    public Employer save(Employer obj) {
        return JpaRepository.save(obj);
    }

    public void deleteAll(List<Employer> entities) {
        JpaRepository.deleteAll(entities);
    }

    public void saveAll_fromSet(Set<Employer> entities) {
        JpaRepository.saveAll(entities);
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

    public List<Employer> findAll() {
        return JpaRepository.findAll();
    }

    public Employer getById(Long id) {
        return JpaRepository.getById(id);
    }
}

