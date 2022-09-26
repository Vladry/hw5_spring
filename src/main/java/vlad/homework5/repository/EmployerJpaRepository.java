package vlad.homework5.repository;

import vlad.homework5.domain.Employer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployerJpaRepository extends JpaRepository<Employer, Long> {
}
