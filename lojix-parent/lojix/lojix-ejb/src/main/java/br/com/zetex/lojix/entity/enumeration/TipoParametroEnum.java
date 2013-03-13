package br.com.zetex.lojix.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa todos os tipos de paramêtros possíveis.
 *
 * @author rabsouza
 * @since 19/01/2013
 * @version 1.0
 *
 */
@SuppressWarnings("javadoc")
@XmlRootElement(name = "tipoParametro",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="tipoParametro", namespace="http://model.lojix.zetex.com.br")
public enum TipoParametroEnum {

	APP_WEB("Aplicação Web"), SISTEMA("Sistema");

	private static final Map<String, TipoParametroEnum> LOOK_UP = new HashMap<String, TipoParametroEnum>();

	private String label;

	static {
		for (TipoParametroEnum tipoParametro : TipoParametroEnum.values()) {
			LOOK_UP.put(tipoParametro.getLabel(), tipoParametro);
		}
	}

	/**
	 * Construtor para a classe TipoParametroEnum.
	 *
	 * @param label
	 *            Texto que será impresso.
	 */
	private TipoParametroEnum(String label) {
		this.label = label;
	}

	/**
	 * Retorna o enum.
	 *
	 * @param label
	 *            Nome do label.
	 * @return enum.
	 */
	public static TipoParametroEnum get(String label) {
		return LOOK_UP.get(label);
	}

	/**
	 * @return label
	 */
	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return getLabel();
	}

}
