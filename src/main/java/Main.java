
import com.test.jpa.AccountsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    private static final EntityManager entityManager;

    static {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("AccountsUnit");
            entityManager = factory.createEntityManager();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void main(final String[] args) throws Exception {
        try {
            entityManager.getTransaction().begin();
            AccountsEntity ae = entityManager.find(AccountsEntity.class, 1);
            AccountsEntity ae12 = entityManager.find(AccountsEntity.class, 12);
            System.out.println(ae);
            System.out.println(ae12);
            ae.setEmail("blabla1@bla.com");
            ae12.setEmail("blabla1blaBLA@bla.com");

            List<AccountsEntity> allData= entityManager.createQuery("Select t from " + AccountsEntity.class.getSimpleName() + " t").getResultList();
            System.out.println(allData);
//entityManager.flush();

/*entityManager.getTransaction().commit();
entityManager.getTransaction().begin();*/
//Some actions
            entityManager.getTransaction().commit();//rollback();
        } finally {
            entityManager.close();
        }

    }
}

