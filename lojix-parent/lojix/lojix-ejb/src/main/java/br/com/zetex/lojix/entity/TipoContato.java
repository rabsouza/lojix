package br.com.zetex.lojix.entity;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.util.NoSerializable;

/**
 * Representa as informações da entidade <code>TIPO_CONTATO</CODE>.
 * <ul>
 * <li>ID</li>
 * <li>NOME</li>
 * <li>TIPO</li>
 * <li>DESCRICAO</li>
 * <li>PARTTEN_VALIDACAO</li>
 * <li>PADRAO</li>
 * <li>STATUS</li>
 * </ul>
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 18/07/2010
 * @see BaseEntityImpl
 * @see BaseEntity
 */
@Entity
@XmlRootElement(name = "tipoContato",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="tipoContato", namespace="http://model.lojix.zetex.com.br")
@Table(name = "TIPO_CONTATO")
public class TipoContato extends BaseEntityImpl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Size(min = 0, max = 250)
	@Column(name = "DESCRICAO", nullable = true, length = 250)
	private String descricao;

	@Id
	@SequenceGenerator(name = "SEQUENCE_TIPO_CONTATO_ID", sequenceName = "SEQUENCE_TIPO_CONTATO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_TIPO_CONTATO_ID")
	@Column(name = "ID", nullable = false)
	@NotNull
	private Integer id;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[A-Za-z0-9 ]*", message = "Pode ser digitado letras, números e espaços somente e tamanho máximo de 25 caracteres.")
	@Column(name = "NOME", length = 25, unique = true, nullable = false)
	private String nome;

	@NotNull
	@Column(name = "PADRAO", nullable = false)
	private Boolean padrao = Boolean.FALSE;

	@Size(min = 0, max = 50)
	@Column(name = "PARTTEN_VALIDACAO", nullable = true, length = 50)
	private String parttenValidacao;

	@NotNull
	@Column(name = "STATUS", nullable = false)
	private Boolean status = Boolean.FALSE;

	@Size(min = 0, max = 25)
	@Pattern(regexp = "[A-Z]*", message = "Pode ser digitado letrase tamanho máximo de 25 caracteres.")
	@Column(name = "TIPO", nullable = true, length = 25)
	private String tipo;

	/**
	 * Construtor para a classe TipoContato.
	 *
	 */
	public TipoContato() {
		super();
	}

	/**
	 * Construtor para a classe TipoContato.
	 *
	 * @param map
	 * @throws LojixException
	 */
	public TipoContato(Map<String, Object> map) throws LojixException {
		super(map);
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

	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return parttenValidacao
	 */
	public String getParttenValidacao() {
		return parttenValidacao;
	}

	@NoSerializable
	@Override
	public Object getPk() {
		return getId();
	}

	/**
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @return padrao
	 */
	public boolean isPadrao() {
		return padrao;
	}

	/**
	 * @param descricao
	 *            String
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @param id
	 *            Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param nome
	 *            String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param padrao
	 *            boolean
	 */
	public void setPadrao(boolean padrao) {
		this.padrao = padrao;
	}

	/**
	 * @param parttenValidacao
	 *            String
	 */
	public void setParttenValidacao(String parttenValidacao) {
		this.parttenValidacao = parttenValidacao;
	}

	/**
	 * @param status
	 *            Boolean
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @param tipo
	 *            String
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
