package utils;

public enum Versao {

    MAJOR(1),
    MINOR(7),
    RELEASE(6);

    Versao(int numero) {
        this.numero = numero;
    }

    int numero;

    public int getNumero() {
        return numero;
    }
}
