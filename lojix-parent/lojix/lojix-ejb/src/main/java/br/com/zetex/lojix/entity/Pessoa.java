package br.com.zetex.lojix.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import br.com.zetex.lojix.entity.enumeration.TipoPessoaEnum;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.exception.LojixRuntimeException;
import br.com.zetex.lojix.util.NoSerializable;

/**
 * Representa as informações da entidade <code>PESSOA</CODE>.
 * <ul>
 * <li>ID</li>
 * <li>NOME</li>
 * <li>TIPO</li>
 * <li>INFORMACAO</li>
 * <li>FK_USUARIO</li>
 * </ul>
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 24/07/2010
 * @see BaseEntityImpl
 * @see BaseEntity
 */
@XmlRootElement(name = "pessoa",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="pessoa", namespace="http://model.lojix.zetex.com.br")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO")
@Entity
@Table(name = "PESSOA")
public abstract class Pessoa extends BaseEntityImpl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Contato> contatos = new ArrayList<Contato>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<Endereco>();

	@Id
	@SequenceGenerator(name = "SEQUENCE_PESSOA_ID", sequenceName = "SEQUENCE_PESSOA_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_PESSOA_ID")
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Size(min = 1, max = 250)
	@Column(name = "INFORMACAO", length = 250, nullable = true)
	private String informacao;

	@NotBlank
	@Size(min = 1, max = 150)
	@Column(name = "NOME", length = 150, nullable = false)
	private String nome;

	@Column(name = "TIPO", length = 3, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPessoaEnum tipo;

	@NoSerializable
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "FK_USUARIO", nullable = false)
	private Usuario usuario;

	/**
	 * Construtor para a classe Pessoa.
	 *
	 */
	public Pessoa() {
		super();
	}

	/**
	 * Construtor para a classe Pessoa.
	 *
	 * @param map
	 * @throws LojixException
	 */
	public Pessoa(Map<String, Object> map) throws LojixException {
		super(map);
	}

	/**
	 * @author rabsouza
	 *
	 * @param contato
	 *            Contato que será adicionado.
	 * @return true - sucesso / false - falha
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Contato contato) {
		if (contato == null) {
			throw new LojixRuntimeException("Contato não pode ser nullo!");
		}
		contato.setPessoa(this);
		return contatos.add(contato);
	}

	/**
	 * @author rabsouza
	 *
	 * @param endereco
	 *            Endereco a ser adicionado.
	 * @return true - sucesso / false - falha.
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Endereco endereco) {
		if (endereco == null) {
			throw new LojixRuntimeException("Endereço não pode ser nullo!");
		}
		endereco.setPessoa(this);
		return enderecos.add(endereco);
	}

	/**
	 * @author rabsouza
	 *
	 * @param index
	 *            Indice a ser buscado
	 * @return contato Contato buscado.
	 * @see java.util.List#get(int)
	 */
	public Contato getContato(int index) {
		return contatos.get(index);
	}

	/**
	 * @return contatos
	 */
	@Transient
	public List<Contato> getContatos() {
		return contatos;
	}

	/**
	 * @author rabsouza
	 *
	 * @param index
	 *            Indice a ser buscado
	 * @return endereco Endereco buscado.
	 * @see java.util.List#get(int)
	 */
	public Endereco getEndereco(int index) {
		return enderecos.get(index);
	}

	/**
	 * @return enderecos
	 */
	@Transient
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return informacao
	 */
	public String getInformacao() {
		return informacao;
	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	@NoSerializable
	@Override
	public Object getPk() {
		return getId();
	}

	/**
	 * @return tipo
	 */
	public TipoPessoaEnum getTipo() {
		return tipo;
	}

	/**
	 * @return usuario
	 */
	@NoSerializable
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param contatos
	 *            List<Contato>
	 */
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	/**
	 * @param enderecos
	 *            List<Endereco>
	 */
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	/**
	 * @param id
	 *            Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param informacao
	 *            String
	 */
	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	/**
	 * @param nome
	 *            String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param tipo
	 *            TipoPessoaEnum
	 */
	@Deprecated
	public void setTipo(TipoPessoaEnum tipo) {
		this.tipo = tipo;
	}

	/**
	 * @param usuario
	 *            Usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
