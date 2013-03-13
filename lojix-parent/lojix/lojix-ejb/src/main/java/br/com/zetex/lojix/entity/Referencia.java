package br.com.zetex.lojix.entity;

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

import br.com.zetex.lojix.util.NoSerializable;

/**
 * Representa as informações da entidade <code>REFERENCIA</CODE>.
 * <ul>
 * <li>ID</li>
 * <li>NOME</li>
 * <li>DESCRICAO</li>
 * <li>FK_CLIENTE</li>
 * </ul>
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 18/07/2010
 * @see BaseEntityImpl
 * @see BaseEntity
 */
@Entity
@XmlRootElement(name = "referencia", namespace = "http://model.lojix.zetex.com.br")
@XmlType(name = "referencia", namespace = "http://model.lojix.zetex.com.br")
@Table(name = "REFERENCIA")
public class Referencia extends BaseEntityImpl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQUENCE_REFERENCIA_ID", sequenceName = "SEQUENCE_REFERENCIA_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_REFERENCIA_ID")
	@Column(name = "ID", nullable = false)
	@NotNull
	private Integer id;

	@Size(min = 0, max = 250)
	@Column(name = "NOME", nullable = true, length = 250)
	private String nome;

	@Size(min = 0, max = 250)
	@Column(name = "DESCRICAO", nullable = true, length = 250)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CLIENTE")
	private Cliente cliente;

	@Override
	@NoSerializable
	public Object getPk() {
		return getId();
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
