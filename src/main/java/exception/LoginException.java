package exception;

import java.util.Collection;

public class LoginException extends GenericException {

	private static final long serialVersionUID = 1L;

	public LoginException() {
	}

	public LoginException(Collection<String> detalhes) {
		super(detalhes);
	}

	public LoginException(String message) {
		super(message);
	}


}
