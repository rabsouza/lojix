package br.com.zetex.lojix.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa todos idiomas possíveis.
 *
 * @author rabsouza
 * @since 19/01/2013
 * @version 1.0
 *
 */
@SuppressWarnings("javadoc")
@XmlRootElement(name = "idioma",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="idioma", namespace="http://model.lojix.zetex.com.br")
public enum IdiomaEnum {

	PT_BR("Português Brasil");

	private static final Map<String, IdiomaEnum> LOOK_UP = new HashMap<String, IdiomaEnum>();

	private String label;

	static {
		for (IdiomaEnum idioma : IdiomaEnum.values()) {
			LOOK_UP.put(idioma.getLabel(), idioma);
		}
	}

	/**
	 * Construtor para a classe IdiomaEnum.
	 *
	 * @param label
	 *            Texto que será impresso.
	 */
	private IdiomaEnum(String label) {
		this.label = label;
	}

	/**
	 * Retorna o enum.
	 *
	 * @param label
	 *            Nome do label.
	 * @return enum.
	 */
	public static IdiomaEnum get(String label) {
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
