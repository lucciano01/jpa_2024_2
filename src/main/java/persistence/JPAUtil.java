package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

public class JPAUtil {

    private EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("cardapio");

    private EntityManager entityManager;

    public EntityManager getEntityManager(){
        if(Objects.isNull(entityManager)){
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }
}
