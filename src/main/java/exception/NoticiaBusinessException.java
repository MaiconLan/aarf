package exception;

import java.util.Collection;

public class NoticiaBusinessException extends GenericException {

    private static final long serialVersionUID = -6927034550806371234L;

    public NoticiaBusinessException() {
    }

    public NoticiaBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoticiaBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoticiaBusinessException(String message) {
        super(message);
    }

    public NoticiaBusinessException(Throwable cause) {
        super(cause);
    }

    public NoticiaBusinessException(Collection<String> messages) {
        super(messages);
    }

    public NoticiaBusinessException(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
}
