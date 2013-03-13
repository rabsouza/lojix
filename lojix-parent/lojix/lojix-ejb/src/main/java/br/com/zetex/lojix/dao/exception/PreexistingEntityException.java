package br.com.zetex.lojix.dao.exception;

/**
 * Representa a exceção de pre-existência da entidade.
 *
 * @author Rafael
 * @version 1.0.0
 * @since 25/07/2010 : 17:04:49
 * @see Exception
 */
public class PreexistingEntityException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor para <em>PreexistingEntityException</em>
	 *
	 * @param message
	 *            String
	 */
	public PreexistingEntityException(String message) {
		super(message);
	}

	/**
	 * Construtor para <em>PreexistingEntityException</em>
	 *
	 * @param message
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public PreexistingEntityException(String message, Throwable cause) {
		super(message, cause);
	}
}
