package exception;

public class InstituicaoBusinessException extends Exception {

    private static final long serialVersionUID = -7734377762010424786L;

    public InstituicaoBusinessException() {
    }

    public InstituicaoBusinessException(String s) {
        super(s);
    }

    public InstituicaoBusinessException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InstituicaoBusinessException(Throwable throwable) {
        super(throwable);
    }

    public InstituicaoBusinessException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
