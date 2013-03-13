package br.com.zetex.lojix.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Possui a funação de ler uma propriedade do arquivo de configuração.
 *
 * @author Rafa
 * @since 15/03/2012
 * @version 1.0.0
 *
 */
public class Properties {

	private Properties(){
	}

	/**
	 * <strong>EnumProperties</strong> possui a função de acessar os arquivos de
	 * configuração e de tradução. <br>
	 * <strong>Ex.:</strong> <blockquote>
	 * <dl>
	 * <dt><code>config.properties</code>:</dt>
	 * <dd>Arquivo de configuração dos projetos;</dd>
	 * <dt><code>msg_LOCALE.properties</code>:</dt>
	 * <dd>Arquivo de tradução dos projetos;</dd>
	 * </dl>
	 * </blockquote>
	 *
	 * @author rafael.batista
	 * @version 1.0
	 * @since 28/12/2010
	 */
	private enum EnumProperties {

		FILE_CONFIG_PROPERTIES("config"),
		FILE_MSG_PROPERTIES("msg");

		private String desc;

		/**
		 * Construtor para <em>EnumProperties</em>
		 *
		 * @param desc
		 *            {@link String} que possui a descrição do <code>enum</code> .
		 */
		private EnumProperties(String desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {
			return desc;
		}

	}

	/**
	 * Metodo <b>get</b> possui a função de recuperar uma propriedade.
	 *
	 * @param propertie
	 *            Nome da propriedade.
	 * @param arguments
	 *            Object... valores dos argumentos da proprieddade.
	 * @return value Valor da propriedade.
	 */
	public static String get(String propertie, Object... arguments) {
		return loadConfigProperties(propertie, arguments);
	}

	/**
	 * Método <strong>loadConfigProperties(properties)</strong> possui a função
	 * de ler uma propriedade do arquivo de configuração.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param propertie
	 *            {@link String} a propriedade que será localizada no arquivo de
	 *            propriedade.
	 * @param arguments
	 *            Object... valores dos argumentos da proprieddade.
	 * @return <strong>propertie</strong> {@link String} que possui o valor da
	 *         propriedade localizada.
	 */
	public static String loadConfigProperties(String propertie,
			Object... arguments) {
		return loadProperties(EnumProperties.FILE_CONFIG_PROPERTIES, propertie,
				arguments);
	}

	/**
	 * Método <strong>loadMsgProperties(properties)</strong> possui a função
	 * de ler uma propriedade do arquivo de mensagem.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param propertie
	 *            {@link String} a propriedade que será localizada no arquivo de
	 *            propriedade.
	 * @param arguments
	 *            Object... valores dos argumentos da proprieddade.
	 * @return <strong>propertie</strong> {@link String} que possui o valor da
	 *         propriedade localizada.
	 */
	public static String loadMsgProperties(String propertie,
			Object... arguments) {
		return loadProperties(EnumProperties.FILE_MSG_PROPERTIES, propertie,
				arguments);
	}

	/**
	 * Método <strong>loadProperties(fileConfigProperties, properties)</strong>
	 * possui a função de ler uma propriedade do arquivo de configuração e de
	 * tradução.
	 *
	 * @author rafael.batista
	 * @since 28/12/2010
	 * @param fileConfigProperties
	 *            {@link EnumProperties} informa o arquivo de propriedade.
	 * @param propertie
	 *            {@link String} a propriedade que será localizada no arquivo de
	 *            propriedade.
	 * @param arguments
	 *            Object... valores dos argumentos da proprieddade.
	 * @return <strong>propertie</strong> {@link String} que possui o valor da
	 *         propriedade localizada.
	 */
	private static String loadProperties(EnumProperties fileConfigProperties,
			String propertie, Object... arguments) {
		ResourceBundle rb = ResourceBundle.getBundle(fileConfigProperties
				.toString());
		String valuePropertie = rb.getString(propertie);

		if ((arguments != null) && (arguments.length > 0)) {
			return MessageFormat.format(valuePropertie, arguments);
		} else {
			return valuePropertie;
		}
	}

}
