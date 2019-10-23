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
            Unit unit = new Unit();
            Flyway flyway = new Flyway();

            flyway.setDataSource("jdbc:postgresql://" + unit.getIpBaseDados() + "/" + unit.getNomeBaseDados(), unit.getUsuarioBaseDados(), unit.getSenhaBaseDados());

            flyway.setLocations("classpath:flyway");
            flyway.setInstalledBy("sistema");
            flyway.setTable("versao_base");
            flyway.migrate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
