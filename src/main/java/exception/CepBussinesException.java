package exception;

import java.util.Collection;

public class CepBussinesException extends GenericException {
    public CepBussinesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CepBussinesException(String message, Throwable cause) {
        super(message, cause);
    }

    public CepBussinesException(String message) {
        super(message);
    }

    public CepBussinesException(Throwable cause) {
        super(cause);
    }

    public CepBussinesException(Collection<String> messages) {
        super(messages);
    }

    public CepBussinesException(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}
