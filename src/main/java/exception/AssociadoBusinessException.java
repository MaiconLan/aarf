package exception;

import java.util.Collection;

public class AssociadoBusinessException extends GenericException {

    private static final long serialVersionUID = -6927034550806371234L;

    public AssociadoBusinessException() {
    }

    public AssociadoBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AssociadoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssociadoBusinessException(String message) {
        super(message);
    }

    public AssociadoBusinessException(Throwable cause) {
        super(cause);
    }

    public AssociadoBusinessException(Collection<String> messages) {
        super(messages);
    }

    public AssociadoBusinessException(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}
