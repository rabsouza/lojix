package br.com.zetex.lojix.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import br.com.zetex.lojix.entity.enumeration.EstadoCivilEnum;
import br.com.zetex.lojix.entity.enumeration.SexoEnum;
import br.com.zetex.lojix.entity.enumeration.TipoPessoaEnum;
import br.com.zetex.lojix.exception.LojixException;

/**
 * Representa as informações da entidade <code>PESSOA_FISICA</CODE>.
 * <ul>
 * <li>CPF</li>
 * <li>DATA_NASCIMENTO</li>
 * <li>NOME_PAI</li>
 * <li>NOME_MAE</li>
 * <li>SEXO</li>
 * <li>ESTADO_CIVIL</li>
 * </ul>
 *
 * @author rabsouza
 * @version 1.0.0
 * @since 18/07/2010
 * @see Pessoa
 */
@Entity
@XmlRootElement(name = "pessoaFisica", namespace = "http://model.lojix.zetex.com.br")
@XmlType(name = "pessoaFisica", namespace = "http://model.lojix.zetex.com.br")
@DiscriminatorValue(value = "FIS")
@Table(name = "PESSOA_FISICA")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min = 14, max = 14)
	@Column(name = "CPF", length = 14, nullable = false, unique = true)
	@Pattern(regexp = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d", message = "Deve está no formato: 999.999.999-99")
	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO_CIVIL", length = 10, nullable = true)
	private EstadoCivilEnum estadoCivil;

	@Size(min = 1, max = 100)
	@Column(name = "NOME_CONJUGE", length = 100, nullable = true)
	private String nomeConjuge;

	@Size(min = 1, max = 100)
	@Column(name = "NOME_MAE", length = 100, nullable = true)
	private String nomeMae;

	@Size(min = 1, max = 100)
	@Column(name = "NOME_PAI", length = 100, nullable = true)
	private String nomePai;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "SEXO", length = 10, nullable = false)
	private SexoEnum sexo;

	/**
	 * Construtor para a classe PessoaFisica.
	 *
	 */
	@SuppressWarnings("deprecation")
	public PessoaFisica() {
		super();
		setTipo(TipoPessoaEnum.FIS);
	}

	/**
	 * Construtor para a classe PessoaFisica.
	 *
	 * @param map
	 * @throws LojixException
	 */
	@SuppressWarnings("deprecation")
	public PessoaFisica(Map<String, Object> map) throws LojixException {
		super(map);
		setTipo(TipoPessoaEnum.FIS);
	}

	/**
	 * @return cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @return dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @return estadoCivil
	 */
	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @return nomeConjuge
	 */
	public String getNomeConjuge() {
		return nomeConjuge;
	}

	/**
	 * @return nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * @return nomePai
	 */
	public String getNomePai() {
		return nomePai;
	}

	/**
	 * @return sexo
	 */
	public SexoEnum getSexo() {
		return sexo;
	}

	/**
	 * @param cpf
	 *            String
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @param dataNascimento
	 *            Date
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @param estadoCivil
	 *            EstadoCivilEnum
	 */
	public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @param nomeConjuge
	 *            nomeConjuge
	 */
	public void setNomeConjuge(String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}

	/**
	 * @param nomeMae
	 *            String
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * @param nomePai
	 *            String
	 */
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	/**
	 * @param sexo
	 *            SexoEnum
	 */
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

}
