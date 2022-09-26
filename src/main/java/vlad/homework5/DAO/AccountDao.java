package vlad.homework5.DAO;

import vlad.homework5.domain.Account;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountDao implements Dao<Account> {

    @PersistenceUnit
    EntityManagerFactory emf;

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Account a = em.find(Account.class, id);
            System.out.println("removing account:  a  " + a.getNumber());
            em.getTransaction().begin();
            em.remove(a);
            em.flush();
            em.clear();
            em.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    @Override
    public boolean delete(Account a){return false;}

    @Override
    public Optional<Account> getById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Account.class, id) );
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean putAmount(String accNum, Double amount) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select a from Account a where a.number = :accNum")
                    .setParameter("accNum", accNum);
            Account acc = (Account) query.getSingleResult();
            if (acc != null) {
                em.getTransaction().begin();
                acc.setBalance(acc.getBalance() + amount);
                em.merge(acc);
                em.close();
                em.getTransaction().commit();
                return true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return false;
    }

    public boolean drawAmount(String accNum, Double amount) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select a from Account a where a.number = :accNum")
                    .setParameter("accNum", accNum);
            Account acc = (Account) query.getSingleResult();
            if (acc != null && acc.getBalance() >= amount) {
                em.getTransaction().begin();
                acc.setBalance(acc.getBalance() - amount);
                em.merge(acc);
                em.close();
                em.getTransaction().commit();
                return true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return false;
    }

    @Override
    public Account save(Account obj) {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return obj;
    }

    @Override
    public List<Account> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("select a from Account a").getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }


    }

}
