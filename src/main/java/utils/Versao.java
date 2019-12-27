package utils;

public enum Versao {

    MAJOR(1),
    MINOR(8),
    RELEASE(3);

    Versao(int numero) {
        this.numero = numero;
    }

    int numero;

    public int getNumero() {
        return numero;
    }
}
