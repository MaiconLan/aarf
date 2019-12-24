package exception;

import java.util.Collection;

public class ContratoBusinessException extends GenericException {

    public ContratoBusinessException(Collection<String> messages) {
        super(messages);
    }
}
