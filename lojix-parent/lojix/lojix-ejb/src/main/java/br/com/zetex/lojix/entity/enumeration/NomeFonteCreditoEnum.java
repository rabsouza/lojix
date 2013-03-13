package br.com.zetex.lojix.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa as fontes de consulta de crédito.
 *
 * @author rabsouza
 * @since 19/01/2013
 * @version 1.0
 *
 */
@SuppressWarnings("javadoc")
@XmlRootElement(name = "nomneFonteCredito",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="nomeFonteCredito", namespace="http://model.lojix.zetex.com.br")
public enum NomeFonteCreditoEnum {

	SPC("SPC", "Descrição");

	private static final Map<String, NomeFonteCreditoEnum> LOOK_UP = new HashMap<String, NomeFonteCreditoEnum>();

	private String label;
	private String descricao;

	/**
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	static {
		for (NomeFonteCreditoEnum nomeFonteCredito : NomeFonteCreditoEnum.values()) {
			LOOK_UP.put(nomeFonteCredito.getLabel(), nomeFonteCredito);
		}
	}

	/**
	 * Construtor para a classe NomeFonteCreditoEnum.
	 *
	 * @param label
	 *            Texto que será impresso.
	 * @param descricao
	 *            Descricao da fonte.
	 */
	private NomeFonteCreditoEnum(String label, String descricao) {
		this.label = label;
		this.descricao = descricao;
	}

	/**
	 * Retorna o enum.
	 *
	 * @param label
	 *            Nome do label.
	 * @return enum.
	 */
	public static NomeFonteCreditoEnum get(String label) {
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