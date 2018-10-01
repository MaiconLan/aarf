package exception;

import java.util.Collection;

public class EstudanteBusinessException extends GenericException {

    private static final long serialVersionUID = -6927034550806371234L;

    public EstudanteBusinessException() {
    }

    public EstudanteBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EstudanteBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public EstudanteBusinessException(String message) {
        super(message);
    }

    public EstudanteBusinessException(Throwable cause) {
        super(cause);
    }

    public EstudanteBusinessException(Collection<String> messages) {
        super(messages);
    }

    public EstudanteBusinessException(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}
