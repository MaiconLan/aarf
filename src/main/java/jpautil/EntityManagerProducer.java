package jpautil;

import utils.Unit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory factory;

    public EntityManagerProducer() {
        Unit unit = new Unit();
        String ip = "jdbc:postgresql://" + unit.getIpBaseDados() + ":5432/" + unit.getNomeBaseDados();

        Map<String, String> map = new HashMap<>();
        map.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        map.put("javax.persistence.jdbc.user", unit.getUsuarioBaseDados());
        map.put("javax.persistence.jdbc.password", unit.getSenhaBaseDados());
        map.put("javax.persistence.jdbc.ssl", Unit.SSL);
        map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        map.put("hibernate.show_sql", "false");
        map.put("javax.persistence.jdbc.url", ip);

        this.factory = Persistence.createEntityManagerFactory(Unit.NOME_UNIDADE, map);
    }

    @Produces
    @RequestScoped
    public EntityManager create() {
        return this.factory.createEntityManager();
    }

    public void close(@Disposes EntityManager manager) {
        manager.close();
    }

}
