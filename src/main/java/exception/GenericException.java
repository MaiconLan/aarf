package exception;

import java.util.Collection;

public class GenericException extends Exception {

    private Collection<String> messages;

    public GenericException() {
        super();
    }

    public GenericException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(Throwable cause) {
        super(cause);
    }

    public GenericException(Collection<String> messages) {
        super();
        this.messages = messages;
    }

    public GenericException(Collection<String> messages, Exception cause) {
        super(cause);
        this.messages = messages;
    }

    public Collection<String> getMessages() {
        return messages;
    }

}
