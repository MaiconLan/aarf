package jpautil;

import utils.Unit;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JPAUtil {

    private static EntityManagerFactory factory = null;

    public static EntityManager getEntityManager() {
        if (factory == null) {

        }

        return factory.createEntityManager();
    }

    public static Object getPrimaryKey(Object entity) {
        return factory.getPersistenceUnitUtil().getIdentifier(entity);
    }

}
