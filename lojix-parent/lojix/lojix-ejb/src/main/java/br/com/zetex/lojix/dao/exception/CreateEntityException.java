package br.com.zetex.lojix.dao.exception;

/**
 * Representa a exceção ao criar uma entidade.
 *
 * @author rafael.batista
 * @version 1.0.0
 * @since 18/07/2010
 * @see Exception
 */
public class CreateEntityException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor para <em>CreateEntityException</em>
	 *
	 * @param message
	 *            String
	 */
	public CreateEntityException(String message) {
		super(message);
	}

	/**
	 * Construtor para <em>CreateEntityException</em>
	 *
	 * @param message
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public CreateEntityException(String message, Throwable cause) {
		super(message, cause);
	}
}
