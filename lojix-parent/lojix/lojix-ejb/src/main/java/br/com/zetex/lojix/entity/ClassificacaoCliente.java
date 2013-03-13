package br.com.zetex.lojix.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import br.com.zetex.lojix.util.NoSerializable;

/**
 * Representa as informações da entidade <code>CLASSIFICACAO_CLIENTE</CODE>.
 * <ul>
 * <li>ID</li>
 * <li>CLASSIFICACAO</li>
 * <li>DESCRICAO</li>
 * </ul>
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 18/07/2010
 * @see BaseEntityImpl
 * @see BaseEntity
 */
@Entity
@XmlRootElement(name = "classificacaoCliente", namespace = "http://model.lojix.zetex.com.br")
@XmlType(name = "classificacaoCliente", namespace = "http://model.lojix.zetex.com.br")
@Table(name = "CLASSIFICACAO_CLIENTE")
public class ClassificacaoCliente extends BaseEntityImpl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 25)
	@Column(name = "CLASSIFICACAO", length = 25, nullable = false)
	private String classificacao;

	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "classificacaoCliente", fetch = FetchType.LAZY)
	private List<Cliente> cliente;

	@Size(min = 0, max = 250)
	@Column(name = "DESCRICAO", nullable = true, length = 250)
	private String descricao;

	@Id
	@SequenceGenerator(name = "SEQUENCE_CLASSIFICACAO_CLIENTE_ID", sequenceName = "SEQUENCE_CLASSIFICACAO_CLIENTE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CLASSIFICACAO_CLIENTE_ID")
	@Column(name = "ID", nullable = false)
	@NotNull
	private Integer id;

	/**
	 * @return classificacao
	 */
	public String getClassificacao() {
		return classificacao;
	}

	/**
	 * @return cliente
	 */
	public List<Cliente> getCliente() {
		return cliente;
	}

	/**
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	@Override
	@NoSerializable
	public Object getPk() {
		return getId();
	}

	/**
	 * @param classificacao
	 *            classificacao
	 */
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	/**
	 * @param cliente
	 *            cliente
	 */
	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	/**
	 * @param descricao
	 *            descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @param id
	 *            id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
