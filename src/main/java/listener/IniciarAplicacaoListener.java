package listener;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import utils.Unit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class IniciarAplicacaoListener implements ServletContextListener {

	Logger logger = Logger.getLogger("br.com.aarf.listener");

	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("---------------------------------Contexto Destruido---------------------------------");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("---------------------------------Contexto Iniciado---------------------------------");
		logger.debug("Iniciando Migration FlyWay");
		iniciaFlyaway();
	}

	public void iniciaFlyaway() {
		try {
			Flyway flyway = new Flyway();

			switch (Unit.HOSPEDAGEM) {

			case Unit.LOCAL:
				flyway.setDataSource("jdbc:postgresql://" + Unit.IP_BASE_DADOS_LOCAL + "/" + Unit.NOME_BASE_DADOS_LOCAL,
						Unit.USUARIO_BASE_DADOS_LOCAL, Unit.SENHA_BASE_DADOS_LOCAL);
				break;

			case Unit.DIGITAL_OCEAN:
				flyway.setDataSource("jdbc:postgresql://" + Unit.IP_BASE_DADOS_DO + "/" + Unit.NOME_BASE_DADOS_DO,
						Unit.USUARIO_BASE_DADOS_DO, Unit.SENHA_BASE_DADOS_DO);
				break;

			case Unit.HEROKU:
				flyway.setDataSource("jdbc:postgresql://" + Unit.IP_BASE_DADOS_HEROKU + "/" + Unit.NOME_BASE_DADOS_HEROKU,
						Unit.USUARIO_BASE_DADOS_HEROKU, Unit.SENHA_BASE_DADOS_HEROKU);
				break;
			default:
				break;

			}

			flyway.setLocations("classpath:flyway");
			flyway.setInstalledBy("sistema");
			flyway.setTable("versao_base");
			flyway.migrate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
