package br.com.zetex.lojix.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.com.zetex.lojix.entity.enumeration.PermissaoEnum;
import br.com.zetex.lojix.entity.enumeration.PrioridadeEnum;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.util.NoSerializable;

/**
 * Representa as informações da entidade <code>PERFIL</CODE>.
 * <ul>
 * <li>NOME</li>
 * <li>STATUS</li>
 * <li>PERMISSAO</li>
 * <li>PRIORIDADE</li>
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
@XmlRootElement(name = "perfil", namespace = "http://model.lojix.zetex.com.br")
@XmlType(name = "perfil", namespace = "http://model.lojix.zetex.com.br")
@Table(name = "PERFIL")
public class Perfil extends BaseEntityImpl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@Size(min = 1, max = 350)
	@Column(name = "DESCRICAO", length = 350, nullable = true)
	private String descricao;

	@NoSerializable
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private List<Usuario> usuarios;

	@Id
	@NotNull
	@Size(min = 1, max = 150)
	@Column(name = "NOME", length = 150, nullable = false, unique = true)
	@Pattern(regexp = "[A-Z-]*", message = "Pode ser digitado letras e o silbolo '-' e tamanho máximo de 30 caracteres.")
	private String nome;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMISSAO", nullable = false)
	private PermissaoEnum permissao;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "PRIORIDADE", nullable = false)
	private PrioridadeEnum prioridade;

	@NotNull
	@Column(name = "STATUS", nullable = false)
	private Boolean status = Boolean.FALSE;

	/**
	 * Construtor para a classe Perfil.
	 *
	 */
	public Perfil() {
		super();
	}

	/**
	 * Construtor para a classe Perfil.
	 *
	 * @param map
	 * @throws LojixException
	 */
	public Perfil(Map<String, Object> map) throws LojixException {
		super(map);
	}

	/**
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return usuarios
	 */
	@NoSerializable
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return permissao
	 */
	public PermissaoEnum getPermissao() {
		return permissao;
	}

	@NoSerializable
	@Override
	public Object getPk() {
		return getNome();
	}

	/**
	 * @return prioridade
	 */
	public PrioridadeEnum getPrioridade() {
		return prioridade;
	}

	/**
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * Possui a função de verificar se o perfil está ativo.
	 *
	 * @author rabsouza
	 *
	 * @return true - ativo / false - desativo
	 */
	@NoSerializable
	public Boolean isAtivo() {
		return status;
	}

	/**
	 * @param descricao
	 *            String
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @param usuarios
	 *            List<Usuario>
	 */
	public void setUsuarios(List<Usuario> Usuarios) {
		usuarios = Usuarios;
	}

	/**
	 * @param nome
	 *            String
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param permissao
	 *            PermissaoEnum
	 */
	public void setPermissao(PermissaoEnum permissao) {
		this.permissao = permissao;
	}

	/**
	 * @param prioridade
	 *            PrioridadeEnum
	 */
	public void setPrioridade(PrioridadeEnum prioridade) {
		this.prioridade = prioridade;
	}

	/**
	 * @param status
	 *            Boolean
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * Possui a função ...
	 *
	 * @author rabsouza
	 *
	 */
	@PostLoad
	public void postLoad() {
		getUsuarios();
	}

}
