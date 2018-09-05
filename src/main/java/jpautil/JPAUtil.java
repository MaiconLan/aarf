package jpautil;

import utils.Unit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JPAUtil {

	private static EntityManagerFactory factory = null;

	private static final String IP_LOCAL = "jdbc:postgresql://" + Unit.IP_BASE_DADOS_LOCAL + ":5432/"
			+ Unit.NOME_BASE_DADOS_LOCAL;

	private static final String IP_DIGITAL_OCEAN = "jdbc:postgresql://" + Unit.IP_BASE_DADOS_DO + ":5432/"
			+ Unit.NOME_BASE_DADOS_DO;

	private static final String ipAmazon = "jdbc:postgresql://" + Unit.IP_BASE_DADOS_AZURE + ":5432/"
			+ Unit.NOME_BASE_DADOS_AZURE;

	@SuppressWarnings("unused")
	private static final String IP_AZURE = "jdbc:postgresql://" + Unit.IP_BASE_DADOS_AZURE + ":5432/"
			+ Unit.NOME_BASE_DADOS_AZURE + "?user=" + Unit.USUARIO_BASE_DADOS_AZURE + "&password="
			+ Unit.SENHA_BASE_DADOS_AZURE + "&ssl=" + Unit.SSL;

	private static synchronized void loadInstance(String usuario, String senha, String ssl, String unidade, String ip) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
			map.put("javax.persistence.jdbc.user", usuario);
			map.put("javax.persistence.jdbc.password", senha);
			map.put("javax.persistence.jdbc.ssl", ssl);
			map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			map.put("hibernate.show_sql", "true");
			map.put("javax.persistence.jdbc.url", ip);

			factory = Persistence.createEntityManagerFactory(unidade, map);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static EntityManager getEntityManager() {
		if (factory == null) {

			switch (Unit.HOSPEDAGEM) {

			case Unit.AZURE:
				loadInstance(Unit.USUARIO_BASE_DADOS_AZURE, Unit.SENHA_BASE_DADOS_AZURE, Unit.SSL, Unit.NOME_UNIDADE,
						ipAmazon);
				break;

			case Unit.LOCAL:
				loadInstance(Unit.USUARIO_BASE_DADOS_LOCAL, Unit.SENHA_BASE_DADOS_LOCAL, Unit.SSL, Unit.NOME_UNIDADE,
						IP_LOCAL);
				break;

			case Unit.DIGITAL_OCEAN:
				loadInstance(Unit.USUARIO_BASE_DADOS_DO, Unit.SENHA_BASE_DADOS_DO, Unit.SSL, Unit.NOME_UNIDADE,
						IP_DIGITAL_OCEAN);
				break;

			default:
				break;
			}

		}

		return factory.createEntityManager();
	}

	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

}
