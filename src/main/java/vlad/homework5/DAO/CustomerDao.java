package vlad.homework5.DAO;

import vlad.homework5.domain.Account;
import vlad.homework5.domain.Currency;
import vlad.homework5.domain.Customer;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Repository
public class CustomerDao  {

    @PersistenceUnit
    EntityManagerFactory emf;


     public Customer createAccount(Currency currency, Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer cust = em.find(Customer.class, id);
            if (cust != null) {
                em.getTransaction().begin();
                cust.getAccounts().add(new Account(currency, cust));
                em.merge(cust);
                em.getTransaction().commit();
                em.close();
                return cust;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public Customer deleteAccount(String accNumber, Long id) {
        EntityManager em = emf.createEntityManager();

        Customer cust = em.find(Customer.class, id);
        if (cust != null) {
            Account acc = null;
            for (Account account : cust.getAccounts()) {
                if (account.getNumber().equals(accNumber)) {
                    acc = account;
                    break;
                }
            }
            cust.getAccounts().remove(acc);
            em.getTransaction().begin();
            em.merge(cust);
            em.getTransaction().commit();
            em.close();
            return cust;
        }
        em.close();
        return null;
    }

}
