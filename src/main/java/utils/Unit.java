package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Unit {

    //Geral
    public static final String NOME_UNIDADE = "AARF";
    public static final String SSL = "false";

    // Dados da base local
    private String ipBaseDados = "";
    private String nomeBaseDados = "";
    private String usuarioBaseDados = "";
    private String senhaBaseDados = "";
    private String emailRemetente = "";
    private String emailSenha = "";

    public Unit() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("/opt/tomcat/config/variaveis.properties"));

            ipBaseDados = properties.getProperty("IP_BASE_DADOS");
            nomeBaseDados = properties.getProperty("NOME_BASE_DADOS");
            usuarioBaseDados = properties.getProperty("USUARIO_BASE_DADOS");
            senhaBaseDados = properties.getProperty("SENHA_BASE_DADOS");
            emailRemetente = properties.getProperty("EMAIL_REMETENTE");
            emailSenha = properties.getProperty("EMAIL_SENHA");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIpBaseDados() {
        return ipBaseDados;
    }

    public String getNomeBaseDados() {
        return nomeBaseDados;
    }

    public String getUsuarioBaseDados() {
        return usuarioBaseDados;
    }

    public String getSenhaBaseDados() {
        return senhaBaseDados;
    }

    public String getEmailRemetente() {
        return emailRemetente;
    }

    public String getEmailSenha() {
        return emailSenha;
    }
}
