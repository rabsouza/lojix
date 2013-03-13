package br.com.zetex.lojix.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.com.zetex.lojix.entity.enumeration.NomeFonteCreditoEnum;
import br.com.zetex.lojix.util.NoSerializable;

/**
 * Representa as informações da entidade <code>CREDIARIO</CODE>.
 * <ul>
 * <li>ID</li>
 * <li>NOME_FONTE_CREDITO</li>
 * <li>OBSERVACAO</li>
 * <li>STATUS</li>
 * <li>DATA_REGISTRO</li>
 * <li>DATA_CONSULTA</li>
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
@XmlRootElement(name = "crediario",  namespace="http://model.lojix.zetex.com.br")
@XmlType(name="crediario", namespace="http://model.lojix.zetex.com.br")
@Table(name = "CREDIARIO")
public class Crediario extends BaseEntityImpl implements BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CLIENTE")
	private Cliente cliente;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CONSULTA", nullable = true)
	private Date dataConsulta;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_REGISTRO", nullable = true)
	private Date dataRegistro;

	@Id
	@SequenceGenerator(name = "SEQUENCE_CREDIARIO_ID", sequenceName = "SEQUENCE_CREDIARIO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CREDIARIO_ID")
	@Column(name = "ID", nullable = false)
	@NotNull
	private Integer id;

	@Column(name = "NOME_FONTE_CREDITO", length = 3, nullable = false)
	@Enumerated(EnumType.STRING)
	private NomeFonteCreditoEnum nomeFonteCredito;

	@Size(min = 1, max = 250)
	@Column(name = "OBSERVACAO", length = 250, nullable = true)
	private String observacao;

	@NotNull
	@Column(name = "STATUS", nullable = false)
	private Boolean status = Boolean.FALSE;

	/**
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @return dataConsulta
	 */
	public Date getDataConsulta() {
		return dataConsulta;
	}

	/**
	 * @return dataRegistro
	 */
	public Date getDataRegistro() {
		return dataRegistro;
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return nomeFonteCredito
	 */
	public NomeFonteCreditoEnum getNomeFonteCredito() {
		return nomeFonteCredito;
	}

	/**
	 * @return observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	@Override
	@NoSerializable
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
	 * @param cliente
	 *            cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @param dataConsulta
	 *            dataConsulta
	 */
	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	/**
	 * @param dataRegistro
	 *            dataRegistro
	 */
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	/**
	 * @param id
	 *            id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param nomeFonteCredito
	 *            nomeFonteCredito
	 */
	public void setNomeFonteCredito(NomeFonteCreditoEnum nomeFonteCredito) {
		this.nomeFonteCredito = nomeFonteCredito;
	}

	/**
	 * @param observacao
	 *            observacao
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @param status
	 *            status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
