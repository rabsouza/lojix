package br.com.zetex.lojix.exception;

import org.apache.log4j.Logger;

import br.com.zetex.lojix.util.LoggerUtil;
import br.com.zetex.lojix.util.PackageLog;

/**
 * Classe para as exceções gerais.
 *
 * @author rabsouza
 * @since 20/01/2013
 * @version 1.0
 * @see Exception
 *
 */
public class LojixException extends Exception {

	private static final Logger LOGGER = LoggerUtil.getLogger(
			LojixException.class, PackageLog.UTIL);
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor para a classe LojixException.
	 *
	 */
	public LojixException() {
		super();
	}

	/**
	 * Construtor para a classe LojixException.
	 *
	 * @param message
	 */
	public LojixException(String message) {
		super(message);
		LOGGER.error(message);
	}

	/**
	 * Construtor para a classe LojixException.
	 *
	 * @param message
	 * @param cause
	 */
	public LojixException(String message, Throwable cause) {
		super(message, cause);
		LOGGER.error(message, cause);
	}

	/**
	 * Construtor para a classe LojixException.
	 *
	 * @param cause
	 */
	public LojixException(Throwable cause) {
		super(cause);
		LOGGER.error(cause);
	}

}
