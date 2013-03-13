package br.com.zetex.lojix.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import br.com.zetex.lojix.util.NoSerializable;

/**
 * Representa as informações da entidade <code>CLIENTE</CODE>.
 * <ul>
 * <li>ID</li>
 * <li>BLOQUEADO</li>
 * <li>FK_CLASSIFICAO_CLIENTE</li>
 * <li>CODIGO</li>
 * <li>DATA_ATIVACAO</li>
 * <li>EMPRESA</li>
 * <li>SEQUENCE_CLIENTE_ID</li>
 * <li>LIMITE_CREDITO</li>
 * <li>FK_PESSOA</li>
 * <li>STATUS</li>
 * <li>FK_USUARIO</li>
 * </ul>
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 18/07/2010
 * @see BaseEntityImpl
 * @see BaseEntity
 */
@Entity
@XmlRootElement(name = "cliente",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="cliente", namespace="http://model.lojix.zetex.com.br")
@Table(name = "CLIENTE")
public class Cliente extends BaseEntityImpl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "BLOQUEADO", nullable = false)
	private Boolean bloqueado = Boolean.FALSE;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<CampoLivre> camposLivre;

	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_CLASSIFICAO_CLIENTE", nullable = false)
	private ClassificacaoCliente classificacaoCliente;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[A-Za0-9]*", message = "Pode ser digitado letras, números e espaços somente e tamanho máximo de 25 caracteres.")
	@Column(name = "CODIGO", length = 25, unique = true, nullable = false)
	private String codigo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Crediario> crediarios;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ATIVACAO", nullable = true)
	private Date dataAtivacao;

	@Size(min = 1, max = 250)
	@Column(name = "EMPRESA", length = 250, nullable = true)
	private String empresa;

	@Id
	@SequenceGenerator(name = "SEQUENCE_CLIENTE_ID", sequenceName = "SEQUENCE_CLIENTE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CLIENTE_ID")
	@Column(name = "ID", nullable = false)
	@NotNull
	private Integer id;

	@Column(name = "LIMITE_CREDITO", length = 250, nullable = true, precision = 4)
	private BigDecimal limiteCredito;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_PESSOA", nullable = true)
	private Pessoa pessoa;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Referencia> referencias;

	@NotNull
	@Column(name = "STATUS", nullable = false)
	private Boolean status = Boolean.FALSE;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_USUARIO", nullable = true)
	private Usuario usuario;

	/**
	 * @return bloqueado
	 */
	public Boolean getBloqueado() {
		return bloqueado;
	}

	/**
	 * @return camposLivre
	 */
	public List<CampoLivre> getCamposLivre() {
		return camposLivre;
	}

	/**
	 * @return classificacaoCliente
	 */
	public ClassificacaoCliente getClassificacaoCliente() {
		return classificacaoCliente;
	}

	/**
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return crediarios
	 */
	public List<Crediario> getCrediarios() {
		return crediarios;
	}

	/**
	 * @return dataAtivacao
	 */
	public Date getDataAtivacao() {
		return dataAtivacao;
	}

	/**
	 * @return empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return limiteCredito
	 */
	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	/**
	 * @return pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	@Override
	@NoSerializable
	public Object getPk() {
		return getId();
	}

	/**
	 * @return referencias
	 */
	public List<Referencia> getReferencias() {
		return referencias;
	}

	/**
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @return usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param bloqueado
	 *            bloqueado
	 */
	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * @param camposLivre
	 *            camposLivre
	 */
	public void setCamposLivre(List<CampoLivre> camposLivre) {
		this.camposLivre = camposLivre;
	}

	/**
	 * @param classificacaoCliente
	 *            classificacaoCliente
	 */
	public void setClassificacaoCliente(ClassificacaoCliente classificacaoCliente) {
		this.classificacaoCliente = classificacaoCliente;
	}

	/**
	 * @param codigo
	 *            codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @param crediarios
	 *            crediarios
	 */
	public void setCrediarios(List<Crediario> crediarios) {
		this.crediarios = crediarios;
	}

	/**
	 * @param dataAtivacao
	 *            dataAtivacao
	 */
	public void setDataAtivacao(Date dataAtivacao) {
		this.dataAtivacao = dataAtivacao;
	}

	/**
	 * @param empresa
	 *            empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @param id
	 *            id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param limiteCredito
	 *            limiteCredito
	 */
	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	/**
	 * @param pessoa
	 *            pessoa
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @param referencias
	 *            referencias
	 */
	public void setReferencias(List<Referencia> referencias) {
		this.referencias = referencias;
	}

	/**
	 * @param status
	 *            status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @param usuario
	 *            usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
