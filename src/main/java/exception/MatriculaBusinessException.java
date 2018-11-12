package exception;

import java.util.Collection;

public class MatriculaBusinessException extends GenericException{
	
	private static final long serialVersionUID = 6179214353070090496L;

	public MatriculaBusinessException() {
	}
	
    public MatriculaBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MatriculaBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatriculaBusinessException(String message) {
        super(message);
    }

    public MatriculaBusinessException(Throwable cause) {
        super(cause);
    }

    public MatriculaBusinessException(Collection<String> messages) {
        super(messages);
    }

    public MatriculaBusinessException(Collection<String> messages, Exception cause) {
        super(messages, cause);
    }
	
}
