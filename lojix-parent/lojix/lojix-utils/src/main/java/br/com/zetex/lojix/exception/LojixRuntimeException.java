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
public class LojixRuntimeException extends RuntimeException {

	private static final Logger LOGGER = LoggerUtil.getLogger(
			LojixRuntimeException.class, PackageLog.UTIL);
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor para a classe LojixException.
	 *
	 */
	public LojixRuntimeException() {
		super();
	}

	/**
	 * Construtor para a classe LojixException.
	 *
	 * @param message
	 */
	public LojixRuntimeException(String message) {
		super(message);
		LOGGER.error(message);
	}

	/**
	 * Construtor para a classe LojixException.
	 *
	 * @param message
	 * @param cause
	 */
	public LojixRuntimeException(String message, Throwable cause) {
		super(message, cause);
		LOGGER.error(message, cause);
	}

	/**
	 * Construtor para a classe LojixException.
	 *
	 * @param cause
	 */
	public LojixRuntimeException(Throwable cause) {
		super(cause);
		LOGGER.error(cause);
	}

}
