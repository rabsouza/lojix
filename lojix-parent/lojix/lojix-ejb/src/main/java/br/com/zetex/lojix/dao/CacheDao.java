package br.com.zetex.lojix.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.zetex.lojix.dao.exception.CreateEntityException;
import br.com.zetex.lojix.dao.exception.NoneExistentEntityException;
import br.com.zetex.lojix.entity.BaseEntity;
import br.com.zetex.lojix.exception.LojixException;

/**
 * Representa a classe base que possui as funções globais para o controlador de
 * um entidade e possui o map de cache das entidades do sistema.
 * 
 * @version 1.0.0
 * @since 24/07/2010 : 19:26:59
 * @see BaseDao
 */
public abstract class CacheDao implements BaseDao {
	
	private static final Map<String, HashMap<Object, BaseEntity>> cacheEntity = new HashMap<String, HashMap<Object, BaseEntity>>();
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	@Named("baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * Contrustor para a classe CacheDao
	 * 
	 */
	public CacheDao() {
		super();
	}
	
	/**
	 * Contrustor para a classe CacheDao
	 * 
	 * @param dao
	 */
	@Deprecated
	public CacheDao(BaseDao dao) {
		super();
		this.dao = dao;
	}
	
	/**
	 * Possui a função de limpar a cache dao.
	 * 
	 * @author rabsouza
	 * 
	 */
	public static void clearCache() {
		cacheEntity.clear();
	}
	
	/**
	 * Metodo <b>addEntity(model)</b> possui a função de adicionar uma nova
	 * entidade a cache de entidade.
	 * 
	 * @since 28/12/2010
	 * @param model
	 *            {@link BaseEntity} que será adicionado a cache de entidade.
	 * @return <strong>true</strong> {@link BaseEntity} adicionado com successo.<br>
	 *         <strong>false</strong> {@link BaseEntity} não foi adicionado com
	 *         successo.
	 */
	private Boolean addEntity(BaseEntity model) {
		
		if (model != null) {
			final String nameClass = model.getClass().getName();
			HashMap<Object, BaseEntity> mapEntity;
			
			if (cacheEntity.containsKey(nameClass)) {
				mapEntity = cacheEntity.get(nameClass);
			} else {
				mapEntity = new HashMap<Object, BaseEntity>();
			}
			
			if (mapEntity.containsKey(model.getPk())) {
				final BaseEntity obj = mapEntity.get(model.getPk());
				
				if (obj.getVersao() < model.getVersao()) {
					mapEntity.put(model.getPk(), model);
					cacheEntity.put(nameClass, mapEntity);
					return true;
				} else {
					return false;
				}
				
			} else {
				mapEntity.put(model.getPk(), model);
				cacheEntity.put(nameClass, mapEntity);
				return true;
				
			}
		} else {
			return false;
		}
		
	}
	
	/**
	 * Método <strong>checkListEntity</strong> possui a função de verificar se a
	 * lista de entidade j� est� na cache de entidade.
	 * 
	 * @since 28/12/2010
	 * @param listBaseEntity
	 *            {@link List} lista de entidade que seráo adicionadas a cache
	 *            de entidade.
	 */
	private void checkListEntity(List<BaseEntity> listBaseEntity) {
		if (!listBaseEntity.isEmpty()) {
			for (final BaseEntity model : listBaseEntity) {
				addEntity(model);
			}
		}
	}
	
	@Override
	public Boolean delete(BaseEntity obj) throws NoneExistentEntityException,
			NullPointerException, LojixException {
		final boolean result = dao.delete(obj);
		if (result) {
			removeEntity(obj);
		}
		return result;
		
	}
	
	@Override
	public Boolean delete(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		return dao.delete(namedQuery, mapParans, nullable);
	}
	
	@Override
	public Boolean delete(String namedQuery, Object... obj) throws LojixException {
		return dao.delete(namedQuery, obj);
		
	}
	
	@Override
	public Boolean executeQuery(String namedQuery) throws LojixException {
		return dao.executeQuery(namedQuery);
		
	}
	
	@Override
	public Boolean executeQuery(String namedQuery,
			Map<String, Object> mapParans, boolean nullable) throws LojixException {
		return dao.executeQuery(namedQuery, mapParans, nullable);
	}
	
	@Override
	public Boolean executeQuery(String namedQuery, Object... obj)
			throws LojixException {
		return dao.executeQuery(namedQuery, obj);
		
	}
	
	@Override
	public BaseEntity get(BaseEntity obj) throws LojixException {
		BaseEntity model = getEntity(obj);
		if (model != null) {
			return model;
		} else {
			model = dao.get(obj);
			if (model != null) {
				addEntity(model);
			}
			return model;
		}
		
	}
	
	/**
	 * Metodo <b>getEntity(obj)</b> possui a função de retorna um entidade da
	 * cache de entidade.
	 * 
	 * @since 28/12/2010
	 * @param model
	 *            {@link BaseEntity} verifica se a entidade est� na cache de
	 *            entidade.
	 * @return <strong>model</strong> {@link BaseEntity} da cache de entidade.
	 */
	private BaseEntity getEntity(BaseEntity model) {
		if (model != null) {
			final String nameClass = model.getClass().getName();
			if (cacheEntity.containsKey(nameClass)) {
				
				final Map<Object, BaseEntity> mapEntity = cacheEntity.get(nameClass);
				if (!mapEntity.isEmpty()) {
					if (mapEntity.containsKey(model.getPk())) {
						return mapEntity.get(model.getPk());
					}
				}
			}
		}
		return null;
		
	}
	
	@Override
	public EntityManager getEntityManager() {
		return dao.getEntityManager();
	}
	
	/**
	 * Método<strong>isEmpty(classe)</strong> possui a função de verificar se
	 * existe registro cadastrado para a entidade.
	 * 
	 * @since 29/12/2010
	 * @return <strong>true</strong> {@link BaseEntity} não possui nenhum registro
	 *         cadastrado.<br>
	 *         <strong>false</strong> {@link BaseEntity} possui registro(s)
	 *         cadastrado(s).
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Boolean isEmpty() throws LojixException;
	
	@Override
	public Boolean isEmpty(Class<?> classe) throws LojixException {
		return dao.isEmpty(classe);
		
	}
	
	@Override
	public Boolean isEmpty(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		return dao.isEmpty(namedQuery, mapParans, nullable);
	}
	
	@Override
	public Boolean isEmpty(String namedQuery, Object... obj) throws LojixException {
		return dao.isEmpty(namedQuery, obj);
		
	}
	
	@Override
	public Boolean persist(BaseEntity obj) throws LojixException, CreateEntityException {
		final boolean result = dao.persist(obj);
		if (result) {
			if ((obj.getPk() != null) && (obj.getVersao() != null)) {
				addEntity(obj);
			} else {
				final BaseEntity model = dao.get(obj);
				if (model != null) {
					addEntity(model);
				}
			}
		}
		return result;
		
	}
	
	@Override
	public Boolean persist(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		return dao.persist(namedQuery, mapParans, nullable);
	}
	
	@Override
	public Boolean persist(String namedQuery, Object... obj) throws LojixException {
		return dao.persist(namedQuery, obj);
		
	}
	
	/**
	 * Metodo <b>removeEntity</b> possui a função de remover uma entidade da
	 * cache de entidade.
	 * 
	 * @since 28/12/2010
	 * @param model
	 *            {@link BaseEntity} que será removido da cache de entidade.
	 * @return <strong>true</strong> {@link BaseEntity} removido com successo.<br>
	 *         <strong>false</strong> {@link BaseEntity} não foi removido com
	 *         successo.
	 */
	private Boolean removeEntity(BaseEntity model) {
		if (model != null) {
			final String nameClass = model.getClass().getName();
			Map<Object, BaseEntity> mapEntity;
			
			if (cacheEntity.containsKey(nameClass)) {
				mapEntity = cacheEntity.get(nameClass);
			} else {
				return false;
			}
			
			if (mapEntity.containsKey(model.getPk())) {
				mapEntity.remove(model.getPk());
				cacheEntity.put(nameClass, (HashMap<Object, BaseEntity>) mapEntity);
				return true;
				
			}
		}
		return false;
		
	}
	
	@Override
	public List<BaseEntity> select(String namedQuery, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException {
		final List<BaseEntity> listBaseEntity = dao.select(namedQuery, maxResults,
				firstResult, mapParans, nullable);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
	}
	
	@Override
	public List<BaseEntity> select(String namedQuery, int maxResults,
			int firstResult, Object... obj) throws LojixException {
		
		final List<BaseEntity> listBaseEntity = dao.select(namedQuery, maxResults,
				firstResult, obj);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
		
	}
	
	@Override
	public List<BaseEntity> select(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		final List<BaseEntity> listBaseEntity = dao.select(namedQuery, mapParans,
				nullable);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
	}
	
	@Override
	public List<BaseEntity> select(String namedQuery, Object... obj)
			throws LojixException {
		
		final List<BaseEntity> listBaseEntity = dao.select(namedQuery, obj);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
	}
	
	/**
	 * Método<strong>selectAll(classe)</strong> possui a função de selecionar
	 * todos os registros de uma entidade.
	 * 
	 * @since 29/12/2010
	 * @return <strong>listEntity</strong> {@link List} lista de resultado
	 *         selecionado.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract List<BaseEntity> selectAll() throws LojixException;
	
	@Override
	public List<BaseEntity> selectAll(Class<?> classe) throws LojixException {
		final List<BaseEntity> listBaseEntity = dao.selectAll(classe);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
		
	}
	
	@Override
	public List<BaseEntity> selectAll(Class<?> classe, int maxResults, int firstResult)
			throws LojixException {
		final List<BaseEntity> listBaseEntity = dao.selectAll(classe, maxResults,
				firstResult);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
		
	}
	
	/**
	 * Método<strong>selectAll(classe, maxResults, firstResult)</strong> possui
	 * a função de selecionar todos os registros de uma entidade e limitar o
	 * tamanho da lista de resultado. Caso o tamanho sejá limitado, deverá ser
	 * enviado o paramêtro posição inicial e o número de objetos que deveráo ser
	 * retornados da lista de entidade.
	 * 
	 * @since 29/12/2010
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
	public abstract List<BaseEntity> selectAll(int maxResults, int firstResult)
			throws LojixException;
	
	@Override
	public List<Object> selectAsMap(String namedQuery, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException {
		return dao.selectAsMap(namedQuery, maxResults, firstResult, mapParans,
				nullable);
	}
	
	@Override
	public List<Object> selectAsMap(String namedQuery, int maxResults,
			int firstResult, Object... obj) throws LojixException {
		return dao.selectAsMap(namedQuery, maxResults, firstResult, obj);
		
	}
	
	@Override
	public BaseEntity selectBean(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		final BaseEntity model = dao.selectBean(namedQuery, mapParans, nullable);
		if (model != null) {
			addEntity(model);
		}
		return model;
	}
	
	@Override
	public BaseEntity selectBean(String namedQuery, Object... obj) throws LojixException {
		final BaseEntity model = dao.selectBean(namedQuery, obj);
		if (model != null) {
			addEntity(model);
		}
		return model;
	}
	
	@Override
	public BaseEntity selectBeanQuery(String query, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		final BaseEntity model = dao.selectBeanQuery(query, mapParans, nullable);
		if (model != null) {
			addEntity(model);
		}
		return model;
	}
	
	@Override
	public BaseEntity selectBeanQuery(String query, Object... obj) throws LojixException {
		final BaseEntity model = dao.selectBeanQuery(query, obj);
		if (model != null) {
			addEntity(model);
		}
		return model;
	}
	
	@Override
	public List<BaseEntity> selectQuery(String query, int maxResults,
			int firstResult, Map<String, Object> mapParans, boolean nullable)
			throws LojixException {
		final List<BaseEntity> listBaseEntity = dao.selectQuery(query, maxResults,
				firstResult, mapParans, nullable);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
	}
	
	@Override
	public List<BaseEntity> selectQuery(String query, int maxResults,
			int firstResult, Object... obj) throws LojixException {
		
		final List<BaseEntity> listBaseEntity = dao.selectQuery(query, maxResults,
				firstResult, obj);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
		
	}
	
	@Override
	public List<BaseEntity> selectQuery(String query, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		final List<BaseEntity> listBaseEntity = dao.selectQuery(query, mapParans,
				nullable);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
	}
	
	@Override
	public List<BaseEntity> selectQuery(String query, Object... obj)
			throws LojixException {
		
		final List<BaseEntity> listBaseEntity = dao.selectQuery(query, obj);
		checkListEntity(listBaseEntity);
		return listBaseEntity;
	}
	
	/**
	 * Método<strong>size(classe)</strong> possui a função de retorna o número
	 * de registros cadastrado para a entidade.
	 * 
	 * @since 29/12/2010
	 * @return <strong>size</strong> {@link Integer} número de registro
	 *         cadastrados para a entidade.
	 * @throws LojixException
	 *             Ocorreu uma execeção geral do projeto!
	 */
	public abstract Integer size() throws LojixException;
	
	@Override
	public Integer size(Class<?> classe) throws LojixException {
		return dao.size(classe);
		
	}
	
	@Override
	public Integer size(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws LojixException {
		return dao.size(namedQuery, mapParans, nullable);
	}
	
	@Override
	public Integer size(String namedQuery, Object... obj) throws LojixException {
		return dao.size(namedQuery, obj);
		
	}
	
	@Override
	public BaseEntity update(BaseEntity obj) throws NoneExistentEntityException,
			LojixException, CreateEntityException {
		final BaseEntity model = dao.update(obj);
		if (model != null) {
			addEntity(model);
		}
		return model;
		
	}
	
	@Override
	public Boolean update(String namedQuery, Map<String, Object> mapParans,
			boolean nullable) throws NoneExistentEntityException, LojixException {
		return dao.update(namedQuery, mapParans, nullable);
	}
	
	@Override
	public Boolean update(String namedQuery, Object... obj)
			throws NoneExistentEntityException, LojixException {
		return dao.update(namedQuery, obj);
		
	}
	
}
