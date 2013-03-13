package br.com.zetex.lojix.entity;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.util.NoSerializable;

/**
 * Representa as informações da entidade <code>CONTATO</CODE>.
 * <ul>
 * <li>ID</li>
 * <li>CONTATO</li>
 * <li>VALOR</li>
 * <li>FK_PESSOA</li>
 * </ul>
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 18/07/2010
 * @see BaseEntityImpl
 * @see BaseEntity
 */
@Entity
@XmlRootElement(name = "contato",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="contato", namespace="http://model.lojix.zetex.com.br")
@Table(name = "CONTATO")
public class Contato extends BaseEntityImpl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQUENCE_CONTATO_ID", sequenceName = "SEQUENCE_CONTATO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CONTATO_ID")
	@Column(name = "ID", nullable = false)
	@NotNull
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_PESSOA")
	private Pessoa pessoa;

	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_TIPO_CONTATO", nullable = false)
	private TipoContato tipo;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 250)
	@Column(name = "VALOR", nullable = false, length = 250)
	private String valor;

	/**
	 * Construtor para a classe Contato.
	 *
	 */
	public Contato() {
		super();
	}

	/**
	 * Construtor para a classe Contato.
	 *
	 * @param map
	 * @throws LojixException
	 */
	public Contato(Map<String, Object> map) throws LojixException {
		super(map);
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	@NoSerializable
	@Override
	public Object getPk() {
		return getId();
	}

	/**
	 * @return tipo
	 */
	public TipoContato getTipo() {
		return tipo;
	}

	/**
	 * @return valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param id
	 *            Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param pessoa
	 *            pessoa
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @param tipo
	 *            TipoContato
	 */
	public void setTipo(TipoContato tipo) {
		this.tipo = tipo;
	}

	/**
	 * @param valor
	 *            Object
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

}
