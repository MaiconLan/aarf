package utils;

public enum Ambiente {

    LOCAL("local"),
    HEROKU("Heroku"),
    DIGITAL_OCEAN("Digital Ocean");

    Ambiente(String valor) {
        this.valor = valor;
    }

    String valor;

    public String getValor() {
        return valor;
    }
}
