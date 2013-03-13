package br.com.zetex.lojix.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import br.com.zetex.lojix.exception.LojixException;

/**
 * Representa os atributos padrões que todas as entidades possui.
 * <ul>
 * <li>UIID</li>
 * <li>VERSION</li>
 * </ul>
 *
 * @author rabsouza
 * @since 14/03/2012
 * @version 1.0.0
 * @see Serializable
 * @see Comparable
 * @see Cloneable
 */
public interface BaseEntity extends Serializable, Comparable<BaseEntity>,
		Cloneable {

	/**
	 * Metodo <b>fillBeanFromMap</b> possui a função de preencher um <em>{@link BaseEntity}</em> a partir de um <em>{@link Map}</em>.
	 *
	 * @param map
	 *            Map
	 * @throws LojixException
	 */
	public void fillBeanFromMap(Map<String, Object> map) throws LojixException;

	/**
	 * @return dataAlteracao
	 */
	public Date getDataAlteracao();

	/**
	 * @return dataCriacao
	 */
	public Date getDataCriacao();

	/**
	 * Retorna o valor do atributo <b>pk</b> do tipo <em>{@link Object}</em>.
	 * </br>Obs.: Metodo para retorna a chave primaria de uma tabela.
	 *
	 * @return pk Object
	 */
	public Object getPk();

	/**
	 * @return uiId
	 */
	public Long getUiId();

	/**
	 * @return usuarioCorrente
	 */
	public String getUsuarioCorrente();

	/**
	 * @return versao
	 */
	public Integer getVersao();

	/**
	 * @param dataAlteracao
	 *            Date
	 */
	@Deprecated
	public void setDataAlteracao(Date dataAlteracao);

	/**
	 * @param dataCriacao
	 *            Date
	 */
	@Deprecated
	public void setDataCriacao(Date dataCriacao);

	/**
	 * @param uiId
	 *            Long
	 */
	@Deprecated
	public void setUiId(Long uiId);

	/**
	 * @param usuarioCorrente
	 *            usuarioCorrente
	 */
	public void setUsuarioCorrente(String usuarioCorrente);

	/**
	 * @param versao
	 *            Integer
	 */
	@Deprecated
	public void setVersao(Integer versao);

	/**
	 * Metodo <b>toMap</b> possui a função de retorna uma <em>{@link Map}</em> que representa o objeto.
	 *
	 * @return map Map<String, Object>
	 * @throws LojixException
	 */
	public Map<String, Object> toMap() throws LojixException;

	/**
	 * Metodo <b>toXml</b> possui a função de retorna uma <em>{@link String}</em> que representa o xml do objeto.
	 *
	 * @return xml String
	 * @throws LojixException
	 */
	public String toXml() throws LojixException;

}
