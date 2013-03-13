package br.com.zetex.lojix.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.zetex.lojix.dao.exception.CreateEntityException;
import br.com.zetex.lojix.dao.exception.NoneExistentEntityException;
import br.com.zetex.lojix.entity.BaseEntity;
import br.com.zetex.lojix.exception.LojixException;
import br.com.zetex.lojix.exception.LojixRuntimeException;
import br.com.zetex.lojix.util.LoggerUtil;
import br.com.zetex.lojix.util.Messages;
import br.com.zetex.lojix.util.PackageLog;

/**
 * <strong>BaseDaoImpl</strong> possui todas as funções que são globais para
 * fazer a persistência de uma entidade.
 *
 * @version 1.0
 * @since 28/12/2010
 * @see BaseDao
 */
@Named
@ApplicationScoped
public class BaseDaoImpl implements BaseDao {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerUtil.getLogger(BaseDao.class,
			PackageLog.DAO);

	@Inject
	private EntityManager em;

	/**
	 * Contrustor para a classe BaseDaoImpl
	 *
	 */
	public BaseDaoImpl() {
	}

	/**
	 * Contrustor para a classe BaseDaoImpl
	 * Utilizar em classe de Teste.
	 *
	 * @param em
	 *            EntityManager para teste.
	 */
	@Deprecated
	public BaseDaoImpl(EntityManager em) {
		this.em = em;
	}

	/**
	 * Método <strong>closeTransaction()</strong> possui a função flush dos dados.
	 *
	 * @since 28/12/2010
	 */
	private void closeTransaction() {
		getEntityManager().flush();
	}

	@Override
	public Boolean delete(BaseEntity obj) throws NoneExistentEntityException,
			NullPointerException, LojixException {

		try {
			try {
				synchronized (obj) {
					obj = getEntityManager().getReference(obj.getClass(),
							obj.getPk());
					getEntityManager().remove(obj);
					return true;
				}
			} catch (NoResultException e) {
				LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
				throw new NoneExistentEntityException(
						Messages.get("msg.dao.exception.noresult"), e);

			} catch (EntityNotFoundException enfe) {
				LOGGER.error(Messages.get("msg.dao.exception.entitynotfound"),
						enfe);
				throw new NoneExistentEntityException(
						Messages.get("msg.dao.exception.entitynotfound"), enfe);

			} catch (NullPointerException ex) {
				LOGGER.error(Messages.get("msg.dao.exception.nullpointer"), ex);
				throw ex;

			}
		} finally {
			closeTransaction();
		}

	}

	/**
	 * Método <strong>delete(nullable, query)</strong> possui a função de apagar
	 * uma entidade através de uma query.
	 *
	 * @author rabsouza
	 * @since 01/06/2011
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @param query
	 *            JPAQL que será executada.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	private Boolean delete(boolean nullable, Query query)
			throws LojixException {
		try {

			try {
				synchronized (query) {
					Integer result = query.executeUpdate();
					return result != 0;
				}
			} catch (NoResultException e) {
				if (!nullable) {
					LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
					throw new LojixException(
							Messages.get("msg.dao.exception.noresult"), e);
				}
				return false;
			}

		} finally {
			closeTransaction();
		}
	}

	@Override
	public Boolean delete(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByMap(mapParans, query);
		return delete(nullable, query);
	}

	@Override
	public Boolean delete(String namedQuery, Object... obj) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByArray(query, obj);
		return delete(false, query);
	}

	/**
	 * Método <strong>executeQuery(query)</strong> possui a função de executar
	 * uma query e tamb�m pode ser enviado param�tros para essa query.
	 *
	 * @author rabsouza
	 * @since 01/06/2011
	 * @param query
	 *            JPAQL que será executada.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	private Boolean executeQuery(Query query) throws LojixException {
		try {
			synchronized (query) {
				Integer result = query.executeUpdate();
				return result != 0;
			}
		} catch (Exception e) {
			LOGGER.error(Messages.get("msg.dao.exception"), e);
			throw new LojixException(Messages.get("msg.dao.exception"), e);

		} finally {
			closeTransaction();
		}
	}

	@Override
	public Boolean executeQuery(String namedQuery) throws LojixException {
		Object obj = null;
		return this.executeQuery(namedQuery, obj);

	}

	@Override
	public Boolean executeQuery(String namedQuery,
			Map<String, Object> mapParans, boolean nullable) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByMap(mapParans, query);
		return executeQuery(query);
	}

	@Override
	public Boolean executeQuery(String namedQuery, Object... obj)
			throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByArray(query, obj);
		return executeQuery(query);
	};

	/**
	 * Método <strong>fillParansByArray(obj)</strong> possui a função de
	 * preencher os parametros através de um array.
	 *
	 * @author rabsouza
	 * @since 30/05/2011
	 * @param query
	 *            Objeto {@link Query} que será adicionado os param�tros.
	 * @param obj
	 *            {@link Object} lista de param�tros que seráo utilizados pela
	 *            query.
	 */
	private void fillParansByArray(Query query, Object... obj) {
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				query.setParameter(i + 1, obj[i]);
			}
		}
	}

	/**
	 * Método <strong>fillParansByMap(mapParans, query)</strong> possui a função
	 * de preencher os parametros através de um map.
	 *
	 * @author rabsouza
	 * @since 30/05/2011
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos param�tros.
	 * @param query
	 *            Objeto {@link Query} que será adicionado os param�tros.
	 */
	private void fillParansByMap(Map<String, Object> mapParans,
			Query query) {
		if ((mapParans != null) && !mapParans.isEmpty()) {
			for (String key : mapParans.keySet()) {
				query.setParameter(key, mapParans.get(key));
			}
		}
	}

	@Override
	public BaseEntity get(BaseEntity obj) throws LojixException {

		if(obj == null){
			throw new LojixRuntimeException(Messages.get("msg.dao.exception.nullpointer"));
		}

		try {
			synchronized (obj) {
				BaseEntity model = getEntityManager().find(obj.getClass(),
						obj.getPk());
				return model;
			}
		} catch (NoResultException e) {
			LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
			throw new LojixException(Messages.get("msg.dao.exception.noresult"), e);
		} finally {
			closeTransaction();
		}

	}

	@Override
	public EntityManager getEntityManager() {
		return em;

	}

	/**
	 * Método <strong>isEmpty(nullable, query)</strong> possui a função de
	 * verificar se existe registro cadastrado para a query.
	 *
	 * @author rabsouza
	 * @since 01/06/2011
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @param query
	 *            JPAQL que será executada.
	 * @return <strong>true</strong> query não retornou nenhum registro
	 *         cadastrado.<br>
	 *         <strong>false</strong> query retornou registro(s) cadastrado(s).
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	private Boolean isEmpty(boolean nullable, Query query)
			throws LojixException {
		try {
			synchronized (query) {
				int result = query.executeUpdate();
				return result != 0;
			}
		} catch (Exception e) {
			if (!nullable) {
				LOGGER.error(Messages.get("msg.dao.exception"), e);
				throw new LojixException(Messages.get("msg.dao.exception"), e);
			}
			return false;
		} finally {
			closeTransaction();
		}
	}

	@Override
	public Boolean isEmpty(Class<?> classe) throws LojixException {
		List<BaseEntity> list = selectAll(classe);
		return list.isEmpty();

	}

	@Override
	public Boolean isEmpty(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByMap(mapParans, query);
		return isEmpty(nullable, query);

	}

	@Override
	public Boolean isEmpty(String namedQuery, Object... obj) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByArray(query, obj);
		return isEmpty(false, query);

	}

	@Override
	public Boolean persist(BaseEntity obj) throws LojixException, CreateEntityException {

		try {
			synchronized (obj) {
				getEntityManager().persist(obj);
			}
			return true;
		} catch (Exception ex) {

			String msg = ex.getLocalizedMessage();
			if ((msg == null) || (msg.length() == 0)) {

				if ((obj != null)
						&& ((obj.getPk() == null) || (get(obj) == null))) {
					LOGGER.error(Messages.get("msg.dao.exception.createentity"),
							ex);
					throw new CreateEntityException(
							Messages.get("msg.dao.exception.createentity"), ex);

				} else {
					LOGGER.error(Messages
							.get("msg.dao.exception.createentity.existent"), ex);
					throw new CreateEntityException(
							Messages.get("msg.dao.exception.createentity.existent"),
							ex);

				}
			}

			LOGGER.error(Messages.get("msg.dao.exception"), ex);
			throw new LojixException(Messages.get("msg.dao.exception"), ex);

		} finally {
			closeTransaction();
		}
	}

	/**
	 * Método <strong>persist(nullable, query)</strong> possui a função de
	 * criar/inserir uma nova entidade.
	 *
	 * @author rabsouza
	 * @since 01/06/2011
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @param query
	 *            JPAQL que será executada.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	private Boolean persist(boolean nullable, Query query)
			throws LojixException {
		try {
			try {
				synchronized (query) {
					Integer result = query.executeUpdate();
					return result != 0;
				}
			} catch (NoResultException e) {
				if (!nullable) {
					LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
					throw new LojixException(
							Messages.get("msg.dao.exception.noresult"), e);
				}
				return false;
			}
		} finally {
			closeTransaction();
		}
	}

	@Override
	public Boolean persist(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByMap(mapParans, query);
		return persist(nullable, query);

	}

	@Override
	public Boolean persist(String namedQuery, Object... obj) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByArray(query, obj);
		return persist(false, query);

	}

	/**
	 * Método <strong>select(namedQuery, all, maxResults, firstResult,
	 * mapParans, nullable)</strong> possui a função de selecionar uma lista de
	 * entidade através de uma query e verifica se o tamanho da lista será
	 * limitado. Caso o tamanho sej� limitado, dever� ser enviado o param�tro da
	 * posi��o inicial e o n�mero de objetos que dever�o ser retornados.
	 *
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param all
	 *            {@link Boolean} indica ser o resultado será limitado.
	 * @param maxResults
	 *            {@link Integer} indica o n�mero de objetos que seráo
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posi��o inicial da lista de
	 *            entidades.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos param�tros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	@SuppressWarnings("unchecked")
	private List<BaseEntity> select(String namedQuery, boolean all, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException {
		List<BaseEntity> listEntity = null;
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			fillParansByMap(mapParans, query);
			if (!all) {
				query.setMaxResults(firstResult + maxResults);
			}
			synchronized (query) {
				listEntity = query.getResultList();
			}
			if (!all) {
				firstResult = firstResult > listEntity.size() ? listEntity
						.size() : firstResult;
				return listEntity.subList(firstResult, listEntity.size());
			}
			return listEntity;
		} catch (EntityExistsException e) {
			LOGGER.error(Messages.get("msg.dao.exception.entityexists"), e);
			throw new LojixException(Messages.get("msg.dao.exception.entityexists"),
					e);

		} catch (NoResultException e) {
			if (!nullable) {
				LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
				throw new LojixException(Messages.get("msg.dao.exception.noresult"),
						e);
			}
			return null;
		} catch (PersistenceException e) {
			LOGGER.error(Messages.get("msg.dao.exception.persistence"), e);
			throw new LojixException(Messages.get("msg.dao.exception.persistence"),
					e);

		}

	}

	/**
	 * Método <strong>select(namedQuery, all, maxResults, firstResult,
	 * obj)</strong> possui a função de selecionar uma lista de entidade através
	 * de uma query e verifica se o tamanho da lista será limitado. Caso o
	 * tamanho sej� limitado, dever� ser enviado o param�tro da posi��o inicial
	 * e o n�mero de objetos que dever�o ser retornados.
	 *
	 * @since 29/12/2010
	 * @param namedQuery
	 *            {@link String} query que será executada.
	 * @param all
	 *            {@link Boolean} indica ser o resultado será limitado.
	 * @param maxResults
	 *            {@link Integer} indica o n�mero de objetos que seráo
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posi��o inicial da lista de
	 *            entidades.
	 * @param obj
	 *            {@link Object} lista de param�tros que seráo utilizados pela
	 *            query.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	@SuppressWarnings("unchecked")
	private List<BaseEntity> select(String namedQuery, boolean all, int maxResults,
			int firstResult, Object... obj) throws LojixException {
		List<BaseEntity> listEntity = null;
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);
			fillParansByArray(query, obj);
			if (!all) {
				query.setMaxResults(firstResult + maxResults);
			}
			synchronized (query) {
				listEntity = query.getResultList();
			}
			if (!all) {
				firstResult = firstResult > listEntity.size() ? listEntity
						.size() : firstResult;
				return listEntity.subList(firstResult, listEntity.size());
			}
			return listEntity;
		} catch (EntityExistsException e) {
			LOGGER.error(Messages.get("msg.dao.exception.entityexists"), e);
			throw new LojixException(Messages.get("msg.dao.exception.entityexists"),
					e);

		} catch (NoResultException e) {
			LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
			throw new LojixException(Messages.get("msg.dao.exception.noresult"), e);

		} catch (PersistenceException e) {
			LOGGER.error(Messages.get("msg.dao.exception.persistence"), e);
			throw new LojixException(Messages.get("msg.dao.exception.persistence"),
					e);

		}

	}

	@Override
	public List<BaseEntity> select(String namedQuery, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException {
		return select(namedQuery, false, maxResults, firstResult, mapParans,
				nullable);

	}

	@Override
	public List<BaseEntity> select(String namedQuery, int maxResults,
			int firstResult, Object... obj) throws LojixException {
		return select(namedQuery, false, maxResults, firstResult, obj);

	}

	@Override
	public List<BaseEntity> select(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		return select(namedQuery, true, -1, -1, mapParans, nullable);

	}

	@Override
	public List<BaseEntity> select(String namedQuery, Object... obj)
			throws LojixException {
		return select(namedQuery, true, -1, -1, obj);

	}

	@Override
	public List<BaseEntity> selectAll(Class<?> classe) throws LojixException {
		return selectAll(classe, true, -1, -1);

	}

	/**
	 * Método <strong>selectAll(classe, all, maxResults, firstResult)</strong>
	 * possui a função de selecionar todos os registros de uma entidade e
	 * verifica se o tamanho da lista será limitado. Caso o tamanho sej�
	 * limitado, dever� ser enviado o param�tro posi��o inicial e o n�mero de
	 * objetos que dever�o ser retornados da lista de entidade.
	 *
	 * @since 29/12/2010
	 * @param classe
	 *            {@link BaseEntity} entidade que será selecionada.
	 * @param all
	 *            {@link Boolean} indica ser o resultado será limitado.
	 * @param maxResults
	 *            {@link Integer} indica o n�mero de objetos que seráo
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posi��o inicial da lista de
	 *            entidades.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	@SuppressWarnings({ "unchecked" })
	private List<BaseEntity> selectAll(Class<?> classe, boolean all, int maxResults,
			int firstResult) throws LojixException {

		List<BaseEntity> listEntity = null;
		try {
			if (classe == null) {
				return new ArrayList<BaseEntity>();
			}
			String strQuery = "SELECT OBJECT(obj) FROM "
					+ classe.getSimpleName() + " obj ";
			Query query = getEntityManager().createQuery(strQuery);
			if (!all) {
				query.setMaxResults(firstResult + maxResults);
			}
			synchronized (query) {
				listEntity = query.getResultList();
			}
			if (!all) {
				firstResult = firstResult > listEntity.size() ? listEntity
						.size() : firstResult;
				return listEntity.subList(firstResult, listEntity.size());
			}
			return listEntity;

		} catch (EntityExistsException e) {
			LOGGER.error(Messages.get("msg.dao.exception.entityexists"), e);
			throw new LojixException(Messages.get("msg.dao.exception.entityexists"),
					e);

		} catch (NoResultException e) {
			LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
			throw new LojixException(Messages.get("msg.dao.exception.noresult"), e);

		} catch (PersistenceException e) {
			LOGGER.error(Messages.get("msg.dao.exception.persistence"), e);
			throw new LojixException(Messages.get("msg.dao.exception.persistence"),
					e);

		} finally {
			closeTransaction();
		}

	}

	@Override
	public List<BaseEntity> selectAll(Class<?> classe, int maxResults, int firstResult)
			throws LojixException {
		return selectAll(classe, false, maxResults, firstResult);

	}

	/**
	 * Método <strong>selectAsMap(maxResults, firstResult, nullable,
	 * query)</strong> possui a função de selecionar uma lista de objetos
	 * através de uma query.
	 *
	 * @author rabsouza
	 * @since 01/06/2011
	 * @param maxResults
	 *            {@link Integer} indica o n�mero de objetos que seráo
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posi��o inicial da lista de
	 *            entidades.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @param query
	 *            JPAQL que será executada.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	@SuppressWarnings("unchecked")
	private List<Object> selectAsMap(int maxResults, int firstResult,
			boolean nullable, Query query) throws LojixException {
		List<Object> listMap;
		try {
			if ((firstResult > 0) && (maxResults > 0)) {
				query.setMaxResults(firstResult + maxResults);
			}
			synchronized (query) {
				listMap = query.getResultList();
			}
			if ((firstResult > 0) && (maxResults > 0)) {
				firstResult = firstResult > listMap.size() ? listMap.size()
						: firstResult;
				return listMap.subList(firstResult, listMap.size());
			}
			return listMap;
		} catch (EntityExistsException e) {
			LOGGER.error(Messages.get("msg.dao.exception.entityexists"), e);
			throw new LojixException(Messages.get("msg.dao.exception.entityexists"),
					e);

		} catch (NoResultException e) {
			if (!nullable) {
				LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
				throw new LojixException(Messages.get("msg.dao.exception.noresult"),
						e);
			}
			return null;
		} catch (PersistenceException e) {
			LOGGER.error(Messages.get("msg.dao.exception.persistence"), e);
			throw new LojixException(Messages.get("msg.dao.exception.persistence"),
					e);

		}
	}

	@Override
	public List<Object> selectAsMap(String namedQuery, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByMap(mapParans, query);
		return selectAsMap(maxResults, firstResult, nullable, query);
	}

	@Override
	public List<Object> selectAsMap(String namedQuery, int maxResults,
			int firstResult, Object... obj) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByArray(query, obj);
		return selectAsMap(maxResults, firstResult, false, query);
	}

	/**
	 * Método <strong>selectBean(nullable, query)</strong> possui a função de
	 * selecionar um Bean através de uma query.
	 *
	 * @author rabsouza
	 * @since 01/06/2011
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @param query
	 *            JPAQL que será executada.
	 * @return <strong>entity</strong> {@link BaseEntity} entidade de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	private BaseEntity selectBean(boolean nullable, Query query)
			throws LojixException {
		BaseEntity entity;
		try {
			synchronized (query) {
				entity = (BaseEntity) query.getSingleResult();
			}
			return nullable || (entity != null) ? entity : (BaseEntity) new Object();
		} catch (EntityExistsException e) {
			LOGGER.error(Messages.get("msg.dao.exception.entityexists"), e);
			throw new LojixException(Messages.get("msg.dao.exception.entityexists"),
					e);

		} catch (NoResultException e) {
			if (!nullable) {
				LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
				throw new LojixException(Messages.get("msg.dao.exception.noresult"),
						e);
			}
			return null;
		} catch (PersistenceException e) {
			LOGGER.error(Messages.get("msg.dao.exception.persistence"), e);
			throw new LojixException(Messages.get("msg.dao.exception.persistence"),
					e);

		}
	}

	@Override
	public BaseEntity selectBean(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByMap(mapParans, query);
		return selectBean(nullable, query);

	}

	@Override
	public BaseEntity selectBean(String namedQuery, Object... obj) throws LojixException {
		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByArray(query, obj);
		return selectBean(true, query);

	}

	@Override
	public BaseEntity selectBeanQuery(String strQuery,
			Map<String, Object> mapParans, boolean nullable) throws LojixException {
		Query query = getEntityManager().createQuery(strQuery);
		fillParansByMap(mapParans, query);
		return selectBean(nullable, query);

	}

	@Override
	public BaseEntity selectBeanQuery(String strQuery, Object... obj)
			throws LojixException {
		Query query = getEntityManager().createQuery(strQuery);
		fillParansByArray(query, obj);
		return selectBean(true, query);

	}

	/**
	 * Método <strong>selectQuery(query, all, maxResults, firstResult,
	 * mapParans, nullable)</strong> possui a função de selecionar uma lista de
	 * entidade através de uma query e verifica se o tamanho da lista será
	 * limitado. Caso o tamanho sej� limitado, dever� ser enviado o param�tro da
	 * posi��o inicial e o n�mero de objetos que dever�o ser retornados.
	 *
	 * @since 29/12/2010
	 * @param query
	 *            {@link String} query que será executada.
	 * @param all
	 *            {@link Boolean} indica ser o resultado será limitado.
	 * @param maxResults
	 *            {@link Integer} indica o n�mero de objetos que seráo
	 *            retornados.
	 * @param firstResult
	 *            {@link Integer} indica a posi��o inicial da lista de
	 *            entidades.
	 * @param mapParans
	 *            {@link Map} que possui o nome e o valor dos param�tros.
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	@SuppressWarnings("unchecked")
	private List<BaseEntity> selectQuery(String strQuery, boolean all,
			int maxResults, int firstResult, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		List<BaseEntity> listEntity = null;
		try {
			Query query = getEntityManager().createQuery(strQuery);
			fillParansByMap(mapParans, query);
			if (!all) {
				query.setMaxResults(firstResult + maxResults);
			}
			synchronized (query) {
				listEntity = query.getResultList();
			}
			if (!all) {
				firstResult = firstResult > listEntity.size() ? listEntity
						.size() : firstResult;
				return listEntity.subList(firstResult, listEntity.size());
			}
			return listEntity;
		} catch (EntityExistsException e) {
			LOGGER.error(Messages.get("msg.dao.exception.entityexists"), e);
			throw new LojixException(Messages.get("msg.dao.exception.entityexists"),
					e);

		} catch (NoResultException e) {
			if (!nullable) {
				LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
				throw new LojixException(Messages.get("msg.dao.exception.noresult"),
						e);
			}
			return null;
		} catch (PersistenceException e) {
			LOGGER.error(Messages.get("msg.dao.exception.persistence"), e);
			throw new LojixException(Messages.get("msg.dao.exception.persistence"),
					e);

		}

	}

	@Override
	public List<BaseEntity> selectQuery(String query, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException {
		return selectQuery(query, false, maxResults, firstResult, mapParans,
				nullable);

	}

	@Override
	public List<BaseEntity> selectQuery(String query, int maxResults,
			int firstResult, Object... obj) throws LojixException {
		return selectQuery(query, false, maxResults, firstResult, obj);

	}

	@Override
	public List<BaseEntity> selectQuery(String query, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		return selectQuery(query, true, -1, -1, mapParans, nullable);

	}

	@Override
	public List<BaseEntity> selectQuery(String query, Object... obj)
			throws LojixException {
		return selectQuery(query, true, -1, -1, obj);

	}

	@Override
	public Integer size(Class<?> classe) throws LojixException {
		List<BaseEntity> list = selectAll(classe);
		return list.size();

	}

	/**
	 * Método <strong>size(query)</strong> possui a função de retornar o n�mero
	 * de registro encontrados para uma query.
	 *
	 * @author rabsouza
	 * @since 01/06/2011
	 * @param query
	 *            JPAQL que será executada.
	 * @return <strong>size</strong> {@link Integer} n�mero de registro
	 *         encontrados para a query.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	private Integer size(Query query) throws LojixException {
		try {
			synchronized (query) {
				return query.getResultList().size();
			}
		} catch (Exception e) {
			LOGGER.error(Messages.get("msg.dao.exception"), e);
			throw new LojixException(e.getMessage(),
					e.getCause());

		} finally {
			closeTransaction();
		}
	}

	@Override
	public Integer size(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByMap(mapParans, query);
		return size(query);

	}

	@Override
	public Integer size(String namedQuery, Object... obj) throws LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByArray(query, obj);
		return size(query);

	}

	@Override
	public BaseEntity update(BaseEntity obj) throws NoneExistentEntityException,
			LojixException, CreateEntityException {

		try {
			synchronized (obj) {
				BaseEntity model = getEntityManager().merge(obj);
				return model;
			}

		} catch (Exception ex) {

			String msg = ex.getLocalizedMessage();
			if ((msg == null) || (msg.length() == 0)) {
				if ((obj.getPk() == null) || (get(obj) == null)) {

					LOGGER.error(Messages.get("msg.dao.exception.createentity"),
							ex);
					throw new CreateEntityException(ex.getMessage(),
							ex.getCause());

				} else {
					LOGGER.error(Messages
							.get("msg.dao.exception.createentity.existent"), ex);
					throw new CreateEntityException(
							Messages.get("msg.dao.exception.createentity.existent"),
							ex);

				}
			}

			LOGGER.error(Messages.get("msg.dao.exception"), ex);
			throw new LojixException(Messages.get("msg.dao.exception"), ex);

		} finally {
			closeTransaction();
		}

	}

	/**
	 * Método <strong>update(nullable, query)</strong> possui a função de
	 * executar uma query de atualiza��o.
	 *
	 * @author rabsouza
	 * @since 01/06/2011
	 * @param nullable
	 *            Indica se o resultado pode ser nullo.
	 * @param query
	 *            JPAQL que será executada.
	 * @return <strong>true</strong> query executada com successo.<br>
	 *         <strong>false</strong> não foi possível executar a query com
	 *         successo.
	 * @throws LojixException
	 *             Ocorreu uma exceção geral do projeto!
	 */
	private Boolean update(boolean nullable, Query query)
			throws LojixException {
		try {
			try {
				synchronized (query) {
					Integer result = query.executeUpdate();
					return result != 0;
				}
			} catch (NoResultException e) {
				if (!nullable) {
					LOGGER.error(Messages.get("msg.dao.exception.noresult"), e);
					throw new LojixException(
							Messages.get("msg.dao.exception.noresult"), e);
				}
				return false;
			}
		} finally {
			closeTransaction();
		}
	}

	@Override
	public Boolean update(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws NoneExistentEntityException, LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByMap(mapParans, query);
		return update(nullable, query);

	}

	@Override
	public Boolean update(String namedQuery, Object... obj)
			throws NoneExistentEntityException, LojixException {

		Query query = getEntityManager().createNamedQuery(namedQuery);
		fillParansByArray(query, obj);
		return update(false, query);

	}

}
