package br.com.zetex.lojix.dao.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a exceção de ilegal classe
 *
 * @author rafael.batista
 * @version 1.0.0
 * @since 25/07/2010
 * @see Exception
 */
public class IllegalOrphanException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<String> messages;

	/**
	 * Construtor para <em>IllegalOrphanException</em>
	 *
	 * @param messages
	 *            List<String>
	 */
	public IllegalOrphanException(List<String> messages) {
		super((messages != null) && (messages.size() > 0) ? messages.get(0)
				: null);

		if (messages == null) {
			this.messages = new ArrayList<String>();
		} else {
			this.messages = messages;
		}
	}

	/**
	 * Metodo <b>getMessages</b> possui a função de retornar as msg da exceção
	 *
	 * @return List<String>
	 */
	public List<String> getMessages() {
		return messages;
	}
}
