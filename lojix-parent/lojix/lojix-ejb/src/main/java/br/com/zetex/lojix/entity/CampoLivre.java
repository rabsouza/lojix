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

import org.hibernate.validator.constraints.NotBlank;

import br.com.zetex.lojix.util.NoSerializable;

/**
 * Representa as informações da entidade <code>CAMPO_LIVRE</CODE>.
 * <ul>
 * <li>ID</li>
 * <li>NOME</li>
 * <li>VALOR</li>
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
@XmlRootElement(name = "campoLivre",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="campoLivre", namespace="http://model.lojix.zetex.com.br")
@Table(name = "CAMPO_LIVRE")
public class CampoLivre extends BaseEntityImpl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQUENCE_CAMPO_LIVRE_ID", sequenceName = "SEQUENCE_CAMPO_LIVRE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CAMPO_LIVRE_ID")
	@Column(name = "ID", nullable = false)
	@NotNull
	private Integer id;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 25)
	@Column(name = "NOME", length = 25, nullable = false)
	private String nome;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 250)
	@Column(name = "VALOR", nullable = false, length = 250)
	private String valor;

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
	 * @return valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
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
