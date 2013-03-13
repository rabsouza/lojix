package br.com.zetex.lojix.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa todas as UF's possíveis.
 *
 * @author rabsouza
 * @since 19/01/2013
 * @version 1.0
 *
 */
@SuppressWarnings("javadoc")
@XmlRootElement(name = "uf",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="uf", namespace="http://model.lojix.zetex.com.br")
public enum UFEnum {

	MG("Minas Gerais"),
	NENHUM(""), ;

	private static final Map<String, UFEnum> LOOK_UP = new HashMap<String, UFEnum>();

	private String label;

	static {
		for (UFEnum uf : UFEnum.values()) {
			LOOK_UP.put(uf.getLabel(), uf);
		}
	}

	/**
	 * Construtor para a classe UFEnum.
	 *
	 * @param label
	 *            Texto que será impresso.
	 */
	private UFEnum(String label) {
		this.label = label;
	}

	/**
	 * Retorna o enum.
	 *
	 * @param label
	 *            Nome do label.
	 * @return enum.
	 */
	public static UFEnum get(String label) {
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
