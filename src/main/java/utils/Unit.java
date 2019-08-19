package utils;

public class Unit {

	//Geral
	public static final String NOME_UNIDADE = "AARF";
	public static final String SSL = "false";

	// Dados da base local
	public static final String IP_BASE_DADOS_LOCAL = "localhost";
	public static final String NOME_BASE_DADOS_LOCAL = "aarf-develop";
	public static final String USUARIO_BASE_DADOS_LOCAL = "postgres";
	public static final String SENHA_BASE_DADOS_LOCAL = "postgres";

	// Dados da base local
	public static final String IP_BASE_DADOS_HEROKU = "";
	public static final String NOME_BASE_DADOS_HEROKU = "";
	public static final String USUARIO_BASE_DADOS_HEROKU = "";
	public static final String SENHA_BASE_DADOS_HEROKU = "";
	
	// Dados da base DigitalOcean
	public static final String IP_BASE_DADOS_DO = "127.0.0.1";
	public static final String NOME_BASE_DADOS_DO = "aarf-oficial";
	public static final String USUARIO_BASE_DADOS_DO = "postgres";
	public static final String SENHA_BASE_DADOS_DO = "#e#j#m#m#r";

	public static final Ambiente AMBIENTE = Ambiente.LOCAL;
}
