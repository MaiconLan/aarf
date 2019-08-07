package exception;

import java.util.ArrayList;
import java.util.Collection;

public class GenericException extends Exception {

    private Collection<String> messages = new ArrayList<>();

    public GenericException() {
        super();
    }

    public GenericException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        messages.add(message);
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
        messages.add(message);
    }

    public GenericException(String message) {
        super(message);
        messages.add(message);
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
