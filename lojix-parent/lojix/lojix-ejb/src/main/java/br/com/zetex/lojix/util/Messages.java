package br.com.zetex.lojix.util;

/**
 * Possui a função de ler uma propriedade do arquivo de mensagem.
 *
 * @author Rafa
 * @since 15/03/2012
 * @version 1.0.0
 *
 */
public class Messages {

	/**
	 * Metodo <b>get</b> possui a função de recuperar uma propriedade.
	 *
	 * @param propertie
	 *            Nome da propriedade.
	 * @param arguments
	 *            {@link Object...} valores dos argumentos da proprieddade.
	 * @return value Valor da propriedade.
	 */
	public static String get(String propertie, Object... arguments) {
		return Properties.loadMsgProperties(propertie, arguments);
	}

}
