package br.com.zetex.lojix.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa todos os sexos.
 *
 * @author rabsouza
 * @since 19/01/2013
 * @version 1.0
 *
 */
@SuppressWarnings("javadoc")
@XmlRootElement(name = "tipoEndereco",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="tipoEndereco", namespace="http://model.lojix.zetex.com.br")
public enum TipoEnderecoEnum {

	RESIDENCIAL("Residêncial"), COMERCIAL("Comercial");

	private static final Map<String, TipoEnderecoEnum> LOOK_UP = new HashMap<String, TipoEnderecoEnum>();

	private String label;

	static {
		for (TipoEnderecoEnum tipoEndereco : TipoEnderecoEnum.values()) {
			LOOK_UP.put(tipoEndereco.getLabel(), tipoEndereco);
		}
	}

	/**
	 * Construtor para a classe TipoEnderecoEnum.
	 *
	 * @param label
	 *            Texto que será impresso.
	 */
	private TipoEnderecoEnum(String label) {
		this.label = label;
	}

	/**
	 * Retorna o enum.
	 *
	 * @param label
	 *            Nome do label.
	 * @return enum.
	 */
	public static TipoEnderecoEnum get(String label) {
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
