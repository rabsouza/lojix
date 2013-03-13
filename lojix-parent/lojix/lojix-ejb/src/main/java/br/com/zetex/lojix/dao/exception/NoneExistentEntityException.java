package br.com.zetex.lojix.dao.exception;

/**
 * Representa a exceção de não existência da entidade.
 *
 * @author rafael.batista
 * @version 1.0.0
 * @since 25/07/2010
 * @see Exception
 */
public class NoneExistentEntityException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor para <em>NonexistentEntityException</em>
	 *
	 * @param message
	 *            String
	 */
	public NoneExistentEntityException(String message) {
		super(message);
	}

	/**
	 * Construtor para <em>NonexistentEntityException</em>
	 *
	 * @param message
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public NoneExistentEntityException(String message, Throwable cause) {
		super(message, cause);
	}
}
