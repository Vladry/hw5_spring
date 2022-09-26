package vlad.homework5.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import vlad.homework5.domain.SysUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
@RequiredArgsConstructor
public class UserRepositoryEm {

    private final EntityManagerFactory emf;

    public SysUser saveEntity(SysUser user){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }
}
