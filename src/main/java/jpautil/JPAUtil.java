package jpautil;

import utils.Unit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JPAUtil {

    private static EntityManagerFactory factory = null;

    private static synchronized void loadInstance(String usuario, String senha, String ssl, String unidade, String ip) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            map.put("javax.persistence.jdbc.user", usuario);
            map.put("javax.persistence.jdbc.password", senha);
            map.put("javax.persistence.jdbc.ssl", ssl);
            map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            map.put("hibernate.show_sql", "false");
            map.put("javax.persistence.jdbc.url", ip);

            factory = Persistence.createEntityManagerFactory(unidade, map);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        if (factory == null) {
            Unit unit = new Unit();
            String ip = "jdbc:postgresql://" + unit.getIpBaseDados() + ":5432/" + unit.getNomeBaseDados();
            loadInstance(unit.getUsuarioBaseDados(), unit.getSenhaBaseDados(), Unit.SSL, Unit.NOME_UNIDADE, ip);
        }

        return factory.createEntityManager();
    }

    public static Object getPrimaryKey(Object entity) {
        return factory.getPersistenceUnitUtil().getIdentifier(entity);
    }

}
