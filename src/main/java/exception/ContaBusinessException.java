package exception;

import java.util.Collection;

public class ContaBusinessException extends GenericException {

    private static final long serialVersionUID = -6927034550806371234L;

    public ContaBusinessException() {
    }

    public ContaBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ContaBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContaBusinessException(String message) {
        super(message);
    }

    public ContaBusinessException(Throwable cause) {
        super(cause);
    }

    public ContaBusinessException(Collection<String> messages) {
        super(messages);
    }

    public ContaBusinessException(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}
