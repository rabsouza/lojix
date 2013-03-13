package br.com.zetex.lojix.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa todos os tipos de pessoas do sistema.
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 31/07/2010
 */
@SuppressWarnings("javadoc")
@XmlRootElement(name = "tipoPessoa",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="tipoPessoa", namespace="http://model.lojix.zetex.com.br")
public enum TipoPessoaEnum {

	FIS("Pessoa Fisica"), JUR("Pessoa Juridica");

	private static final Map<String, TipoPessoaEnum> LOOK_UP = new HashMap<String, TipoPessoaEnum>();

	private String label;

	static {
		for (TipoPessoaEnum tipoPessoa : TipoPessoaEnum.values()) {
			LOOK_UP.put(tipoPessoa.getLabel(), tipoPessoa);
		}
	}

	/**
	 * Construtor para a classe TipoPessoaEnum.
	 *
	 * @param label
	 *            Texto que será impresso.
	 */
	private TipoPessoaEnum(String label) {
		this.label = label;
	}

	/**
	 * Retorna o enum.
	 *
	 * @param label
	 *            Nome do label.
	 * @return enum.
	 */
	public static TipoPessoaEnum get(String label) {
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
