package br.com.zetex.lojix.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import br.com.zetex.lojix.dao.exception.CreateEntityException;
import br.com.zetex.lojix.dao.exception.NoneExistentEntityException;
import br.com.zetex.lojix.entity.BaseEntity;
import br.com.zetex.lojix.exception.LojixException;

/**
 * <strong>BaseDao</strong> possui todas as funções que são globais para fazer a
 * persistência de uma entidade.
 * 
 * @version 1.0
 * @since 29/12/2010
 */
public interface BaseDao extends Serializable {
	
	/**
	 * Método<strong>delete(obj)</strong> possui a função de apagar uma
	 * entidade.
	 * 
	 * @since 29/12/2010
	 * @param obj
	 *            {@link BaseEntity} entidade que será apagada.
	 * @return <strong>true</strong> {@link BaseEntity} apagado com successo.<br>
	 *         <strong>false</strong> {@link BaseEntity} não foi apagado com
	 *         successo.
	 * @throws NoneExistentEntityException
	 *             Não existe essa entidade!
	 * @throws NullPointerException
	 *             Error ao acessar um objeto nulo!
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean delete(BaseEntity obj)
			throws NoneExistentEntityException, NullPointerException, LojixException;
	
	/**
	 * Método<strong>delete(namedQuery, mapParans, nullable)</strong> possui a
	 * função de apagar uma entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean delete(String namedQuery,
			Map<String, Object> mapParans, boolean nullable) throws LojixException;
	
	/**
	 * Método<strong>delete(namedQuery, obj)</strong> possui a função de apagar
	 * uma entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean delete(String namedQuery, Object... obj)
			throws LojixException;
	
	/**
	 * Método<strong>executeQuery(namedQuery)</strong> possui a função de
	 * executar uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean executeQuery(String namedQuery) throws LojixException;
	
	/**
	 * Método<strong>executeQuery(namedQuery, mapParans, nullable)</strong>
	 * possui a função de executar uma query e tamb�m pode ser enviado
	 * paramêtros para essa query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean executeQuery(String namedQuery,
			Map<String, Object> mapParans, boolean nullable) throws LojixException;
	
	/**
	 * Método<strong>executeQuery(namedQuery, obj)</strong> possui a função de
	 * executar uma query e tamb�m pode ser enviado paramêtros para essa query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean executeQuery(String namedQuery, Object... obj)
			throws LojixException;
	
	/**
	 * Método<strong>get(obj)</strong> possui a função de recupera/consulta uma
	 * entidade.
	 * 
	 * @since 29/12/2010
	 * @param obj
	 *            {@link BaseEntity} entidade que será recuperada/consultada.
	 * @return <strong>model</strong> {@link BaseEntity} entidade que foi
	 *         recuperada/consultada.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract BaseEntity get(BaseEntity obj) throws LojixException;
	
	/**
	 * Método<strong>getEntityManager()</strong> retorna o atributo <em>em</em>
	 * 
	 * @return <strong>em</strong> possui o tipo <em>{@link EntityManager}</em>
	 */
	public abstract EntityManager getEntityManager();
	
	/**
	 * Método<strong>isEmpty(classe)</strong> possui a função de verificar se
	 * existe registro cadastrado para a entidade.
	 * 
	 * @since 29/12/2010
	 * @param classe
	 *            {@link BaseEntity} que será verificada.
	 * @return <strong>true</strong> {@link BaseEntity} não possui nenhum registro
	 *         cadastrado.<br>
	 *         <strong>false</strong> {@link BaseEntity} possui registro(s)
	 *         cadastrado(s).
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean isEmpty(Class<?> classe) throws LojixException;
	
	/**
	 * Método<strong>isEmpty(namedQuery, mapParans, nullable)</strong> possui a
	 * função de verificar se existe registro cadastrado para a query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>true</strong> query não retornou nenhum registro
	 *         cadastrado.<br>
	 *         <strong>false</strong> query retornou registro(s) cadastrado(s).
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean isEmpty(String namedQuery,
			Map<String, Object> mapParans, boolean nullable) throws LojixException;
	
	/**
	 * Método<strong>isEmpty(namedQuery, obj)</strong> possui a função de
	 * verificar se existe registro cadastrado para a query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>true</strong> query não retornou nenhum registro
	 *         cadastrado.<br>
	 *         <strong>false</strong> query retornou registro(s) cadastrado(s).
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean isEmpty(String namedQuery, Object... obj)
			throws LojixException;
	
	/**
	 * Método<strong>persist(obj)</strong> possui a função de criar/inserir uma
	 * nova entidade.
	 * 
	 * @since 29/12/2010
	 * @param obj
	 *            {@link BaseEntity} entidade que será criada/inserida.
	 * @return <strong>true</strong> {@link BaseEntity} criado/inserido com successo.<br>
	 *         <strong>false</strong> {@link BaseEntity} não foi criado/inserido com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 * @throws CreateEntityException
	 *             Já existe registro.
	 */
	public abstract Boolean persist(BaseEntity obj) throws LojixException, CreateEntityException;
	
	/**
	 * Metodo <b>persist(namedQuery, mapParans, nullable)</b> possui a função de
	 * criar/inserir uma nova entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean persist(String namedQuery,
			Map<String, Object> mapParans, boolean nullable) throws LojixException;
	
	/**
	 * Metodo <b>persist(namedQuery, obj)</b> possui a função de criar/inserir
	 * uma nova entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean persist(String namedQuery, Object... obj)
			throws LojixException;
	
	/**
	 * Método<strong>select(namedQuery, maxResults, firstResult, mapParans,
	 * nullable)</strong> possui a função de selecionar uma lista de entidade
	 * através de uma query e limitar o tamanho da lista de resultado. Caso o
	 * tamanho sejá limitado, deverá ser enviado o paramêtro da posição inicial
	 * e o número de objetos que deveráo ser retornados.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param maxResults
	 *            {@link Integer} indica o número de objetos que serão
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posição inicial da lista de
	 *            entidades.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> select(String namedQuery, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException;
	
	/**
	 * Método<strong>select(namedQuery, maxResults, firstResult, obj)</strong>
	 * possui a função de selecionar uma lista de entidade através de uma query
	 * e limitar o tamanho da lista de resultado. Caso o tamanho sejá limitado,
	 * deverá ser enviado o paramêtro da posição inicial e o número de objetos
	 * que deveráo ser retornados.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param maxResults
	 *            {@link Integer} indica o número de objetos que serão
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posição inicial da lista de
	 *            entidades.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> select(String namedQuery, int maxResults,
			int firstResult, Object... obj) throws LojixException;
	
	/**
	 * Método<strong>select(namedQuery, mapParans, nullable)</strong> possui a
	 * função de selecionar uma lista de entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> select(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException;
	
	/**
	 * Método<strong>select(namedQuery, obj)</strong> possui a função de
	 * selecionar uma lista de entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> select(String namedQuery, Object... obj)
			throws LojixException;
	
	/**
	 * Método<strong>selectAll(classe)</strong> possui a função de selecionar
	 * todos os registros de uma entidade.
	 * 
	 * @since 29/12/2010
	 * @param classe
	 *            {@link BaseEntity} entidade que será selecionada.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> selectAll(Class<?> classe) throws LojixException;
	
	/**
	 * Método<strong>selectAll(classe, maxResults, firstResult)</strong> possui
	 * a função de selecionar todos os registros de uma entidade e limitar o
	 * tamanho da lista de resultado. Caso o tamanho sejá limitado, deverá ser
	 * enviado o paramêtro posição inicial e o número de objetos que deveráo ser
	 * retornados da lista de entidade.
	 * 
	 * @since 29/12/2010
	 * @param classe
	 *            {@link BaseEntity} entidade que será selecionada.
	 * @param maxResults
	 *            {@link Integer} indica o número de objetos que serão
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posição inicial da lista de
	 *            entidades.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> selectAll(Class<?> classe, int maxResults, int firstResult)
			throws LojixException;
	
	/**
	 * Método<strong>selectAsMap(namedQuery, maxResults, firstResult,
	 * mapParans, nullable)</strong> possui a função de selecionar uma lista de
	 * objetos através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param maxResults
	 *            {@link Integer} indica o número de objetos que serão
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posição inicial da lista de
	 *            entidades.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<Object> selectAsMap(String namedQuery, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException;
	
	/**
	 * Método<strong>selectAsMap(namedQuery, maxResults, firstResult,
	 * obj)</strong> possui a função de selecionar uma lista de objetos através
	 * de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param maxResults
	 *            {@link Integer} indica o número de objetos que serão
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posição inicial da lista de
	 *            entidades.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<Object> selectAsMap(String namedQuery, int maxResults,
			int firstResult, Object... obj) throws LojixException;
	
	/**
	 * Método<strong>selectBean(namedQuery, mapParans, nullable)</strong>
	 * possui a função de selecionar uma lista de entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>entity</strong> {@link BaseEntity} entidade de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public BaseEntity selectBean(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException;
	
	/**
	 * Método<strong>selectBean(namedQuery, obj)</strong> possui a função de
	 * selecionar uma lista de entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>entity</strong> {@link BaseEntity} entidade de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public BaseEntity selectBean(String namedQuery, Object... obj) throws LojixException;
	
	/**
	 * Método<strong>selectBeanQuery(query, mapParans, nullable)</strong>
	 * possui a função de selecionar uma lista de entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param query
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>entity</strong> {@link BaseEntity} entidade de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public BaseEntity selectBeanQuery(String query, Map<String, Object> mapParans,
			boolean nullable) throws LojixException;
	
	/**
	 * Método<strong>selectBeanQuery(query, obj)</strong> possui a função de
	 * selecionar uma lista de entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param query
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>entity</strong> {@link BaseEntity} entidade de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public BaseEntity selectBeanQuery(String query, Object... obj) throws LojixException;
	
	/**
	 * Método<strong>selectQuery(query, maxResults, firstResult, mapParans,
	 * nullable)</strong> possui a função de selecionar uma lista de entidade
	 * através de uma query e limitar o tamanho da lista de resultado. Caso o
	 * tamanho sejá limitado, deverá ser enviado o paramêtro da posição inicial
	 * e o número de objetos que deveráo ser retornados.
	 * 
	 * @since 29/12/2010
	 * @param query
	 *            {@link String} query que será executada.
	 * @param maxResults
	 *            {@link Integer} indica o número de objetos que serão
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posição inicial da lista de
	 *            entidades.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> selectQuery(String query, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException;
	
	/**
	 * Método<strong>selectQuery(query, maxResults, firstResult, obj)</strong>
	 * possui a função de selecionar uma lista de entidade através de uma query
	 * e limitar o tamanho da lista de resultado. Caso o tamanho sejá limitado,
	 * deverá ser enviado o paramêtro da posição inicial e o número de objetos
	 * que deveráo ser retornados.
	 * 
	 * @since 29/12/2010
	 * @param query
	 *            {@link String} query que será executada.
	 * @param maxResults
	 *            {@link Integer} indica o número de objetos que serão
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posição inicial da lista de
	 *            entidades.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> selectQuery(String query, int maxResults,
			int firstResult, Object... obj) throws LojixException;
	
	/**
	 * Método<strong>selectQuery(query, mapParans, nullable)</strong> possui a
	 * função de selecionar uma lista de entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param query
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> selectQuery(String query, Map<String, Object> mapParans,
			boolean nullable) throws LojixException;
	
	/**
	 * Método<strong>selectQuery(query, obj)</strong> possui a função de
	 * selecionar uma lista de entidade através de uma query.
	 * 
	 * @since 29/12/2010
	 * @param query
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public List<BaseEntity> selectQuery(String query, Object... obj)
			throws LojixException;
	
	/**
	 * Método<strong>size(classe)</strong> possui a função de retorna o número
	 * de registros cadastrado para a entidade.
	 * 
	 * @since 29/12/2010
	 * @param classe
	 *            {@link BaseEntity} entidade que será selecionada.
	 * @return <strong>size</strong> {@link Integer} número de registro
	 *         cadastrados para a entidade.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public Integer size(Class<?> classe) throws LojixException;
	
	/**
	 * Metodo <b>size(namedQuery, mapParans, nullable)</b> possui a função de
	 * retornar o número de registro encontrados para uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>size</strong> {@link Integer} número de registro
	 *         encontrados para a query.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public Integer size(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException;
	
	/**
	 * Metodo <b>size(namedQuery, obj)</b> possui a função de retornar o número
	 * de registro encontrados para uma query.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>size</strong> {@link Integer} número de registro
	 *         encontrados para a query.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public Integer size(String namedQuery, Object... obj) throws LojixException;
	
	/**
	 * Método<strong>update(obj)</strong> possui a função de atualizar uma
	 * entidade.
	 * 
	 * @since 29/12/2010
	 * @param obj
	 *            {@link BaseEntity} entidade que será atualizada.
	 * @return model {@link BaseEntity} entidade que foi atualizada.
	 * @throws NoneExistentEntityException
	 *             Não existe essa entidade!
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 * @throws CreateEntityException
	 *             Erro ao tentar criar uma nova entity.
	 */
	public BaseEntity update(BaseEntity obj) throws NoneExistentEntityException,
			LojixException, CreateEntityException;
	
	/**
	 * Método<strong>update(namedQuery, mapParans, nullable)</strong> possui a
	 * função de executar uma query de atualização.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos paramêtros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws NoneExistentEntityException
	 *             Não existe essa entidade!
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public Boolean update(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws NoneExistentEntityException, LojixException;
	
	/**
	 * Método<strong>update(namedQuery, obj)</strong> possui a função de
	 * executar uma query de atualização.
	 * 
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param obj
	 *            {@link Object} lista de paramêtros que serão utilizados pela
	 *            query.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws NoneExistentEntityException
	 *             Não existe essa entidade!
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public Boolean update(String namedQuery, Object... obj)
			throws NoneExistentEntityException, LojixException;
	
}
