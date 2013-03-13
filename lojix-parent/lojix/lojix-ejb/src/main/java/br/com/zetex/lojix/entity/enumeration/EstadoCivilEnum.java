package br.com.zetex.lojix.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa todos os estados civis possíveis.
 *
 * @author rabsouza
 * @since 19/01/2013
 * @version 1.0
 *
 */
@SuppressWarnings("javadoc")
@XmlRootElement(name = "estadoCivil",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="estadoCivil", namespace="http://model.lojix.zetex.com.br")
public enum EstadoCivilEnum {

	CASADO("Casado(a)"), NENHUM(""), SOLTEIRO("Solteiro(a)");

	private static final Map<String, EstadoCivilEnum> LOOK_UP = new HashMap<String, EstadoCivilEnum>();

	private String label;

	static {
		for (EstadoCivilEnum estadoCivil : EstadoCivilEnum.values()) {
			LOOK_UP.put(estadoCivil.getLabel(), estadoCivil);
		}
	}

	/**
	 * Construtor para a classe EstadoCivilEnum.
	 *
	 * @param label
	 *            Texto que será impresso.
	 */
	private EstadoCivilEnum(String label) {
		this.label = label;
	}

	/**
	 * Retorna o enum.
	 *
	 * @param label
	 *            Nome do label.
	 * @return enum.
	 */
	public static EstadoCivilEnum get(String label) {
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
