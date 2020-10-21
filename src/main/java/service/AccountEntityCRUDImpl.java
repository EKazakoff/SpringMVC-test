package service;

// import jpa.AccountsEntity;
import com.test.jpa.AccountsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;

import static org.hibernate.jpa.AvailableSettings.PERSISTENCE_UNIT_NAME;

@Service
public class AccountEntityCRUDImpl implements AccountEntityCRUD {
    private static final EntityManager entityManager;

    static {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("AccountsUnit");
            entityManager = factory.createEntityManager();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public void create(AccountsEntity ae) {
        try {
            entityManager.getTransaction().begin();
            this.entityManager.persist(ae);
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public AccountsEntity read(Integer id) {
        try {
            entityManager.getTransaction().begin();
            AccountsEntity ae = this.entityManager.find(AccountsEntity.class, id); //id - primary key
            return ae;
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void update(AccountsEntity ae) {
        try {
            entityManager.getTransaction().begin();
            this.entityManager.merge(ae);
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            entityManager.getTransaction().begin();
            this.entityManager.remove(id);
        } finally {
            entityManager.getTransaction().commit();
        }
    }

    @PreDestroy
    public void predestroy() {
        this.entityManager.close();
    }
}


