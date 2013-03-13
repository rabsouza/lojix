package br.com.zetex.lojix.dao.exception;

/**
 * Representa a exceção de roolback falido.
 *
 * @author Rafael
 * @version 1.0.0
 * @since 25/07/2010 : 17:05:40
 * @see Exception
 */
public class RollbackFailureException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor para <em>RollbackFailureException</em>
	 *
	 * @param message
	 *            String
	 */
	public RollbackFailureException(String message) {
		super(message);
	}

	/**
	 * Construtor para <em>RollbackFailureException</em>
	 *
	 * @param message
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public RollbackFailureException(String message, Throwable cause) {
		super(message, cause);
	}
}
