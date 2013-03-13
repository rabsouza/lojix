package br.com.zetex.lojix.entity.enumeration;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representa todos os n�veis de prioridade do sistema.
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 31/07/2010
 */
@SuppressWarnings("javadoc")
@XmlRootElement(name = "prioridade",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="prioridade", namespace="http://model.lojix.zetex.com.br")
public enum PrioridadeEnum {

	ALTA("Alta"), BAIXO("Baixa"), MEDIA("Media"), PADRAO("Padr�o");

	private static final Map<String, PrioridadeEnum> LOOK_UP = new HashMap<String, PrioridadeEnum>();

	private String label;

	static {
		for (PrioridadeEnum prioridade : PrioridadeEnum.values()) {
			LOOK_UP.put(prioridade.getLabel(), prioridade);
		}
	}

	/**
	 * Construtor para a classe PrioridadeEnum.
	 *
	 * @param label
	 *            Texto que será impresso.
	 */
	private PrioridadeEnum(String label) {
		this.label = label;
	}

	/**
	 * Retorna o enum.
	 *
	 * @param label
	 *            Nome do label.
	 * @return enum.
	 */
	public static PrioridadeEnum get(String label) {
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
